package hoge.mock2.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import hoge.mock2.common.exception.AppException;
import hoge.mock2.common.exception.FileException;
import hoge.mock2.common.exception.FileException.ERR_KIND;
import hoge.mock2.common.valueObject.Args;
import hoge.mock2.common.valueObject.ExeFile;

public class ExeControl {

	private ExeFile exeFile;
	private int timeOutSec;
	private List<String> stdOut;
	private List<String> stdErr;

	public ExeControl(String exeFilePath) throws FileException {
		this(exeFilePath, 10);
	}

	public ExeControl(String exeFilePath, int timeOutsec) throws FileException {
		this.exeFile = new ExeFile(exeFilePath);
		this.timeOutSec = timeOutsec;
	}

	public int execute(Args args) throws AppException {

		ProcessBuilder pb = getProcess(args.getList());
		return execute2(pb);
	}



	private ProcessBuilder getProcess(List<String> args) {
		List<String> list = new ArrayList<String>();
		list.add(exeFile.getExeFilePathString());
		list.addAll(args);
		return new ProcessBuilder(list);
	}

	// タイムアウト対応版。標準出力のバッファ処理が無いのでタイムアウトエラーになる。
	private int execute1(ProcessBuilder pb) throws FileException {

		try {
			Process process = pb.start();

			boolean end = process.waitFor(10, TimeUnit.SECONDS); //10秒でタイムアウト
			if (end) {
				int result = process.exitValue();
				System.out.println("正常終了 " + result);
				return result;
			} else {
				System.out.println("タイムアウト");
				process.destroy(); // プロセスを強制終了
				return -1;
			}
		} catch (Exception e) {
			throw new FileException(ERR_KIND.FILE_EXECUTE_ERROR, exeFile.getExeFilePathString(),e);
		}
	}


	private int execute2(ProcessBuilder pb) throws FileException {

		//InputStreamのスレッド終了待ち
		try {
			Process process = pb.start();

			//InputStreamのスレッド開始
			InputStreamThread it = new InputStreamThread(process.getInputStream(),"Shift-JIS");
			InputStreamThread et = new InputStreamThread(process.getErrorStream(),"Shift-JIS");
			it.start();
			et.start();

			//プロセスの終了待ち
			boolean end = process.waitFor(timeOutSec, TimeUnit.SECONDS);
			it.join();
			et.join();

			stdOut = new ArrayList<String>(it.getStringList());
			stdErr = new ArrayList<String>(et.getStringList());

			return process.exitValue();
		} catch (Exception e) {
			throw new FileException(ERR_KIND.FILE_EXECUTE_ERROR, exeFile.getExeFilePathString(),e);
		}



	}

	public void sysout() {
		System.out.println("標準出力");
		for (String s : stdOut) {
			System.out.println(s);
		}

		System.out.println("標準エラー");
		for (String s : stdErr) {
			System.out.println(s);
		}
	}

}

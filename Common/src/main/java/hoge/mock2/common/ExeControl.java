package hoge.mock2.common;

import hoge.mock2.common.exception.FileException;
import hoge.mock2.common.valueObject.ExeFile;

public class ExeControl {

	private ExeFile exeFile;

	public ExeControl(String exeFilePath) throws FileException {
		exeFile = new ExeFile(exeFilePath);
		com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
		System.out.println(NTSystem.getName());
		System.out.println(NTSystem.getDomain());
	}
}

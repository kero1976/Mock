package hoge.mock2.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import hoge.mock2.common.exception.FileException;
import hoge.mock2.common.valueObject.Args;

class ExeControlTest {

	@Test
	void コンストラクタのテスト_NULL() {

		FileException e = assertThrows(FileException.class,
				() -> new ExeControl(null));

		assertTrue(e.getMessage().contains("VALUE_IS_NULL:null"));
	}

	@Test
	void コンストラクタのテスト_値が空() {

		FileException e = assertThrows(FileException.class,
				() -> new ExeControl(""));

		assertTrue(e.getMessage().contains("FILE_NOT_FOUND:"));
	}

	@Test
	void コンストラクタのテスト_存在しないファイル() {

		FileException e = assertThrows(FileException.class,
				() -> new ExeControl("c:\\存在しないファイル"));

		assertTrue(e.getMessage().contains("FILE_NOT_FOUND:c:\\存在しないファイル"));
	}

	@Test
	void コンストラクタのテスト_存在するフォルダ() {

		FileException e = assertThrows(FileException.class,
				() -> new ExeControl("C:\\Windows"));

		assertTrue(e.getMessage().contains("VALUE_IS_DIR:C:\\Windows"));
	}

	@Test
	void コンストラクタのテスト_セキュリティ例外() {

		// TODO: 未実装
	}

	@Test
	void keytoolのテスト() {
		try {
			ExeControl exe = new ExeControl("G:\\Java\\pleiades\\java\\8\\bin\\keytool.exe");
			List<String> list = new ArrayList<String>();
			list.add("-list");
			list.add("-keystore");
			list.add("G:\\Java\\pleiades\\java\\8\\jre\\lib\\security\\cacerts");
			list.add("-storepass");
			list.add("changeit");

			Args args = new Args(list);

			int result = exe.execute(args);

			exe.sysout();
			assertEquals(0, result);
		} catch (Exception e) {
			fail("失敗");
		}


	}
}

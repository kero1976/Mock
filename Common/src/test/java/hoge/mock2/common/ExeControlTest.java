package hoge.mock2.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hoge.mock2.common.exception.FileException;

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

		FileException e = assertThrows(FileException.class,
				() -> new ExeControl("C:\\JavaTest2\\1.txt"));
System.out.println(e.getMessage());
		assertTrue(e.getMessage().contains("VALUE_IS_DIR:C:\\Windows"));
	}
}

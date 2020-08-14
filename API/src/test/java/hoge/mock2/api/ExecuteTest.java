package hoge.mock2.api;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hoge.mock2.api.exception.ApiException;

class ExecuteTest {

	String timeOut = "30";

	@Test
	void 基本テスト１() {

		System.out.println("通信時間10秒、10秒後に終了し、0が返ること");
		Params params = null;
		try {
			params = new Params(new String[]{
				"10",timeOut,"0"
			});
		}catch(Exception e) {
			fail();
		}
		ResultAndTimeCheck(params, 0, 10);
	}

	@Test
	void 基本テスト２() {

		System.out.println("通信時間60秒、60秒後に終了し、1が返ること");
		Params params = null;
		try {
			params = new Params(new String[]{
				"60",timeOut,"0"
			});
		} catch (ApiException e) {
			fail();
		}
		ResultAndTimeCheck(params, 1, 60);
	}

	@Test
	void キャンセルテスト１() {

		System.out.println("通信時間10秒、5秒後に終了し、2が返ること");
		Params params = null;
		try {
			params = new Params(new String[]{
				"10",timeOut,"0"
			});
			ResultAndTimeCheck(params, 2, 5);
		} catch (ApiException e) {
			fail();
		}
	}

	@Test
	void キャンセルテスト２() {

		System.out.println("通信時間60秒、40秒後に終了し、2が返ること");
		Params params = null;
		try {
			params = new Params(new String[]{
				"60",timeOut,"0"
			});
			ResultAndTimeCheck(params, 2, 40);
		} catch (ApiException e) {
			fail();
		}
	}

	@Test
	void 無通信テスト() {

		System.out.println("無通信フラグを有効。30秒後に終了し、1が返ること");
		Params params = null;
		try {
			params = new Params(new String[]{
				"60",timeOut,"1"
			});
			ResultAndTimeCheck(params, 1, 30);
		} catch (ApiException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	private void ResultAndTimeCheck(Params params, int returnCode, int time) {

		try {
			long start = System.currentTimeMillis();
			Execute exe = new Execute(params);
			try {
				int result = exe.start();
				assertEquals(returnCode, result);
			} catch (ApiException e) {
				System.out.println("ApiExceptionが発生");
				fail(e.getMessage());
			}
			long end = System.currentTimeMillis();
			long actualTime = (end - start) / 1000;
			System.out.println("処理時間：" + actualTime);

			assertTrue((time - 2) <= actualTime && actualTime <= (time + 2));
		}catch(Exception e) {
			System.out.println("Exceptionが発生");
			e.printStackTrace();
		}catch(Error e2) {
			System.out.println("Errorが発生");
			e2.printStackTrace();
		}

	}
}

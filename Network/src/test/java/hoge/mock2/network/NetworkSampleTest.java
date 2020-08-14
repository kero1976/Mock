package hoge.mock2.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hoge.mock2.common.Result;
import hoge.mock2.network.exception.NetworkException;

public class NetworkSampleTest {

	@Test
	void 通信を実行＿正常値をセットして終了することを確認() {
		NetworkSample sample = new NetworkSample(3,5,false);
		try {
			sample.execute();
		} catch (NetworkException e) {
			fail("予期せぬエラー");
		}
		int result = Result.getResult();
		assertEquals(result, 0);
	}
}

package hoge.mock2.network;

import hoge.mock2.common.Log;
import hoge.mock2.network.exception.NetworkException;
import hoge.mock2.network.exception.NetworkException.KIND;

class Watcher {

	void timer(int i) throws NetworkException {
		try {
			Log.sysout("タイマーを開始します。タイムアウトミリ秒：" + i);
			Thread.sleep(i);
			Log.sysout("タイマーを終了します。");
		} catch (InterruptedException e) {
			throw new NetworkException(KIND.SLEEP_ERROR, e.toString());
		}
	}
}

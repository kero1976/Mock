package hoge.mock2.network;

import hoge.mock2.common.Log;
import hoge.mock2.network.exception.NetworkException;
import hoge.mock2.network.exception.NetworkException.KIND;

class Watcher {

	void timer(int sec) throws NetworkException {
		try {
			Log.sysout("タイマーを開始します。タイムアウト秒：" + sec);
			for(int i =0; i < sec; i++) {
				Thread.sleep(1000);
			}
			Log.sysout("タイマーを終了します。");

		} catch (InterruptedException e) {
			Log.sysout("タイマーが終了しませんでした。");
			throw new NetworkException(KIND.TIMER_ERROR, e.toString());
		}
	}
}

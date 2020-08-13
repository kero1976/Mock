package hoge.mock2.network;

import hoge.mock2.common.Log;
import hoge.mock2.common.Result;
import hoge.mock2.network.exception.NetworkException;
import hoge.mock2.network.exception.NetworkException.KIND;

/**
 * 通信ダミー
 *
 */
class Fake {

	/**
	 *
	 * @param i 実行時間(秒)
	 * @throws NetworkException
	 */
	void execute(int sec) throws NetworkException {
		Log.sysout("通信ダミー処理を開始します。");
		try {
			// Thread.sleep(sec);
			for(int i =0; i < sec; i++) {
				Thread.sleep(1000);
				Result.setViewData(i + "");
			}
			// 通信が完了したので終了コードをセットする
			Result.setResult("0");
			Log.sysout("通信ダミー処理を終了します。");
		} catch (InterruptedException e) {
			Log.sysout("通信ダミー処理が終了しませんでした。");
			throw new NetworkException(KIND.NETWORK_ERROR, e.toString());
		}
	}
}

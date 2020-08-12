package hoge.mock2.network;

import hoge.mock2.common.Result;
import hoge.mock2.network.exception.NetworkException;
import hoge.mock2.network.exception.NetworkException.KIND;

public class NetworkSample {

	private int _networkTime;
	private int _watchTime;

	public NetworkSample(int networkTIme, int watchTime) {
		this._networkTime = networkTIme;
		this._watchTime = watchTime;
	}

	/**
	 *
	 * @throws NetworkException
	 */
	public void execute() throws NetworkException {

		check(_networkTime);
		Watcher watcher = new Watcher();
		watcher.timer(_watchTime * 1000);
		Fake network = new Fake();
		network.execute(_networkTime);
		cleanup();

	}

	private void check(int i) throws NetworkException {
		if(i < 0) {
			throw new NetworkException(KIND.TIME_CHECK_ERR, "マイナス値は入力できません。入力値：" + i);
		}
		if(99 < i) {
			throw new NetworkException(KIND.TIME_CHECK_ERR, "値が大きすぎます。99秒までしか対応していません。入力値：" + i);
		}
	}



	private void cleanup() {
		Result.setResult("0");
	}
}

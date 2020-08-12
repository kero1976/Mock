package hoge.mock2.api;

import hoge.mock2.api.exception.ApiException;
import hoge.mock2.api.exception.ApiException.KIND;

class Params {


	private int _networkTime;
	private int _timeOut;
	private boolean _mutuushinFlg;

	Params(String[] args) throws ApiException{
		if(args.length < 3) {
			throw  new ApiException(KIND.PARAM_SHORT, "パラメータの数" + args.length);
		}

		String networkTime = args[0];
		String timeOut = args[1];
		String mutuushinFlg = args[2];

		try {
			_networkTime = Integer.parseInt(networkTime);
		}catch(NumberFormatException e) {
			throw  new ApiException(KIND.PARAM_VAL_ERROR, "ネットワーク時間：" + networkTime);
		}

		try {
			_timeOut = Integer.parseInt(timeOut);
		}catch(NumberFormatException e) {
			throw  new ApiException(KIND.PARAM_VAL_ERROR, "タイムアウト時間：" + timeOut);
		}

		try {
			int val = Integer.parseInt(mutuushinFlg);
			if(val == 1) {
				_mutuushinFlg = true;
			}else {
				_mutuushinFlg = false;
			}
		}catch(NumberFormatException e) {
			throw  new ApiException(KIND.PARAM_VAL_ERROR, "無通信フラグ：" + mutuushinFlg);
		}
	}

	int getNetworkTime() {
		return _networkTime;
	}

	int getTimeOut() {
		return _timeOut;
	}

	boolean isMutuushin() {
		return _mutuushinFlg;
	}

	public String toString() {
		return "ネットワーク時間：" + getNetworkTime() + ", タイムアウト時間：" + getTimeOut() + "無通信フラグ："  + isMutuushin();
	}
}

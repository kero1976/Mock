package hoge.mock2.network.exception;

import hoge.mock2.common.exception.AppException;

public class NetworkException extends AppException {

	public enum KIND{
		TIME_CHECK_ERR,
		SLEEP_ERROR
	}

	private KIND _kind;
	private String _message;

	public NetworkException(KIND kind, String message) {
		this._kind = kind;
		this._message = message;
	}

	@Override
	public String getMessage() {
		switch(_kind) {
		case TIME_CHECK_ERR:
			return "時間の値が不正です。" + _message;
		case SLEEP_ERROR:
			return "ダミー通信中にエラーが発生しました" + _message;
		default:
			return "予期せぬエラーが発生";
		}
	}
}

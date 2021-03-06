package hoge.mock2.api.exception;

import hoge.mock2.common.exception.AppException;

public class ApiException extends AppException {

	public enum KIND{
		PARAM_SHORT,
		PARAM_VAL_ERROR,
		UI_ERROR,
		NETWORK_ERROR
	}

	private KIND _kind;
	private String _message;

	public ApiException(KIND kind, String message) {
		this._kind = kind;
		this._message = message;
	}

	@Override
	public String getMessage() {
		switch(_kind) {
		case PARAM_SHORT:
			return "【例外】パラメータの数が足りません。パラメータは、数値3個（「通信時間(秒)」、「タイムアウト(秒)」、「0:無通信OFF、1:無通信ON」）指定してください。" + _message;
		case PARAM_VAL_ERROR:
			return "【例外】パラメータの値が不正です。数値を入力してください。" + _message;
		case UI_ERROR:
			return "【例外】UIでエラーが発生しました。" + _message;
		case NETWORK_ERROR:
			return "【例外】NETWORKでエラーが発生しました。" + _message;
		default:
			return "【例外】予期せぬエラーが発生";
		}
	}

}

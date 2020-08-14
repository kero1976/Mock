package hoge.mock2.common.exception;

public class CommonException extends AppException {

	private String _msg;

	public CommonException(String msg) {
		this._msg = msg;
	}
	@Override
	public String getMessage() {
		return _msg;
	}

}

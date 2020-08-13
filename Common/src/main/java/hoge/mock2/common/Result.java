package hoge.mock2.common;

public class Result {

	private static String _result = "";

	private static String _viewData = "";

	private static boolean _isCancel = false;

	private static boolean _isTimeout = false;

	public static String getViewData() {
		return _viewData;
	}

	public static void setViewData(String _data) {
		Result._viewData = _data;
	}

	public static int getResult() {
		return Integer.parseInt(_result);
	}

	public static void setResult(String val) {
		_result = val;
	}

	public static void setCancel() {
		_isCancel = true;
		setResult("2");
	}

	public static boolean isCancel() {
		return _isCancel;
	}

	public static void setTimeout() {
		_isTimeout = true;
	}

	public static boolean isTimeout() {
		return _isTimeout;
	}
}

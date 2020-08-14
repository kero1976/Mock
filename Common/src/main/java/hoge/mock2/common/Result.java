package hoge.mock2.common;

import hoge.mock2.common.valueObject.ExitCode;

public class Result {

	private static ExitCode _result = ExitCode.UNKNOWN;

	private static String _viewData = "";

	private static boolean _isCancel = false;

	public static String getViewData() {
		return _viewData;
	}

	public static void setViewData(String _data) {
		Result._viewData = _data;
	}

	public static ExitCode getResult() {
		return _result;
	}

	public static void setResult(ExitCode val) {
		_result = val;
	}

	public static void setCancel() {
		_isCancel = true;
		setResult(ExitCode.CANCEL);
	}

	public static boolean isCancel() {
		return _isCancel;
	}

}

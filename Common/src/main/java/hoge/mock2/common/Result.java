package hoge.mock2.common;

public class Result {

	static String _result;

	static String _data = "-1";



	public static String getData() {
		return _data;
	}

	public static void setData(String _data) {
		Result._data = _data;
	}

	public static int getResult() {
		return Integer.parseInt(_result);
	}

	public static void setResult(String val) {
		_result = val;
	}

}

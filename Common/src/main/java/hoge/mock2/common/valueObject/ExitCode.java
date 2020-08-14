package hoge.mock2.common.valueObject;

public class ExitCode {

	public static final ExitCode OK = new ExitCode("0");
	public static final ExitCode TIMEOUT = new ExitCode("1");
	public static final ExitCode CANCEL = new ExitCode("2");
	public static final ExitCode UNKNOWN = new ExitCode("-1");

	private String _value;

	public ExitCode(String value) {
		this._value = value;
		try {
			Integer.parseInt(_value);
		}catch(Exception e) {

		}
	}

	public String getStringResult() {
		return _value;
	}

	public int getIntResult() {
		return Integer.parseInt(_value);
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExitCode result = (ExitCode) o;

        return _value == result._value;
	}
}

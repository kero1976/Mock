package hoge.mock2.common.valueObject;

import java.util.List;

public class Args {

	private List<String> list;

	public Args(List<String> list) {
		this.list = list;
	}

	public String[] getArray() {
		return list.toArray(new String[0]);
	}

	public List<String> getList() {
		return list;
	}
}

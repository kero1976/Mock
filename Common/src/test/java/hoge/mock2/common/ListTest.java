package hoge.mock2.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {

	@Test
	void test1() {
		List<String> data = new ArrayList<String>();
		data.add("A");
		data.add("B");
		data.add("C");

		assertEquals("ABC",convert(data));

	}


	private String convert(List<String> list) {
		return null;
	}
}

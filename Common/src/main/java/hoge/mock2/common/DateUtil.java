package hoge.mock2.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);

	private String s;
	private Date d;

	public DateUtil(String s) {
		this.s = s;
	}

	public void convert() throws ParseException {
		d = df1.parse(s);
	}
}

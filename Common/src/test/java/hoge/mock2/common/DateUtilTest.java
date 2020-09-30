package hoge.mock2.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

	@Test
	void コンストラクタのテスト_NULL() {

		DateUtil dateUtil = new DateUtil("Fri Sep 11 22:07:53 JST 2020");
		String s = "Fri Sep 11 22:07:53 JST 2020";;

		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
		   try {
			Date date = df.parse(s);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(dateFormat.format(date));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}

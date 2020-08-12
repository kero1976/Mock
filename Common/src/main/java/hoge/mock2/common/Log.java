package hoge.mock2.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

	public static void sysout(String msg) {



		System.out.print(getTime() + ":");
		System.out.print(String.format("%-30s", getClassName()));


		System.out.println(msg);
	}


	private static String getTime() {
		DateTimeFormatter dtformat1 =
				DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
		return dtformat1.format(LocalDateTime.now());
	}

	private static String getClassName() {
		StackTraceElement[] ste = new Throwable().getStackTrace();
		return ste[2].getClassName();
	}
}

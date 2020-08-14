package hoge.mock2.api;

import hoge.mock2.api.exception.ApiException;
import hoge.mock2.common.Log;

public class Main {

	public static void main(String[] args) {
		Log.sysout("アプリケーションを開始します");
		try {
			Params params = new Params(args);
			Log.sysout(params.toString());
			Execute exe = new Execute(params);
			Log.sysout("アプリケーションを終了します。終了コード：" + exe.start());
		} catch (ApiException e) {
			Log.sysout("アプリケーションが異常終了しました。"  + e.getMessage());
		}
	}
}

package hoge.mock2.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hoge.mock2.common.Log;
import hoge.mock2.common.Result;
import hoge.mock2.network.NetworkSample;
import hoge.mock2.network.exception.NetworkException;
import hoge.mock2.ui.View;

class Execute {

	private Params _params;

	Execute(Params params){
		this._params = params;
	}

	int start() {

		Log.sysout("画面を表示します");
		View view = new View();
	    ExecutorService executor = Executors.newSingleThreadExecutor();
	    executor.execute(new Runnable(){
	          public void run() {
	            try {
	        		view.open();
	            } catch (Exception e) {
	             System.out.println(e.toString());
	            }
	          }});

	    Log.sysout("通信を開始します");
		NetworkSample network = new NetworkSample(_params.getNetworkTime(), _params.getTimeOut());
		try {
			network.execute();
		    Log.sysout("通信が完了しました");
		    executor.execute(new Runnable(){
		          public void run() {
		            try {
		            	Log.sysout("画面を閉じる");
		        		view.close();
		        		Log.sysout("画面を閉じた");
		            } catch (Exception e) {
		             System.out.println(e.toString());
		            }
		          }});
		    Thread.sleep(1000);
		} catch (NetworkException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		Log.sysout("終了");
		return Result.getResult();
	}


}

package hoge.mock2.api;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.swt.widgets.Display;

import hoge.mock2.common.Log;
import hoge.mock2.common.Result;
import hoge.mock2.network.NetworkSample;
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
	    Future<Void> f = executor.submit(new Callable<Void>(){
	          public Void call() {
	        	  view.open();
	        	  return null;
	          }
	    });

	    Log.sysout("通信を開始します");
		NetworkSample network = new NetworkSample(_params.getNetworkTime(), _params.getTimeOut());
		try {
			network.execute();
		    Log.sysout("通信が完了しました");
		}catch(Exception e) {
			Log.sysout(e.toString());
		}

		if(f.isDone()) {
			Log.sysout("画面が正しく閉じられています。");

		}else {
			Log.sysout("画面が表示されているので終了します");
			Display.getDefault().syncExec(new Runnable() {
			    public void run() {
			     view.close();
			    }
			});
		}
		executor.shutdown();
		Log.sysout("終了");
		return Result.getResult();
	}


}

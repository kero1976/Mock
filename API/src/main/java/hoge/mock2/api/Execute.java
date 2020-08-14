package hoge.mock2.api;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import hoge.mock2.api.exception.ApiException;
import hoge.mock2.api.exception.ApiException.KIND;
import hoge.mock2.common.Log;
import hoge.mock2.network.NetworkSample;
import hoge.mock2.network.exception.NetworkException;
import hoge.mock2.ui.View;

class Execute {

	private Params _params;
	ExecutorService executor;

	Execute(Params params){
		this._params = params;
	}

	int start() throws ApiException {

		View view = new View();
	    Future<Void> f = UiOpen(view);

	    Log.sysout("通信を開始します");
		NetworkSample network = new NetworkSample(_params.getNetworkTime(), _params.getTimeOut(), _params.isMutuushin());
		try {
			int result = network.execute();
			// ネットワークの例外出力に時間がかかるので、少し待つ
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			Log.sysout("通信が終了しました");
			return result;
		}catch(NetworkException e) {
			Log.sysout(e.toString());
			throw new ApiException(KIND.NETWORK_ERROR, e.getMessage());
		}finally {

			Log.sysout("終了");
		}
	}

	private Future<Void> UiOpen(View view) {
		Log.sysout("画面を表示します");
	    executor = Executors.newSingleThreadExecutor();
	    return executor.submit(new Callable<Void>(){
	          public Void call() {
	        	  view.open();
	        	  return null;
	          }
	    });
	}
}

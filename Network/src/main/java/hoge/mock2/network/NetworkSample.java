package hoge.mock2.network;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import hoge.mock2.common.Log;
import hoge.mock2.common.Result;
import hoge.mock2.network.exception.NetworkException;
import hoge.mock2.network.exception.NetworkException.KIND;

public class NetworkSample {

	private int _networkTime;
	private int _watchTime;
	private boolean _mutuushinFlg;

	public NetworkSample(int networkTIme, int watchTime) {
		this._networkTime = networkTIme;
		this._watchTime = watchTime;
	}

	/**
	 *
	 * @throws NetworkException
	 */
	public int execute() throws NetworkException {

		check(_networkTime);

        ExecutorService taskExecutor = Executors.newFixedThreadPool(3);
        CompletionService<Void> taskCompletionService = new ExecutorCompletionService<Void>(taskExecutor);


        Future<Void> timer = taskCompletionService.submit(new Callable<Void>(){
	          public Void call() {
	            try {
	        		Watcher watcher = new Watcher();
	        		watcher.timer(_watchTime);
	            } catch (NetworkException e) {
	            	Log.sysout(e.getMessage());
	            }
	            return null;
	          }});

        Future<Void> network = taskCompletionService.submit(new Callable<Void>(){
	          public Void call() {
	            try {
	        		Fake network = new Fake();
	        		network.execute(_networkTime);
	            } catch (NetworkException e) {
	            	Log.sysout(e.getMessage());
	            }
	            return null;
	          }});

	    try {
	    	while(true) {
		    	//Log.sysout("pollを実行します");
		    	if(Result.isCancel()) {
		    		Log.sysout("通信処理を中断します");
		    		timer.cancel(true);
		    		network.cancel(true);
		    		break;
		    	}
				Future<Void> result = taskCompletionService.poll();
				if(result == null) {
					//Log.sysout("結果を取得できませんでした");
				}else {
					Log.sysout("通信かタイマーのどちらかが完了しました");
					try {
						int returnCode = Result.getResult();
						Log.sysout("リターンコードがセットされています。");
						timer.cancel(true);
						return returnCode;
					}catch(Exception e) {
						Log.sysout("リターンコードがセットされていませんでした");

					}
					break;
				}
		    	//Log.sysout("pollを実行しました");
		    	Thread.sleep(1000);
	    	}


		} catch (Exception e) {
			Log.sysout("メイン処理で例外が発生" + e.toString());
		}
	    taskExecutor.shutdown();
	    while(true) {
	    	if(taskExecutor.isShutdown()) {
	    		Log.sysout("スレッドが終了しました");
	    		break;
	    	}else {
	    		Log.sysout("スレッド終了待ち");
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.sysout("スレッド終了待ちで例外が発生");
				}
	    	}
	    }
		cleanup();
return 0;
	}

	private void check(int i) throws NetworkException {
		if(i < 0) {
			throw new NetworkException(KIND.TIME_CHECK_ERR, "マイナス値は入力できません。入力値：" + i);
		}
		if(99 < i) {
			throw new NetworkException(KIND.TIME_CHECK_ERR, "値が大きすぎます。99秒までしか対応していません。入力値：" + i);
		}
	}



	private void cleanup() {
		Log.sysout("通信の後処理");
	}
}

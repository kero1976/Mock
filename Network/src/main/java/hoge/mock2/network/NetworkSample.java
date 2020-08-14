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

	public NetworkSample(int networkTIme, int watchTime, boolean mutuushinFlg) {
		this._networkTime = networkTIme;
		this._watchTime = watchTime;
		this._mutuushinFlg = mutuushinFlg;
	}


	private Future<Void> threadTimer(CompletionService<Void> taskCompletionService){
        return taskCompletionService.submit(new Callable<Void>(){
	          public Void call() {
	            try {
	        		Watcher watcher = new Watcher();
	        		watcher.timer(_watchTime);
	            } catch (NetworkException e) {
	            	Log.sysout(e.getMessage());
	            }
	            return null;
	          }});
	}

	private Future<Void> threadNetwork(CompletionService<Void> taskCompletionService){
        return taskCompletionService.submit(new Callable<Void>(){
	          public Void call() {
	            try {
	        		Fake network = new Fake();
	        		network.execute(_networkTime);
	            } catch (NetworkException e) {
	            	Log.sysout(e.getMessage());
	            }
	            return null;
	          }});
	}

	private ExecutorService startThreadPool() {
		return Executors.newFixedThreadPool(3);
	}

	private void stopThreadPool(ExecutorService taskExecutor) {
	    taskExecutor.shutdown();
	    while(true) {
	    	if(taskExecutor.isShutdown()) {
	    		Log.sysout("スレッドが終了しました");
	    		return;
	    	}else {
	    		Log.sysout("スレッド終了待ち");
	    		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.sysout("スレッド終了待ちで例外が発生");
					return;
				}
	    	}
	    }
	}

	/**
	 *
	 * @throws NetworkException
	 */
	public int execute() throws NetworkException {

		check(_networkTime);

        ExecutorService taskExecutor = startThreadPool();

        CompletionService<Void> taskCompletionService = new ExecutorCompletionService<Void>(taskExecutor);

        Future<Void> timer = threadTimer(taskCompletionService);
        Future<Void> network = threadNetwork(taskCompletionService);

	    try {
	    	while(true) {
		    	if(Result.isCancel()) {
		    		Log.sysout("通信処理を中断します");
		    		timer.cancel(true);
		    		network.cancel(true);
		    		return Result.getResult();
		    	}
				Future<Void> result = taskCompletionService.poll();
				if(result == null) {
					Thread.sleep(1000);
				}else {
					Log.sysout("通信かタイマーのどちらかが完了しました");
					try {
						int returnCode = Result.getResult();
						Log.sysout("リターンコードがセットされています。");
						timer.cancel(true);
						return returnCode;
					}catch(Exception e) {
						Log.sysout("リターンコードがセットされていませんでした");
						if(_mutuushinFlg) {
							Log.sysout("無通信と判断し処理を終了します");
							network.cancel(true);
							return 1;
						}else {
							Log.sysout("無通信ではないので処理終了まで待機します。");
							while(!network.isDone()) {
								Thread.sleep(500);
						    	if(Result.isCancel()) {
						    		Log.sysout("通信処理を中断します");
						    		network.cancel(true);
						    		return Result.getResult();
						    	}
							}
							network.get();
							return 1;
						}
					}
				}
	    	}
		} catch (Exception e) {
			Log.sysout("メイン処理で例外が発生" + e.toString());
			throw new NetworkException(KIND.NETWORK_ERROR, e.toString());
		}finally {
		    stopThreadPool(taskExecutor);
			cleanup();
		}
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

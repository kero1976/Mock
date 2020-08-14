package hoge.mock2.common;

public class Lock {

	static Lock instance = null;
    public static synchronized Lock getInstace() {
        if (instance == null) {
            instance = new Lock();
        }
        return instance;
    }
}

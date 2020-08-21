package hoge.mock2.common.exception;

public class OtherException extends AppException {

	public enum ERR_KIND{
		/** NULL参照 */
		VALUE_IS_NULL2,

		/** ファイルが見つからない */
		FILE_NOT_FOUND2,

		/** 実行ファイルの起動に失敗 */
		FILE_EXECUTE_ERROR2,
	}

	@Override
	public String getMessage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	private ERR_KIND kind;

	public OtherException(ERR_KIND kind) {
		this.kind = kind;
	}


}

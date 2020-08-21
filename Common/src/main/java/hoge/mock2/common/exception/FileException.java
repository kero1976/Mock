package hoge.mock2.common.exception;

public class FileException extends AppException {

	public static enum ERR_KIND{
		/** NULL */
		VALUE_IS_NULL,

		/** ファイルが見つからない */
		FILE_NOT_FOUND,

		/** ファイルではなくてディレクトリ */
		VALUE_IS_DIR,

		/** 実行ファイルの起動に失敗 */
		FILE_EXECUTE_ERROR,

		/** セキュリティ例外 */
		SECURITY_ERROR,
	}

	@Override
	public String getMessage() {
		// TODO 自動生成されたメソッド・スタブ
		return kind + ":" + filePath;
	}

	private ERR_KIND kind;
	private String filePath;
	private Exception innerException;

	public FileException(ERR_KIND kind, String filePath) {
		this(kind, filePath, null);
	}

	public FileException(ERR_KIND kind, String filePath, Exception e) {
		this.kind = kind;
		this.filePath = filePath;
		this.innerException = e;
	}
}

package sqlService.issuetracker;

public class SqlUpdateFailException extends RuntimeException{
	public SqlUpdateFailException(String message) {
		super(message);
	}
	public SqlUpdateFailException(String message, Throwable cause) {
		super(message, cause);
	}
}

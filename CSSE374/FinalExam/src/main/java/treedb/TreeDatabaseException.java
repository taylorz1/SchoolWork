package treedb;

public class TreeDatabaseException extends Exception {
	private static final long serialVersionUID = 680741452428911383L;

	public TreeDatabaseException() {
	}

	public TreeDatabaseException(String message) {
		super(message);
	}

	public TreeDatabaseException(Throwable cause) {
		super(cause);
	}

	public TreeDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public TreeDatabaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

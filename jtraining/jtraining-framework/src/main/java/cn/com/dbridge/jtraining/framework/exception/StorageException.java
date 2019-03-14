package cn.com.dbridge.jtraining.framework.exception;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = -1946286419924186866L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

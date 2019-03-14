package cn.com.dbridge.jtraining.framework.exception;

public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = 6350433587985501056L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
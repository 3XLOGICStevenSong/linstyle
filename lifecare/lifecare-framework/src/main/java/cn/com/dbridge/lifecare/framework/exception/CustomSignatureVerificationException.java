package cn.com.dbridge.lifecare.framework.exception;

public class CustomSignatureVerificationException  extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9034642363743780070L;

	public CustomSignatureVerificationException(String msg) {
        super(msg);
    }

    public CustomSignatureVerificationException() {
        super();
    }
}

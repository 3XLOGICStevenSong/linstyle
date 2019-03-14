package cn.com.dbridge.lifecare.framework.exception;

public class CustomAuthException  extends RuntimeException {

	private static final long serialVersionUID = -5123615185100327391L;
	 public CustomAuthException(String msg) {
	        super(msg);
	    }

	    public CustomAuthException() {
	        super();
	    }
}

package cn.com.dbridge.jtraining.framework.exception;

public class CustomAuthException  extends RuntimeException {

	private static final long serialVersionUID = -5123615185100327391L;
	 public CustomAuthException(String msg) {
	        super(msg);
	    }

	    public CustomAuthException() {
	        super();
	    }
}

package com.djb.highway.framework.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String CAUSE_CAPTION = "Caused by: ";

	public BaseRuntimeException(String message, Throwable e) {
		super(message, e);
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(Throwable e) {
		super(e);
	}

	@Override
	public void printStackTrace(PrintStream ps) {
		if (getCause() == null) {
			super.printStackTrace(ps);
		} else {
			ps.println(this);
			StackTraceElement[] trace = getStackTrace();
			for (StackTraceElement traceElement : trace)
				ps.println("\tat " + traceElement);
			ps.println(CAUSE_CAPTION);
			getCause().printStackTrace();
		}
	}

	@Override
	public void printStackTrace(PrintWriter pw) {
		if (getCause() == null) {
			super.printStackTrace(pw);
		} else {
			pw.println(this);
			getCause().printStackTrace(pw);
		}
	}
}

package com.djb.ylt.framework.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseException extends Exception {
    private static final long serialVersionUID = 1L;
    private static final String CAUSE_CAPTION = "Caused by: ";

    public BaseException(String message, Throwable e) {
        super(message, e);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable e) {
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

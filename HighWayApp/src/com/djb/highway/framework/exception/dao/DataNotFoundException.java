package com.djb.highway.framework.exception.dao;

import com.djb.highway.framework.exception.BaseException;

public class DataNotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message, Throwable e) {
		super(message, e);
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable e) {
		super(e);
	}
}

package com.djb.highway.framework.exception.service;

import com.djb.highway.framework.exception.BaseRuntimeException;

public class BaseServiceRuntimeException extends BaseRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseServiceRuntimeException(String message, Throwable e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

	public BaseServiceRuntimeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BaseServiceRuntimeException(Throwable e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

}

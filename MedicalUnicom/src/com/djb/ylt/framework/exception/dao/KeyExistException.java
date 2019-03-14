package com.djb.ylt.framework.exception.dao;

import com.djb.ylt.framework.exception.BaseException;

public class KeyExistException extends BaseException {
    private static final long serialVersionUID = 1L;

    public KeyExistException(String message, Throwable e) {
        super(message, e);
    }

    public KeyExistException(String message) {
        super(message);
    }

    public KeyExistException(Throwable e) {
        super(e);
    }
}

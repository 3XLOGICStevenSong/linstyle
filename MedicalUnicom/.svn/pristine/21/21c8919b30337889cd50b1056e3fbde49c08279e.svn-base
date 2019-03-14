package com.djb.ylt.framework.log;

import org.aspectj.lang.JoinPoint;

public class LogInterceptor {
    protected final Logger logger = new Logger(this.getClass());

    public void doBefore(JoinPoint jp) {

        logger.info("method start ", jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
    }

    public void doAfter(JoinPoint jp) {
        logger.info("method end ", jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
    }

}
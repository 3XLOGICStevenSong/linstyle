/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : フレームワーク
 * Description        : システム例外クラス
 *
 * 2011/07/28      Wangjiquan
 *
 *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
package com.djb.ylt.framework.exception;

import java.lang.Exception;
import java.lang.Throwable;

/**
 * 
 * <H3>
 * システム例外クラス.</H3>
 * 
 * <PRE>
 * 詳細
 * </PRE>
 * 
 * @author Wangjiquan
 */
public class SystemException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3140932082133859900L;

    /** 標準エラーメッセージ（メッセージ未設定の場合に使用） */
    private static final String DEFAULT_MSG = "errors.common.system";

    private Throwable cause = null;

    /**
     * <PRE>
     * 概要：コンストラクタ.
     * 詳細：
     * </PRE>
     * 
     * @param message
     *            詳細メッセージ
     * @param cause
     *            　 原因
     */
    public SystemException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * <PRE>
     * 概要：コンストラクタ.
     * 詳細：
     * </PRE>
     * 
     * @param cause
     *            原因
     */
    public SystemException(Throwable cause) {
        super(DEFAULT_MSG);
    }

    /**
     * <PRE>
     * 概要：コンストラクタ.
     * 詳細：
     * </PRE>
     */
    public SystemException() {
        super();
    }

    /**
     * <H3>
     * 原因となった例外の取得.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 原因となった例外
     */
    public Throwable getCause() {
        return cause;
    }

}

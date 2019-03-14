package com.djb.ylt.framework.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.djb.ylt.framework.UserInfo;

/**
 * <P>
 * BaseLoggerのラッパークラス.先頭・最後尾に決まった文字列を出力するために使用.
 * </P>
 * 
 * 
 * @author Wangjiquan
 * 
 */
public class Logger {

    /**
     * ヘッダー、フッターの間に入れる仕切り文字
     */
    public static final String SPACER = "\t";

    /**
     * ヘッダー
     */
    private StringBuffer stbHeader = null;

    /**
     * クラス名
     */
    private String className = null;

    /**
     * ユーザー情報
     */
    private UserInfo userInfo = null;

    /**
     * 実際のログ出力を行うベースオブジェクト
     */
    private static BaseLogger basicLogger = null;

    /**
     * <P>
     * コンストラクタ<BR>
     * 
     */
    public Logger() {
        super();

        if (basicLogger == null) {
            basicLogger = new BaseLogger(Logger.class);
            // PropertyConfigurator.configure("log4j.properties");
        }
    }

    /**
     * <P>
     * コンストラクタ<BR>
     * 
     * @param classObj
     *            ログ出力を使用するクラス
     */
    public Logger(Class<?> classObj) {
        super();

        if (basicLogger == null) {
            basicLogger = new BaseLogger(Logger.class);
            // PropertyConfigurator.configure("log4j.properties");
        }
        // クラス名のセット
        if (classObj != null) {
            this.className = classObj.getName();
        }
    }

    /**
     * <H3>
     * コンストラクタ</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param classObj
     *            ログ出力を使用するクラス
     * @param userInfo
     *            ユーザー情報
     */
    public Logger(Class<?> classObj, UserInfo userInfo) {
        this(classObj);
        this.setUserInfo(userInfo);
    }

    /**
     * <P>
     * DEBUG優先度を使ってメッセージオブジェクトをログ出力します.
     * </P>
     * DEBUG優先度は、アプリケーションのデバッグでもっとも役立つ細粒度の情報イベントを指します.
     * 
     * @param methodName
     *            メソッド名
     * @param message
     *            ログに出力するメッセージ
     */
    public void debug(String methodName, Object message) {
        basicLogger.debug(this.formatLog(methodName, message));
    }

    /**
     * <P>
     * ERROR優先度を使ってメッセージオブジェクトをログ出力します.
     * </P>
     * ERROR優先度は、まだアプリケーションが起動しつづけられるようなエラーイベントを指します.
     * 
     * @param methodName
     *            メソッド名
     * @param e
     *            発生した例外
     */
    public void error(String methodName, Throwable e) {
        try {
            if (e != null) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);
                StringBuffer stb = new StringBuffer();

                stb.append(this.formatLog(methodName, stringWriter));
                // stb.append(stringWriter);

                basicLogger.error(stb.toString());
            } else {
                basicLogger.error("!!Throwable is null!!");
            }
        } catch (Exception ex) {
            basicLogger.error("!!Print Stack Trace Err!!");
            // e.printStackTrace();
        }
    }

    /**
     * <P>
     * ERROR優先度を使ってメッセージオブジェクトをログ出力します.
     * </P>
     * ERROR優先度は、まだアプリケーションが起動しつづけられるようなエラーイベントを指します.
     * 
     * @param methodName
     *            メソッド名
     * @param message
     *            ログに出力するメッセージ
     */
    public void error(String methodName, Object message) {
        basicLogger.error(this.formatLog(methodName, message));
    }

    /**
     * <P>
     * FATAL優先度を使ってメッセージオブジェクトをログ出力します.
     * </P>
     * FATAL優先度は、おそらくアプリケーションの停止を起こすようなまさにサーバのエラーイベントを指します.
     * 
     * @param methodName
     *            メソッド名
     * @param message
     *            ログに出力するメッセージ
     */
    public void fatal(String methodName, Object message) {
        basicLogger.fatal(this.formatLog(methodName, message));
    }

    /**
     * <P>
     * FATAL優先度を使ってメッセージオブジェクトをログ出力します.
     * </P>
     * FATAL優先度は、おそらくアプリケーションの停止を起こすようなまさにサーバのエラーイベントを指します.
     * 
     * @param methodName
     *            メソッド名
     * @param e
     *            発生した例外
     */
    public void fatal(String methodName, Throwable e) {
        try {
            if (e != null) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);
                StringBuffer stb = new StringBuffer();

                stb.append(this.formatLog(methodName, stringWriter));
                // stb.append(stringWriter);

                basicLogger.fatal(stb.toString());
            } else {
                basicLogger.fatal("!!Throwable is null!!");
            }
        } catch (Exception ex) {
            basicLogger.fatal("!!Print Stack Trace Err!!");
            // e.printStackTrace();
        }

    }

    /**
     * <P>
     * INFO優先度を使ってメッセージオブジェクトをログ出力します.
     * </P>
     * INFO優先度は、粗粒度レベルでアプリケーションの推移を強調する情報のメッセージを指します.
     * 
     * @param methodName
     *            メソッド名
     * @param message
     *            ログに出力するメッセージ
     */
    public void info(String methodName, Object message) {
        basicLogger.info(this.formatLog(methodName, message));
    }

    /**
     * <P>
     * WARN優先度を使ってメッセージオブジェクトをログ出力します.
     * </P>
     * WARN優先度は、潜在的に、有害な状況を指します.
     * 
     * @param methodName
     *            メソッド名
     * @param message
     *            ログに出力するメッセージ
     */
    public void warn(String methodName, Object message) {
        basicLogger.warn(this.formatLog(methodName, message));
    }

    /**
     * <P>
     * ログ出力整形処理.
     * </P>
     * 
     * <PRE>
     * メッセージ部分のみの整形です.
     * ログの全体的な構成はプロパティファイルにて設定しています.
     * </PRE>
     * 
     * @param methodName
     *            メソッド名
     * @param message
     *            メッセージ
     * @return String 整形後のログメッセージ
     */
    private String formatLog(String methodName, Object message) {
        StringBuffer buffer = new StringBuffer();

        if (this.stbHeader == null) {
            this.stbHeader = new StringBuffer();
            this.stbHeader.append(Logger.SPACER + Logger.SPACER + Logger.SPACER);
        }

        buffer.append(this.stbHeader);

        if (this.className != null) {
            buffer.append("[");
            buffer.append(this.className);
            buffer.append("#");
            buffer.append(methodName);
            buffer.append(getLineNumber());
            buffer.append("] ");
            buffer.append(Logger.SPACER);
        }

        if (message != null) {
            buffer.append(message.toString());
        }

        return buffer.toString();
    }

    /**
     * <H3>
     * ユーザー情報をセットする.</H3>
     * 
     * <PRE>
     * ユーザー情報は、ログのヘッダーとして出力されます.
     * </PRE>
     * 
     * @param userInfo
     *            ユーザー情報
     */
    public void setUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            this.userInfo = userInfo;

            this.stbHeader = new StringBuffer();
            this.stbHeader.append(Logger.SPACER);
            if (this.userInfo.getLngLoginTime() > 0) {
                this.stbHeader.append(this.userInfo.getLngLoginTime());
            }
            this.stbHeader.append(Logger.SPACER);

            if (this.userInfo.getStrLoginId() != null) {
                this.stbHeader.append(this.userInfo.getStrLoginId());
            }
            this.stbHeader.append(Logger.SPACER);
        }
    }

    /**
     * <H3>
     * ユーザー情報を取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return ユーザー情報
     */
    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    /**
     * 
     * @return 返回ソースの行号
     */
    private String getLineNumber() {
        String strLineNumber = "";
        try {
            StackTraceElement[] ste = new Throwable().getStackTrace();
            int length = ste.length;
            int steIndex = 0;
            steIndex = length >= 4 ? 3 : (length >= 3 ? 2 : (length >= 2 ? 1 : (length >= 1 ? 0 : 0)));
            // // System.out.println(steIndex);
            String strSte = ste[steIndex].toString();
            strLineNumber = strSte.substring(strSte.indexOf("("));
        } catch (Exception e) {
            strLineNumber = "";
        }
        return strLineNumber;
    }
}

package com.djb.ylt.framework.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 * <br>
 * ログを出力するユーティリティクラスです.<BR>
 * 
 * 設定ファイルより出力のコントロールを行えます.<BR>
 * コントロールは以下の関係で行われます.<BR>
 * DEBUG＜INFO＜WARN＜ERROR＜FATAL <BR>
 * 例えばあるカテゴリのpriorityがwarnの場合、debug,info指定の出力は優先度が低いので、出力されません.<BR>
 * warn,error,fatalはwarnより高いpriorityなので出力されます.<BR>
 * 設定ファイルはクラスパスで指定されたディレクトリに log4j.propertiesという名前で保存します.<BR>
 * <p>
 * <使用例><br>
 * <blockquote style="color:blue">
 * 
 * <pre>
 * {
 *     // ログ出力
 *     BaseLogger.debug(&quot;Debug&quot;);
 *     BaseLogger.info(&quot;Information&quot;);
 *     BaseLogger.warn(&quot;Warn&quot;);
 *     BaseLogger.error(&quot;Error&quot;);
 *     BaseLogger.fatal(&quot;Fatal&quot;);
 * }
 * 
 * </pre>
 * 
 * </blockquote>
 * 
 * @author Wangjiquan
 */

public class BaseLogger {

    private Logger logger = null;

    public BaseLogger(Class<?> classLogger) {
        super();
        this.logger = Logger.getLogger(classLogger);
    }

    public BaseLogger() {
        super();
    }

    /**
     * DEBUG優先度を使ってメッセージオブジェクトをログ出力します. <BR>
     * DEBUG優先度は、アプリケーションのデバッグでもっとも役立つ細粒度の情報イベントを指します.
     * 
     * @param strMessage
     *            ログに出力するメッセージ
     * @return なし
     */
    public void debug(Object strMessage) {
        this.logger.debug(strMessage);
    }

    /**
     * ERROR優先度を使ってメッセージオブジェクトをログ出力します.<BR>
     * ERROR優先度は、まだアプリケーションが起動しつづけられるようなエラーイベントを指します.
     * 
     * @param strMessage
     *            ログに出力するメッセージ
     * @return なし
     */
    public void error(Object strMessage) {
        this.logger.error(strMessage);
    }

    /**
     * ERROR優先度を使ってメッセージオブジェクトをログ出力します.<BR>
     * ERROR優先度は、まだアプリケーションが起動しつづけられるようなエラーイベントを指します.
     * 
     * @param e
     *            ログに出力するException
     * @return なし
     */
    public void error(Exception e) {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            String str = stringWriter.toString();
            this.logger.error(str);
        } catch (Exception ex) {
            // e.printStackTrace();
        }

    }

    /**
     * FATAL優先度を使ってメッセージオブジェクトをログ出力します.<BR>
     * FATAL優先度は、おそらくアプリケーションの停止を起こすようなまさにサーバのエラーイベントを指します.
     * 
     * @param strMessage
     *            ログに出力するメッセージ
     * @return なし
     */
    public void fatal(Object strMessage) {
        this.logger.fatal(strMessage);
    }

    /**
     * INFO優先度を使ってメッセージオブジェクトをログ出力します.<BR>
     * INFO優先度は、粗粒度レベルでアプリケーションの推移を強調する情報のメッセージを指します.
     * 
     * @param strMessage
     *            ログに出力するメッセージ
     * @return なし
     */
    public void info(Object strMessage) {
        this.logger.info(strMessage);
    }

    /**
     * WARN優先度を使ってメッセージオブジェクトをログ出力します.<BR>
     * WARN優先度は、潜在的に、有害な状況を指します.
     * 
     * @param strMessage
     *            ログに出力するメッセージ
     * @return なし
     */
    public void warn(Object strMessage) {
        this.logger.warn(strMessage);
    }

}

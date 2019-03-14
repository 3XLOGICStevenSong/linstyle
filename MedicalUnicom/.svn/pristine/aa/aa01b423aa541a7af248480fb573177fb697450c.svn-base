/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : フレームワーク
 * Description        : Action拡張クラス
 *
 * 2011/07/28      Wangjiquan
 *
 *+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

package com.djb.ylt.framework.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.EventDispatchAction;
import com.djb.ylt.common.util.Constants;
import com.djb.ylt.common.util.ResourceLocator;
import com.djb.ylt.framework.dto.BaseDTO;
import com.djb.ylt.framework.log.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <H3>Actionクラス実装用の抽象クラス.</H3>
 * 
 * <PRE>
 * 
 *  すべてのActionは、このクラスを継承し作成する。
 * 
 * 【処理の流れ】
 * 
 *  1.excuteメソッドの呼び出し。
 * 
 *  2.doProcessメソッドを呼び出し、各個別実装処理を実行する。 →正常終了 Forward先をリターンする。
 * 
 *  →例外がスローされた場合 エラー処理を行う。
 * 
 * 
 * </PRE>
 * 
 * @author Wangjiquan
 * @version 1.0 2011/07/28
 */
public class BaseAction extends EventDispatchAction {
    /**
     * Logger
     */
    protected final Logger logger = new Logger(this.getClass());

    // private boolean complete;

    /**
     * <H3>コンストラクタ.</H3>
     * 
     * <PRE>
     * 
     * ログ出力オブジェクトの生成
     * 
     * </PRE>
     */
    public BaseAction() {

    }

    /**
     * 
     * @param response
     *            The servlet response we are creating
     * @param dto
     *            BaseDTO
     * 
     * @exception Exception
     */
    public void toJson(HttpServletResponse response, Object dto) throws Exception {

        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        out.print(gson.toJson(dto));

    }

    /**
     * 
     * <H3>StackTraceをString型に変換.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param throwable
     *            変換するStackTrace
     * @return StackTrace
     */
    public String getStackTrace(Throwable throwable) {
        // 画面表示用のメッセージ格納

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        StringBuffer stb = new StringBuffer();
        stb.append(stringWriter);

        return stb.toString();

    }

    /**
     *
     */
    public void formMessages(BaseDTO dto, String key, Object[] args) {
        BaseDTO.MessageData messageData = dto.new MessageData(key, ResourceLocator.getI18NMessage(this, key, args));
        if (!dto.getErrorMessageList().contains(messageData)) {
            dto.getErrorMessageList().add(messageData);
        }
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // test
        long lngTime = new Date().getTime();

        String methodName = super.getMethodName(mapping, form, request, response, mapping.getParameter());

        BaseDTO dto = (BaseDTO) form;
        logger.debug(methodName, ">>" + methodName);
        try {
            ActionForward actionForward = super.execute(mapping, form, request, response);
            return actionForward;
        } catch (Exception e) {
            // 処理失敗
            dto.setErrFlg(true);
            logger.fatal(methodName, e);
            formMessages(dto, Constants.MSG_KEY_SYSTEM_ERROR, null);
            throw e;
        } finally {
            logger.debug(methodName, methodName + "<<");
            // test
            lngTime = (new Date().getTime()) - lngTime;
            logger.error(methodName, "<<[End:execute] ClassName:" + this.getClass().getName() + "经过时间[" + lngTime + "]毫秒");
        }

    }

}
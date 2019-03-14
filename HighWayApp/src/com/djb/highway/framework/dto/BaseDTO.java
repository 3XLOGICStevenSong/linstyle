/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : フレームワーク
 * Description        : Serviceで必要なデータを保持する
 *
 * 2011/07/28      Wangjiquan
 *
 *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

package com.djb.highway.framework.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * <H3>
 * Serviceで必要なデータを保持する.</H3>
 * 
 * <PRE>
 * 全てのDTOクラスは、このインターフェースを実装する.
 * </PRE>
 * 
 * @author Wangjiquan
 */
public class BaseDTO extends ActionForm {

    private static final long serialVersionUID = 1L;

    // is error flg
    private boolean errFlg;

    // event id
    private String methodName;
    
    // 遷移先画面ID
    private String forward;
    // event id
    private String submitField;
    
    // screen id
    private String screenId;

    // request url
    private String url;

    // key
    private String keyName;

    // 错误码：Code
    private String returnCode;

    // 数据来源：app ,h5
    private String requestBy;
    
    // メッセージリスト
    private List<MessageData> messageList = new ArrayList<MessageData>();

    // エラーメッセージリスト
    private List<MessageData> errorMessageList = new ArrayList<MessageData>();

    public boolean isErrFlg() {
        return errFlg;
    }

    public void setErrFlg(boolean errFlg) {
        this.errFlg = errFlg;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    /**
     *  遷移先画面IDの取得
     * @return 遷移先画面ID
     */
    public String getForward() {
        return forward;
    }

    /**
     * 遷移先画面IDの設定
     * @param forward 遷移先画面ID
     */
    public void setForward(String forward) {
        this.forward = forward;
    }
    
    public String getSubmitField() {
		return submitField;
	}

	public void setSubmitField(String submitField) {
		this.submitField = submitField;
	}

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    // メッセージリスト及びエラーメッセージリストのgetter, setter
    public List<MessageData> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageData> messageList) {
        this.messageList = messageList;
    }

    public List<MessageData> getErrorMessageList() {
        return errorMessageList;
    }

    public void setErrorMessageList(List<MessageData> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }
    
    // メッセージ用のインナークラス
    public class MessageData implements Serializable {
        private static final long serialVersionUID = 1L;

        public MessageData() {
        }

        public MessageData(String messageId, String messageContent) {
            this.messageId = messageId;
            this.messageContent = messageContent;
        }

        public MessageData(String messageId) {
            this.messageId = messageId;
        }

        private String messageId;
        private String messageContent;

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

    }
}

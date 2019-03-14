/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : フレームワーク
 * Description        : Serviceで必要なデータを保持する
 *
 * 2011/07/28      Wangjiquan
 *
 *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

package com.djb.ylt.framework.dto;

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

    // request url
    private String url;

    // key
    private String keyName;

    // 错误码：Code
    private String returnCode;
    private String submitField;
    public String getSubmitField() {
 		return submitField;
 	}

 	public void setSubmitField(String submitField) {
 		this.submitField = submitField;
 	}

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

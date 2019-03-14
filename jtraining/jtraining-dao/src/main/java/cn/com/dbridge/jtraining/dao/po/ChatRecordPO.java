package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
@Data
public class ChatRecordPO implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 6698836086835886446L;

    private String no;

    private String fromUserId;

    private String toUserId;

    private Byte signFlag;

    private String msg;

    private Timestamp insertDate;

    private String insertPerson;

    private Timestamp updateDate;

    private String updatePerson;

}
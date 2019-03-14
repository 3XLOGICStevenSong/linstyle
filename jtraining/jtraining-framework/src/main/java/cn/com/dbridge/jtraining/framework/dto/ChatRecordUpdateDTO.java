package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChatRecordUpdateDTO implements Serializable {
    private static final long serialVersionUID = -5828936656552222036L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 学员编号（关联用户信息表中的user_id）
     */
    private Integer studentId;

    /**
     * 教师编号
     */
    private String teacherId;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    /**
     * 创建人
     */
    private Integer insertPerson;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    private Integer updatePerson;

    /**
     * 聊天记录
     */
    private byte[] chatLog;

}
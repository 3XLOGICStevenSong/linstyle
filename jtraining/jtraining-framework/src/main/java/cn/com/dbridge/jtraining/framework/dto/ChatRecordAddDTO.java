package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChatRecordAddDTO implements Serializable {

    private static final long serialVersionUID = -1550498726519940855L;

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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getInsertPerson() {
        return insertPerson;
    }

    public void setInsertPerson(Integer insertPerson) {
        this.insertPerson = insertPerson;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(Integer updatePerson) {
        this.updatePerson = updatePerson;
    }

    public byte[] getChatLog() {
        return chatLog;
    }

    public void setChatLog(byte[] chatLog) {
        this.chatLog = chatLog;
    }

    @Override
    public String toString() {
        return "ChatRecordAddDTO [studentId=" + studentId + ", teacherId="
                + teacherId + ", insertTime=" + insertTime + ", insertPerson="
                + insertPerson + ", updateTime=" + updateTime
                + ", updatePerson=" + updatePerson + ", chatLog="
                + Arrays.toString(chatLog) + "]";
    }

}
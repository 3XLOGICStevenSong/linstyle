package com.djb.highway.carpool.entity;

import java.util.Date;

import com.djb.highway.framework.entity.PageModel;

public class CarpoolRouteEntity extends PageModel {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1525863000336301110L;

    private Integer cr_id;

    private Integer pr_id;

    private Integer dr_id;

    private String insure_id;

    private String lottery_id;

    private String aboard_longitude;

    private String aboard_latitude;

    private String debus_longitude;

    private String debus_latitude;

    private Date aboard_time;

    private Date debus_time;

    private String status_flg;

    private String memo;

    private Date insert_time;

    private Date update_time;

    public Integer getCr_id() {
        return cr_id;
    }

    public void setCr_id(Integer cr_id) {
        this.cr_id = cr_id;
    }

    public Integer getPr_id() {
        return pr_id;
    }

    public void setPr_id(Integer pr_id) {
        this.pr_id = pr_id;
    }

    public Integer getDr_id() {
        return dr_id;
    }

    public void setDr_id(Integer dr_id) {
        this.dr_id = dr_id;
    }

    public String getInsure_id() {
        return insure_id;
    }

    public void setInsure_id(String insure_id) {
        this.insure_id = insure_id;
    }

    public String getLottery_id() {
        return lottery_id;
    }

    public void setLottery_id(String lottery_id) {
        this.lottery_id = lottery_id;
    }

    public String getAboard_longitude() {
        return aboard_longitude;
    }

    public void setAboard_longitude(String aboard_longitude) {
        this.aboard_longitude = aboard_longitude;
    }

    public String getAboard_latitude() {
        return aboard_latitude;
    }

    public void setAboard_latitude(String aboard_latitude) {
        this.aboard_latitude = aboard_latitude;
    }

    public String getDebus_longitude() {
        return debus_longitude;
    }

    public void setDebus_longitude(String debus_longitude) {
        this.debus_longitude = debus_longitude;
    }

    public String getDebus_latitude() {
        return debus_latitude;
    }

    public void setDebus_latitude(String debus_latitude) {
        this.debus_latitude = debus_latitude;
    }

    public Date getAboard_time() {
        return aboard_time;
    }

    public void setAboard_time(Date aboard_time) {
        this.aboard_time = aboard_time;
    }

    public Date getDebus_time() {
        return debus_time;
    }

    public void setDebus_time(Date debus_time) {
        this.debus_time = debus_time;
    }

    public String getStatus_flg() {
        return status_flg;
    }

    public void setStatus_flg(String status_flg) {
        this.status_flg = status_flg;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CarpoolRouteEntity other = (CarpoolRouteEntity) that;
        return (this.getCr_id() == null ? other.getCr_id() == null : this.getCr_id().equals(other.getCr_id()))
                        && (this.getPr_id() == null ? other.getPr_id() == null : this.getPr_id().equals(other.getPr_id()))
                        && (this.getDr_id() == null ? other.getDr_id() == null : this.getDr_id().equals(other.getDr_id()))
                        && (this.getInsure_id() == null ? other.getInsure_id() == null : this.getInsure_id().equals(other.getInsure_id()))
                        && (this.getLottery_id() == null ? other.getLottery_id() == null : this.getLottery_id().equals(other.getLottery_id()))
                        && (this.getAboard_longitude() == null ? other.getAboard_longitude() == null : this.getAboard_longitude().equals(
                                        other.getAboard_longitude()))
                        && (this.getAboard_latitude() == null ? other.getAboard_latitude() == null : this.getAboard_latitude().equals(
                                        other.getAboard_latitude()))
                        && (this.getDebus_longitude() == null ? other.getDebus_longitude() == null : this.getDebus_longitude().equals(
                                        other.getDebus_longitude()))
                        && (this.getDebus_latitude() == null ? other.getDebus_latitude() == null : this.getDebus_latitude().equals(other.getDebus_latitude()))
                        && (this.getAboard_time() == null ? other.getAboard_time() == null : this.getAboard_time().equals(other.getAboard_time()))
                        && (this.getDebus_time() == null ? other.getDebus_time() == null : this.getDebus_time().equals(other.getDebus_time()))
                        && (this.getStatus_flg() == null ? other.getStatus_flg() == null : this.getStatus_flg().equals(other.getStatus_flg()))
                        && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
                        && (this.getInsert_time() == null ? other.getInsert_time() == null : this.getInsert_time().equals(other.getInsert_time()))
                        && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCr_id() == null) ? 0 : getCr_id().hashCode());
        result = prime * result + ((getPr_id() == null) ? 0 : getPr_id().hashCode());
        result = prime * result + ((getDr_id() == null) ? 0 : getDr_id().hashCode());
        result = prime * result + ((getInsure_id() == null) ? 0 : getInsure_id().hashCode());
        result = prime * result + ((getLottery_id() == null) ? 0 : getLottery_id().hashCode());
        result = prime * result + ((getAboard_longitude() == null) ? 0 : getAboard_longitude().hashCode());
        result = prime * result + ((getAboard_latitude() == null) ? 0 : getAboard_latitude().hashCode());
        result = prime * result + ((getDebus_longitude() == null) ? 0 : getDebus_longitude().hashCode());
        result = prime * result + ((getDebus_latitude() == null) ? 0 : getDebus_latitude().hashCode());
        result = prime * result + ((getAboard_time() == null) ? 0 : getAboard_time().hashCode());
        result = prime * result + ((getDebus_time() == null) ? 0 : getDebus_time().hashCode());
        result = prime * result + ((getStatus_flg() == null) ? 0 : getStatus_flg().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getInsert_time() == null) ? 0 : getInsert_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        return result;
    }
}
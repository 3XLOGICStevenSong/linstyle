package com.djb.ylt.medicine.entity;

import java.util.Date;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.user.entity.UserLoginEntity;



public class MedicineCollectionEntity extends PageModel {
    /**
     * 
     */
    private static final long serialVersionUID = -2073324617669088107L;

    private Integer collection_id;

    private Integer medicine_id;

    private Integer userId;

    private Date create_time;

    private Date last_modify_time;

    private Integer create_user;

    private Integer last_modify_user;

    private Integer version;
    private Integer user_id;

    private MedicineInfoEntity medicineInfoEntity;

    private UserLoginEntity userLoginEntity;

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
    }

    
   

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(Date last_modify_time) {
        this.last_modify_time = last_modify_time;
    }

    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }

    public Integer getLast_modify_user() {
        return last_modify_user;
    }

    public void setLast_modify_user(Integer last_modify_user) {
        this.last_modify_user = last_modify_user;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public MedicineInfoEntity getMedicineInfoEntity() {
        return medicineInfoEntity;
    }

    public void setMedicineInfoEntity(MedicineInfoEntity medicineInfoEntity) {
        this.medicineInfoEntity = medicineInfoEntity;
    }

    public UserLoginEntity getUserLoginEntity() {
        return userLoginEntity;
    }

    public void setUserLoginEntity(UserLoginEntity userLoginEntity) {
        this.userLoginEntity = userLoginEntity;
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
        MedicineCollectionEntity other = (MedicineCollectionEntity) that;
        return (this.getCollection_id() == null ? other.getCollection_id() == null : this.getCollection_id().equals(other.getCollection_id()))
                        && (this.getMedicine_id() == null ? other.getMedicine_id() == null : this.getMedicine_id().equals(other.getMedicine_id()))
                        && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                        && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                        && (this.getLast_modify_time() == null ? other.getLast_modify_time() == null : this.getLast_modify_time().equals(
                                        other.getLast_modify_time()))
                        && (this.getCreate_user() == null ? other.getCreate_user() == null : this.getCreate_user().equals(other.getCreate_user()))
                        && (this.getLast_modify_user() == null ? other.getLast_modify_user() == null : this.getLast_modify_user().equals(
                                        other.getLast_modify_user()))
                        && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCollection_id() == null) ? 0 : getCollection_id().hashCode());
        result = prime * result + ((getMedicine_id() == null) ? 0 : getMedicine_id().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getLast_modify_time() == null) ? 0 : getLast_modify_time().hashCode());
        result = prime * result + ((getCreate_user() == null) ? 0 : getCreate_user().hashCode());
        result = prime * result + ((getLast_modify_user() == null) ? 0 : getLast_modify_user().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

	/**
	 * 返回userId的值
	 * @return Integer userId的值
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置userId的值
	 * @param  userId userId的值
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 返回user_id的值
	 * @return Integer user_id的值
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * 设置user_id的值
	 * @param  user_id user_id的值
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
}
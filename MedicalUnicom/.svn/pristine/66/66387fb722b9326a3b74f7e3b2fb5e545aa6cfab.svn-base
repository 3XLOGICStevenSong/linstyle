package com.djb.ylt.medicine.entity;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.entity.PageModel;



public class PrescriptionMedicineEntity extends PageModel {

    

    /**
     * 
     */
    private static final long serialVersionUID = -7915599583508384795L;

    private Integer prescription_medicine_id;
    
    private Integer prescription_id;
    
    private Integer medicine_id;

    private Date create_time;

    private Date last_modify_time;

    private Integer create_user;

    private Integer last_modify_user;

    private Integer version;
    
    private String taking_method;
    
    private String  amount;
    
    private String  memo;
    
    private List<MedicineInfoEntity> medicineInfoEntitys;

    private PrescriptionInfoEntity prescriptionInfoEntity;

   

    public Integer getPrescription_medicine_id() {
        return prescription_medicine_id;
    }

    public void setPrescription_medicine_id(Integer prescription_medicine_id) {
        this.prescription_medicine_id = prescription_medicine_id;
    }

    public Integer getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Integer prescription_id) {
        this.prescription_id = prescription_id;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getTaking_method() {
        return taking_method;
    }

    public void setTaking_method(String taking_method) {
        this.taking_method = taking_method;
    }
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
        PrescriptionMedicineEntity other = (PrescriptionMedicineEntity) that;
        return (this.getPrescription_medicine_id() == null ? other.getPrescription_medicine_id() == null : this.getPrescription_medicine_id().equals(other.getPrescription_medicine_id()))
                        && (this.getPrescription_id() == null ? other.getPrescription_id() == null : this.getPrescription_id().equals(other.getPrescription_id()))
                        && (this.getMedicine_id() == null ? other.getMedicine_id() == null : this.getMedicine_id().equals(
                                        other.getMedicine_id()))
                        && (this.getTaking_method() == null ? other.getTaking_method() == null : this.getTaking_method().equals(
                                        other.getTaking_method()))
                        && (this.getMedicine_id() == null ? other.getMedicine_id() == null : this.getMedicine_id().equals(other.getMedicine_id()))
                        && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
                        && (this.getLast_modify_time() == null ? other.getLast_modify_time() == null : this.getLast_modify_time().equals(
                                        other.getLast_modify_time()))
                        && (this.getCreate_user() == null ? other.getCreate_user() == null : this.getCreate_user().equals(other.getCreate_user()))
                        && (this.getLast_modify_user() == null ? other.getLast_modify_user() == null : this.getLast_modify_user().equals(
                                        other.getLast_modify_user()))
                        && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
                         && (this.getTaking_method() == null ? other.getTaking_method() == null : this.getTaking_method().equals(other.getTaking_method()))
                          && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                           && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPrescription_medicine_id() == null) ? 0 : getPrescription_medicine_id().hashCode());
        result = prime * result + ((getPrescription_id() == null) ? 0 : getPrescription_id().hashCode());
        result = prime * result + ((getMedicine_id() == null) ? 0 : getMedicine_id().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getLast_modify_time() == null) ? 0 : getLast_modify_time().hashCode());
        result = prime * result + ((getCreate_user() == null) ? 0 : getCreate_user().hashCode());
        result = prime * result + ((getLast_modify_user() == null) ? 0 : getLast_modify_user().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getTaking_method() == null) ? 0 : getTaking_method().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 :getAmount().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 :getMemo().hashCode());
        return result;
    }

    public PrescriptionInfoEntity getPrescriptionInfoEntity() {
        return prescriptionInfoEntity;
    }

    public void setPrescriptionInfoEntity(PrescriptionInfoEntity prescriptionInfoEntity) {
        this.prescriptionInfoEntity = prescriptionInfoEntity;
    }

    public List<MedicineInfoEntity> getMedicineInfoEntitys() {
        return medicineInfoEntitys;
    }

    public void setMedicineInfoEntitys(List<MedicineInfoEntity> medicineInfoEntitys) {
        this.medicineInfoEntitys = medicineInfoEntitys;
    }

   
 
}
package com.djb.ylt.medicine.dto;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.dto.BaseDTO;
import com.djb.ylt.medicine.entity.PrescriptionInfoEntity;



public class PrescriptionMedicineDTO extends BaseDTO {

    /**
     * 
     */
    private static final long serialVersionUID = -779223920215876907L;

    private Integer prescription_medicine_id;

    private Integer prescription_id;

    private Integer medicine_id;

    private Date create_time;

    private Date last_modify_time;

    private Integer create_user;

    private Integer last_modify_user;

    private Integer version;

    private String taking_method;

    private String amount;
    
    private String memo;

    private List<MedicineInfoDTO> medicineInfoDTOs;

   private PrescriptionInfoEntity prescriptionInfoEntity;
    List<PrescriptionMedicineDTO> list;

    public List<PrescriptionMedicineDTO> getList() {
        return list;
    }

    public void setList(List<PrescriptionMedicineDTO> list) {
        this.list = list;
    }

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

    public String getTaking_method() {
        return taking_method;
    }

    public void setTaking_method(String taking_method) {
        this.taking_method = taking_method;
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

    public List<MedicineInfoDTO> getMedicineInfoDTOs() {
        return medicineInfoDTOs;
    }

    public void setMedicineInfoDTOs(List<MedicineInfoDTO> medicineInfoDTOs) {
        this.medicineInfoDTOs = medicineInfoDTOs;
    }

    public PrescriptionInfoEntity getPrescriptionInfoEntity() {
        return prescriptionInfoEntity;
    }

    public void setPrescriptionInfoEntity(PrescriptionInfoEntity prescriptionInfoEntity) {
        this.prescriptionInfoEntity = prescriptionInfoEntity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

   

}

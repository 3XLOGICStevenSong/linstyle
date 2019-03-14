package com.djb.ylt.medicine.dtoclient;

import com.djb.ylt.framework.dto.BaseClientDTO;

public class InspectionReportDetailDTO extends BaseClientDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 3537286404171766937L;

    private Integer inspection_report_id;

    private String inspection_report_type;

    private String report_department_name;

    private String inspection_report_status;

    private String report_content;

    private String medical_record_code;

    private Integer user_id;

    // 项目图片
    private String medical_record_image;
    // 看病时间
    private String medical_report_time;
    // 看病项目
    private String inspection_report_name;

    public Integer getInspection_report_id() {
        return inspection_report_id;
    }

    public void setInspection_report_id(Integer inspection_report_id) {
        this.inspection_report_id = inspection_report_id;
    }

    public String getInspection_report_type() {
        return inspection_report_type;
    }

    public void setInspection_report_type(String inspection_report_type) {
        this.inspection_report_type = inspection_report_type;
    }

    public String getReport_department_name() {
        return report_department_name;
    }

    public void setReport_department_name(String report_department_name) {
        this.report_department_name = report_department_name;
    }

    public String getInspection_report_status() {
        return inspection_report_status;
    }

    public void setInspection_report_status(String inspection_report_status) {
        this.inspection_report_status = inspection_report_status;
    }

    public String getReport_content() {
        return report_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    public String getMedical_record_code() {
        return medical_record_code;
    }

    public void setMedical_record_code(String medical_record_code) {
        this.medical_record_code = medical_record_code;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMedical_record_image() {
        return medical_record_image;
    }

    public void setMedical_record_image(String medical_record_image) {
        this.medical_record_image = medical_record_image;
    }

    public String getMedical_report_time() {
        return medical_report_time;
    }

    public void setMedical_report_time(String medical_report_time) {
        this.medical_report_time = medical_report_time;
    }

    public String getInspection_report_name() {
        return inspection_report_name;
    }

    public void setInspection_report_name(String inspection_report_name) {
        this.inspection_report_name = inspection_report_name;
    }

}
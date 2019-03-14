package com.djb.ylt.medicine.entity;

import java.util.Date;

import com.djb.ylt.framework.entity.PageModel;



public class InspectionReportEntity extends PageModel {

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
    private Date medical_report_time;
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

    public Date getMedical_report_time() {
        return medical_report_time;
    }

    public void setMedical_report_time(Date medical_report_time) {
        this.medical_report_time = medical_report_time;
    }

    public String getInspection_report_name() {
        return inspection_report_name;
    }

    public void setInspection_report_name(String inspection_report_name) {
        this.inspection_report_name = inspection_report_name;
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
        InspectionReportEntity other = (InspectionReportEntity) that;
        return (this.getInspection_report_id() == null ? other.getInspection_report_id() == null : this.getInspection_report_id().equals(
                        other.getInspection_report_id()))
                        && (this.getInspection_report_type() == null ? other.getInspection_report_type() == null : this.getInspection_report_type().equals(
                                        other.getInspection_report_type()))
                        && (this.getReport_department_name() == null ? other.getReport_department_name() == null : this.getReport_department_name().equals(
                                        other.getReport_department_name()))
                        && (this.getInspection_report_status() == null ? other.getInspection_report_status() == null : this.getInspection_report_status()
                                        .equals(other.getInspection_report_status()))
                        && (this.getReport_content() == null ? other.getReport_content() == null : this.getReport_content().equals(other.getReport_content()))
                        && (this.getMedical_record_code() == null ? other.getMedical_record_code() == null : this.getMedical_record_code().equals(
                                        other.getMedical_record_code()))
                        && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInspection_report_id() == null) ? 0 : getInspection_report_id().hashCode());
        result = prime * result + ((getInspection_report_type() == null) ? 0 : getInspection_report_type().hashCode());
        result = prime * result + ((getReport_department_name() == null) ? 0 : getReport_department_name().hashCode());
        result = prime * result + ((getInspection_report_status() == null) ? 0 : getInspection_report_status().hashCode());
        result = prime * result + ((getReport_content() == null) ? 0 : getReport_content().hashCode());
        result = prime * result + ((getMedical_record_code() == null) ? 0 : getMedical_record_code().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        return result;
    }
}
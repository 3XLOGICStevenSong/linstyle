package com.djb.highway.road.entity;

import com.djb.highway.framework.entity.PageModel;

public class TmServicesDetailsEntity extends PageModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5984363955192197863L;
    private Integer sd_id;
    private Integer service_id;

    private String details_num;

    private String details_name;
    private String details_pic;
    private String details_1;
    private String details_type;

    public Integer getSd_id() {
        return sd_id;
    }

    public void setSd_id(Integer sd_id) {
        this.sd_id = sd_id;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getDetails_num() {
        return details_num;
    }

    public void setDetails_num(String details_num) {
        this.details_num = details_num;
    }

    public String getDetails_name() {
        return details_name;
    }

    public void setDetails_name(String details_name) {
        this.details_name = details_name;
    }

    public String getDetails_pic() {
        return details_pic;
    }

    public void setDetails_pic(String details_pic) {
        this.details_pic = details_pic;
    }

    public String getDetails_1() {
        return details_1;
    }

    public void setDetails_1(String details_1) {
        this.details_1 = details_1;
    }

    public String getDetails_type() {
        return details_type;
    }

    public void setDetails_type(String details_type) {
        this.details_type = details_type;
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
        TmServicesDetailsEntity other = (TmServicesDetailsEntity) that;
        return (this.getSd_id() == null ? other.getSd_id() == null : this.getSd_id().equals(other.getSd_id()))
                        && (this.getService_id() == null ? other.getService_id() == null : this.getService_id().equals(other.getService_id()))
                        && (this.getDetails_num() == null ? other.getDetails_num() == null : this.getDetails_num().equals(other.getDetails_num()))
                        && (this.getDetails_name() == null ? other.getDetails_name() == null : this.getDetails_name().equals(other.getDetails_name()))
                        && (this.getDetails_pic() == null ? other.getDetails_pic() == null : this.getDetails_pic().equals(other.getDetails_pic()))
                        && (this.getDetails_1() == null ? other.getDetails_1() == null : this.getDetails_1().equals(other.getDetails_1()))
                        && (this.getDetails_type() == null ? other.getDetails_type() == null : this.getDetails_type().equals(other.getDetails_type()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSd_id() == null) ? 0 : getSd_id().hashCode());
        result = prime * result + ((getService_id() == null) ? 0 : getService_id().hashCode());
        result = prime * result + ((getDetails_num() == null) ? 0 : getDetails_num().hashCode());
        result = prime * result + ((getDetails_name() == null) ? 0 : getDetails_name().hashCode());
        result = prime * result + ((getDetails_pic() == null) ? 0 : getDetails_pic().hashCode());
        result = prime * result + ((getDetails_1() == null) ? 0 : getDetails_1().hashCode());
        result = prime * result + ((getDetails_type() == null) ? 0 : getDetails_type().hashCode());

        return result;
    }
}
package com.djb.highway.road.entity;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;

public class TmServicesEntity extends PageModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = -7314616262721970780L;

    private Integer id;
    private Integer sa_id;
    private String type;
    private String service_time;
    private String service_name;
    private String intro;
    private String tel;
    private String pic;
    private String introduction;

    private List<TmServicesDetailsEntity> tmServicesDetailsEntitys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSa_id() {
        return sa_id;
    }

    public void setSa_id(Integer sa_id) {
        this.sa_id = sa_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<TmServicesDetailsEntity> getTmServicesDetailsEntitys() {
        return tmServicesDetailsEntitys;
    }

    public void setTmServicesDetailsEntitys(List<TmServicesDetailsEntity> tmServicesDetailsEntitys) {
        this.tmServicesDetailsEntitys = tmServicesDetailsEntitys;
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
        TmServicesEntity other = (TmServicesEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                        && (this.getSa_id() == null ? other.getSa_id() == null : this.getSa_id().equals(other.getSa_id()))
                        && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                        && (this.getService_time() == null ? other.getService_time() == null : this.getService_time().equals(other.getService_time()))
                        && (this.getService_name() == null ? other.getService_name() == null : this.getService_name().equals(other.getService_name()))
                        && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
                        && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
                        && (this.getPic() == null ? other.getPic() == null : this.getPic().equals(other.getPic()))
                        && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSa_id() == null) ? 0 : getSa_id().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getService_time() == null) ? 0 : getService_time().hashCode());
        result = prime * result + ((getService_name() == null) ? 0 : getService_name().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getPic() == null) ? 0 : getPic().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());

        return result;
    }
}
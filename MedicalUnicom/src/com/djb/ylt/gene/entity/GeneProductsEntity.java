package com.djb.ylt.gene.entity;

import java.util.Date;

import com.djb.ylt.framework.entity.PageModel;

public class GeneProductsEntity extends PageModel {
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -3414025917133611825L;

	private Integer gpId;

    private String gpName;

    private String content;

    private String significance;

    private String simplePoint;

    private Double simplePrice;

    private String standardPoint;

    private Double standardPrice;

    private String productsType;

    private String status;

    private Date createTime;
    
    private String fitPeople;
     
    private Integer age;
    
    private Double  price;
    

    public Integer getGpId() {
        return gpId;
    }

    public void setGpId(Integer gpId) {
        this.gpId = gpId;
    }

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public String getSimplePoint() {
        return simplePoint;
    }

    public void setSimplePoint(String simplePoint) {
        this.simplePoint = simplePoint;
    }

    public Double getSimplePrice() {
        return simplePrice;
    }

    public void setSimplePrice(Double simplePrice) {
        this.simplePrice = simplePrice;
    }

    public String getStandardPoint() {
        return standardPoint;
    }

    public void setStandardPoint(String standardPoint) {
        this.standardPoint = standardPoint;
    }

    public Double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getProductsType() {
        return productsType;
    }

    public void setProductsType(String productsType) {
        this.productsType = productsType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        GeneProductsEntity other = (GeneProductsEntity) that;
        return (this.getGpId() == null ? other.getGpId() == null : this.getGpId().equals(other.getGpId()))
            && (this.getGpName() == null ? other.getGpName() == null : this.getGpName().equals(other.getGpName()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getSignificance() == null ? other.getSignificance() == null : this.getSignificance().equals(other.getSignificance()))
            && (this.getSimplePoint() == null ? other.getSimplePoint() == null : this.getSimplePoint().equals(other.getSimplePoint()))
            && (this.getSimplePrice() == null ? other.getSimplePrice() == null : this.getSimplePrice().equals(other.getSimplePrice()))
            && (this.getStandardPoint() == null ? other.getStandardPoint() == null : this.getStandardPoint().equals(other.getStandardPoint()))
            && (this.getStandardPrice() == null ? other.getStandardPrice() == null : this.getStandardPrice().equals(other.getStandardPrice()))
            && (this.getProductsType() == null ? other.getProductsType() == null : this.getProductsType().equals(other.getProductsType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGpId() == null) ? 0 : getGpId().hashCode());
        result = prime * result + ((getGpName() == null) ? 0 : getGpName().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getSignificance() == null) ? 0 : getSignificance().hashCode());
        result = prime * result + ((getSimplePoint() == null) ? 0 : getSimplePoint().hashCode());
        result = prime * result + ((getSimplePrice() == null) ? 0 : getSimplePrice().hashCode());
        result = prime * result + ((getStandardPoint() == null) ? 0 : getStandardPoint().hashCode());
        result = prime * result + ((getStandardPrice() == null) ? 0 : getStandardPrice().hashCode());
        result = prime * result + ((getProductsType() == null) ? 0 : getProductsType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

	/**
	 * 返回fitPeople的值
	 * @return String fitPeople的值
	 */
	public String getFitPeople() {
		return fitPeople;
	}

	/**
	 * 设置fitPeople的值
	 * @param  fitPeople fitPeople的值
	 */
	public void setFitPeople(String fitPeople) {
		this.fitPeople = fitPeople;
	}

	/**
	 * 返回age的值
	 * @return Integer age的值
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置age的值
	 * @param  age age的值
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 返回price的值
	 * @return Double price的值
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置price的值
	 * @param  price price的值
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
}
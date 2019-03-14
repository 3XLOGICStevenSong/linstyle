package com.djb.ylt.gene.dto;

import java.util.Date;

import com.djb.ylt.framework.dto.BaseDTO;

public class GeneProductsDTO extends BaseDTO {
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 3401868600483817525L;

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

}
package com.djb.ylt.gene.dtoclient;



import com.djb.ylt.framework.dto.BaseClientDTO;


public class GeneProductsClientDTO extends BaseClientDTO {


	private Integer gpId;

    private String gpName;

    private String content;

    private String significance;

    private String simplePoint;

    private String simplePrice;

    private String standardPoint;

    private String standardPrice;

    private String productsType;
    
    private String fitPeople;

    private String originalPrice;
    
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

    public String getSimplePrice() {
        return simplePrice;
    }

    public void setSimplePrice(String simplePrice) {
        this.simplePrice = simplePrice;
    }

    public String getStandardPoint() {
        return standardPoint;
    }

    public void setStandardPoint(String standardPoint) {
        this.standardPoint = standardPoint;
    }

    public String getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getProductsType() {
        return productsType;
    }

    public void setProductsType(String productsType) {
        this.productsType = productsType;
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
	 * 返回originalPrice的值
	 * @return String originalPrice的值
	 */
	public String getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * 设置originalPrice的值
	 * @param  originalPrice originalPrice的值
	 */
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

}
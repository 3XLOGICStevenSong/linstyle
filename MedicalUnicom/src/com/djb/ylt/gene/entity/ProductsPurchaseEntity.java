package com.djb.ylt.gene.entity;

import java.util.Date;

import com.djb.ylt.framework.entity.PageModel;

public class ProductsPurchaseEntity extends PageModel {
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = 485281610898934386L;

	private Long prId;

    private Integer gpId;

    private Integer patientId;

    private String receiveName;

    private String receiveTel;

    private String checkName;

    private String receiveAddress;

    private String payType;

    private String payStatus;

    private String orderStatus;

    private String orderNum;

    private Date payTime;

    private String payParam;

    private String refundParam;

    private Date buyTime;

    private String memo;

    private String refundReason;

    private Double payTotal;

    private Date refundTime;

    private Double refundTotal;

    private String payInfo;

    private String refundNum;
    
    private String buyType;
    
    private String sex;
    
    private String age;
    
    private String gpName;
    
    private String detectNum;
    
    
    private  GeneProductsEntity geneProductsEntity;
    
    private String  userCode;
    
    public Long getPrId() {
        return prId;
    }

    public void setPrId(Long prId) {
        this.prId = prId;
    }

    public Integer getGpId() {
        return gpId;
    }

    public void setGpId(Integer gpId) {
        this.gpId = gpId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveTel() {
        return receiveTel;
    }

    public void setReceiveTel(String receiveTel) {
        this.receiveTel = receiveTel;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayParam() {
        return payParam;
    }

    public void setPayParam(String payParam) {
        this.payParam = payParam;
    }

    public String getRefundParam() {
        return refundParam;
    }

    public void setRefundParam(String refundParam) {
        this.refundParam = refundParam;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Double getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Double payTotal) {
        this.payTotal = payTotal;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Double getRefundTotal() {
        return refundTotal;
    }

    public void setRefundTotal(Double refundTotal) {
        this.refundTotal = refundTotal;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(String refundNum) {
        this.refundNum = refundNum;
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
        ProductsPurchaseEntity other = (ProductsPurchaseEntity) that;
        return (this.getPrId() == null ? other.getPrId() == null : this.getPrId().equals(other.getPrId()))
            && (this.getGpId() == null ? other.getGpId() == null : this.getGpId().equals(other.getGpId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getReceiveName() == null ? other.getReceiveName() == null : this.getReceiveName().equals(other.getReceiveName()))
            && (this.getReceiveTel() == null ? other.getReceiveTel() == null : this.getReceiveTel().equals(other.getReceiveTel()))
            && (this.getCheckName() == null ? other.getCheckName() == null : this.getCheckName().equals(other.getCheckName()))
            && (this.getReceiveAddress() == null ? other.getReceiveAddress() == null : this.getReceiveAddress().equals(other.getReceiveAddress()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getPayParam() == null ? other.getPayParam() == null : this.getPayParam().equals(other.getPayParam()))
            && (this.getRefundParam() == null ? other.getRefundParam() == null : this.getRefundParam().equals(other.getRefundParam()))
            && (this.getBuyTime() == null ? other.getBuyTime() == null : this.getBuyTime().equals(other.getBuyTime()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getRefundReason() == null ? other.getRefundReason() == null : this.getRefundReason().equals(other.getRefundReason()))
            && (this.getPayTotal() == null ? other.getPayTotal() == null : this.getPayTotal().equals(other.getPayTotal()))
            && (this.getRefundTime() == null ? other.getRefundTime() == null : this.getRefundTime().equals(other.getRefundTime()))
            && (this.getRefundTotal() == null ? other.getRefundTotal() == null : this.getRefundTotal().equals(other.getRefundTotal()))
            && (this.getPayInfo() == null ? other.getPayInfo() == null : this.getPayInfo().equals(other.getPayInfo()))
            && (this.getRefundNum() == null ? other.getRefundNum() == null : this.getRefundNum().equals(other.getRefundNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPrId() == null) ? 0 : getPrId().hashCode());
        result = prime * result + ((getGpId() == null) ? 0 : getGpId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getReceiveName() == null) ? 0 : getReceiveName().hashCode());
        result = prime * result + ((getReceiveTel() == null) ? 0 : getReceiveTel().hashCode());
        result = prime * result + ((getCheckName() == null) ? 0 : getCheckName().hashCode());
        result = prime * result + ((getReceiveAddress() == null) ? 0 : getReceiveAddress().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getPayParam() == null) ? 0 : getPayParam().hashCode());
        result = prime * result + ((getRefundParam() == null) ? 0 : getRefundParam().hashCode());
        result = prime * result + ((getBuyTime() == null) ? 0 : getBuyTime().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getRefundReason() == null) ? 0 : getRefundReason().hashCode());
        result = prime * result + ((getPayTotal() == null) ? 0 : getPayTotal().hashCode());
        result = prime * result + ((getRefundTime() == null) ? 0 : getRefundTime().hashCode());
        result = prime * result + ((getRefundTotal() == null) ? 0 : getRefundTotal().hashCode());
        result = prime * result + ((getPayInfo() == null) ? 0 : getPayInfo().hashCode());
        result = prime * result + ((getRefundNum() == null) ? 0 : getRefundNum().hashCode());
        return result;
    }

	/**
	 * 返回buyType的值
	 * @return String buyType的值
	 */
	public String getBuyType() {
		return buyType;
	}

	/**
	 * 设置buyType的值
	 * @param  buyType buyType的值
	 */
	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	/**
	 * 返回sex的值
	 * @return String sex的值
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置sex的值
	 * @param  sex sex的值
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 返回geneProductsEntity的值
	 * @return GeneProductsEntity geneProductsEntity的值
	 */
	public GeneProductsEntity getGeneProductsEntity() {
		return geneProductsEntity;
	}

	/**
	 * 设置geneProductsEntity的值
	 * @param  geneProductsEntity geneProductsEntity的值
	 */
	public void setGeneProductsEntity(GeneProductsEntity geneProductsEntity) {
		this.geneProductsEntity = geneProductsEntity;
	}

	/**
	 * 返回age的值
	 * @return String age的值
	 */
	public String getAge() {
		return age;
	}

	/**
	 * 设置age的值
	 * @param  age age的值
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * 返回geneName的值
	 * @return String geneName的值
	 */
	public String getGpName() {
		return gpName;
	}

	/**
	 * 设置geneName的值
	 * @param  geneName geneName的值
	 */
	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	/**
	 * 返回detectNum的值
	 * @return String detectNum的值
	 */
	public String getDetectNum() {
		return detectNum;
	}

	/**
	 * 设置detectNum的值
	 * @param  detectNum detectNum的值
	 */
	public void setDetectNum(String detectNum) {
		this.detectNum = detectNum;
	}

	/**
	 * 返回userCode的值
	 * @return String userCode的值
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * 设置userCode的值
	 * @param  userCode userCode的值
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
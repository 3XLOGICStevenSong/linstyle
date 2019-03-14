package com.djb.ylt.gene.dto;

import java.util.Date;

import com.djb.ylt.framework.dto.PageDTO;



public class ProductsPurchaseDTO extends PageDTO  {
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -7987935197552280543L;

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

    private Long orderNum;

    private Date payTime;

    private String payParam;

    private String refundParam;

    private Date buyTime;

    private String memo;

    private String refundReason;

    private String payTotal;

    private Date refundTime;

    private Double refundTotal;

    private String payInfo;

    private Long refundNum;
    
    private String sex;
    
    private Integer age;
    
    private String  userCode;
    
    private String  gpName;

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

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
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

    public Long getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Long refundNum) {
        this.refundNum = refundNum;
    }

	/**
	 * 返回payTotal的值
	 * @return String payTotal的值
	 */
	public String getPayTotal() {
		return payTotal;
	}

	/**
	 * 设置payTotal的值
	 * @param  payTotal payTotal的值
	 */
	public void setPayTotal(String payTotal) {
		this.payTotal = payTotal;
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

	/**
	 * 返回geName的值
	 * @return String geName的值
	 */
	public String getGpName() {
		return gpName;
	}

	/**
	 * 设置geName的值
	 * @param  geName geName的值
	 */
	public void setGpName(String gpName) {
		this.gpName = gpName;
	}


}
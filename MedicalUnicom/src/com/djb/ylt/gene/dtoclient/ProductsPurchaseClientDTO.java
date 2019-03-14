package com.djb.ylt.gene.dtoclient;



import com.djb.ylt.framework.dto.BaseClientDTO;



public class ProductsPurchaseClientDTO extends BaseClientDTO  {

	private Long prId;

    private Integer gpId;

    private Integer patientId;

    private String geneName;

    private String buyTime;
    
    private String payStatus;

	/**
	 * 返回prId的值
	 * @return Long prId的值
	 */
	public Long getPrId() {
		return prId;
	}

	/**
	 * 设置prId的值
	 * @param  prId prId的值
	 */
	public void setPrId(Long prId) {
		this.prId = prId;
	}

	/**
	 * 返回gpId的值
	 * @return Integer gpId的值
	 */
	public Integer getGpId() {
		return gpId;
	}

	/**
	 * 设置gpId的值
	 * @param  gpId gpId的值
	 */
	public void setGpId(Integer gpId) {
		this.gpId = gpId;
	}

	/**
	 * 返回patientId的值
	 * @return Integer patientId的值
	 */
	public Integer getPatientId() {
		return patientId;
	}

	/**
	 * 设置patientId的值
	 * @param  patientId patientId的值
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	/**
	 * 返回geneName的值
	 * @return String geneName的值
	 */
	public String getGeneName() {
		return geneName;
	}

	/**
	 * 设置geneName的值
	 * @param  geneName geneName的值
	 */
	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	/**
	 * 返回buyTime的值
	 * @return String buyTime的值
	 */
	public String getBuyTime() {
		return buyTime;
	}

	/**
	 * 设置buyTime的值
	 * @param  buyTime buyTime的值
	 */
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	/**
	 * 返回payStatus的值
	 * @return String payStatus的值
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * 设置payStatus的值
	 * @param  payStatus payStatus的值
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

   
   
}
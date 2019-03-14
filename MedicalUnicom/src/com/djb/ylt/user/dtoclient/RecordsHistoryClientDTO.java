package com.djb.ylt.user.dtoclient;


import com.djb.ylt.framework.dto.BaseClientDTO;

public class RecordsHistoryClientDTO extends BaseClientDTO {



	
	private String analysis;
	
	private String guidance;
	
	private String detectedName;
	
	private String sex;
	
	private Integer age;

	private String detectTime;

	/**
	 * 返回analysis的值
	 * @return String analysis的值
	 */
	public String getAnalysis() {
		return analysis;
	}

	/**
	 * 设置analysis的值
	 * @param  analysis analysis的值
	 */
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	/**
	 * 返回guidance的值
	 * @return String guidance的值
	 */
	public String getGuidance() {
		return guidance;
	}

	/**
	 * 设置guidance的值
	 * @param  guidance guidance的值
	 */
	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}

	/**
	 * 返回detectedName的值
	 * @return String detectedName的值
	 */
	public String getDetectedName() {
		return detectedName;
	}

	/**
	 * 设置detectedName的值
	 * @param  detectedName detectedName的值
	 */
	public void setDetectedName(String detectedName) {
		this.detectedName = detectedName;
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
	 * @return String age的值
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
	 * 返回detectTime的值
	 * @return String detectTime的值
	 */
	public String getDetectTime() {
		return detectTime;
	}

	/**
	 * 设置detectTime的值
	 * @param  detectTime detectTime的值
	 */
	public void setDetectTime(String detectTime) {
		this.detectTime = detectTime;
	}
	
	
}
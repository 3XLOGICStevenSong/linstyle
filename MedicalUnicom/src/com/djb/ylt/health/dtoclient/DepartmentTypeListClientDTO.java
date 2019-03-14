package com.djb.ylt.health.dtoclient;


import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;




public class DepartmentTypeListClientDTO extends BaseClientDTO{

    private List<DepartmentTypeClientDTO> departmentTypeList;
    
    private String sysTime;

	/**
	 * 返回departmentTypeList的值
	 * @return List<DepartmentTypeClientDTO> departmentTypeList的值
	 */
	public List<DepartmentTypeClientDTO> getDepartmentTypeList() {
		return departmentTypeList;
	}

	/**
	 * 设置departmentTypeList的值
	 * @param  departmentTypeList departmentTypeList的值
	 */
	public void setDepartmentTypeList(List<DepartmentTypeClientDTO> departmentTypeList) {
		this.departmentTypeList = departmentTypeList;
	}

	/**
	 * 返回sysTime的值
	 * @return String sysTime的值
	 */
	public String getSysTime() {
		return sysTime;
	}

	/**
	 * 设置sysTime的值
	 * @param  sysTime sysTime的值
	 */
	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
    
    

}
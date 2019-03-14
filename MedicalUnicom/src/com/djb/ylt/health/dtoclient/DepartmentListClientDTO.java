package com.djb.ylt.health.dtoclient;


import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;
import com.djb.ylt.user.dtoclient.DoctorPackageClientDTO;



public class DepartmentListClientDTO extends BaseClientDTO{
	
	


    
    private List<DepartmentClientDTO> departmentList;
    
    private String sysTime;
    
    private List< DoctorPackageClientDTO> doctorList;

	/**
	 * 返回departmentList的值
	 * @return List<DepartmentClientDTO> departmentList的值
	 */
	public List<DepartmentClientDTO> getDepartmentList() {
		return departmentList;
	}

	/**
	 * 设置departmentList的值
	 * @param  departmentList departmentList的值
	 */
	public void setDepartmentList(List<DepartmentClientDTO> departmentList) {
		this.departmentList = departmentList;
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

	/**
	 * 返回doctorList的值
	 * @return List<DoctorPackageClientDTO> doctorList的值
	 */
	public List<DoctorPackageClientDTO> getDoctorList() {
		return doctorList;
	}

	/**
	 * 设置doctorList的值
	 * @param  doctorList doctorList的值
	 */
	public void setDoctorList(List<DoctorPackageClientDTO> doctorList) {
		this.doctorList = doctorList;
	}
    
  

    
}
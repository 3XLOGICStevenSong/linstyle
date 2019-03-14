package com.djb.ylt.health.dtoclient;



import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class DepartmentTypeClientDTO extends BaseClientDTO{
	


    private Integer dcId;

    private String dcName;

    private String iconPic;

    private String cellPic;

    private List<DepartmentClientDTO> departmentList;
    
    private String memo;

	/**
	 * 返回dcId的值
	 * @return Integer dcId的值
	 */
	public Integer getDcId() {
		return dcId;
	}

	/**
	 * 设置dcId的值
	 * @param  dcId dcId的值
	 */
	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}

	/**
	 * 返回dcName的值
	 * @return String dcName的值
	 */
	public String getDcName() {
		return dcName;
	}

	/**
	 * 设置dcName的值
	 * @param  dcName dcName的值
	 */
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	/**
	 * 返回iconPic的值
	 * @return String iconPic的值
	 */
	public String getIconPic() {
		return iconPic;
	}

	/**
	 * 设置iconPic的值
	 * @param  iconPic iconPic的值
	 */
	public void setIconPic(String iconPic) {
		this.iconPic = iconPic;
	}

	/**
	 * 返回cellPic的值
	 * @return String cellPic的值
	 */
	public String getCellPic() {
		return cellPic;
	}

	/**
	 * 设置cellPic的值
	 * @param  cellPic cellPic的值
	 */
	public void setCellPic(String cellPic) {
		this.cellPic = cellPic;
	}

	

	/**
	 * 返回memo的值
	 * @return String memo的值
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置memo的值
	 * @param  memo memo的值
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

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

 
}
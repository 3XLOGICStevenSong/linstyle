package com.djb.highway.road.entity;

import com.djb.highway.framework.entity.BaseEntity;

public class BaseTravelPlanEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3525041917439280346L;

	// 起点桩号
	private String start_stake_id;

	// 终点桩号
	private String end_stake_id;

	// 行驶路线收费站数组
	private String[] plazaCodeArgs;

	private String[] h_codeArgs;

	public String getStart_stake_id() {
		return start_stake_id;
	}

	public void setStart_stake_id(String start_stake_id) {
		this.start_stake_id = start_stake_id;
	}

	public String getEnd_stake_id() {
		return end_stake_id;
	}

	public void setEnd_stake_id(String end_stake_id) {
		this.end_stake_id = end_stake_id;
	}

	public String[] getPlazaCodeArgs() {
		return plazaCodeArgs;
	}

	public void setPlazaCodeArgs(String[] plazaCodeArgs) {
		this.plazaCodeArgs = plazaCodeArgs;
	}

	public String[] getH_codeArgs() {
		return h_codeArgs;
	}

	public void setH_codeArgs(String[] h_codeArgs) {
		this.h_codeArgs = h_codeArgs;
	}

}

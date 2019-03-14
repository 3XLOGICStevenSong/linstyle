package com.djb.highway.road.dto;

public class RoadInfoItemDTO {

	// 收费站ID
	private Integer plazId;

	// 收费站CODE
	private String plazCode;

	// 收费站桩号
	private String stickId;

	// test
	// 收费站名称
	private String plazName;

	// 收费站开通状态
	private Integer plazState;

	// 收费站天气信息
	private String weather;

	// 收费站所属的高速
	private String manageName;

	// private Left

	// 道路事件:只需要确认当前收费站涉及道路事件，有没有Flag;需要"路段CODE"或路段ID
	private int roadInfoLeft;

	// 服务区：只需要确认当前收费站涉及道路事件，有没有Flag;需要"路段CODE"或路段ID
	// 服务区：当开放时，才显示；为开放不显示
	private int serviceAreaLeft;

	// 车友分享：每个路段都有，需要"路段CODE"或路段ID,三种颜色：拥堵信息指数
	// 拥堵信息指数:1代表：绿色10条以下，2代表：橙色10-20条，3代表：红色20条以上
	private Integer carShareInfoFlagLeft;
	// 路段CODE:当前收费站Code+下一个收费站Code，例如：05210522
	// 使用路段CODE 替代 SectionID的作用
	private String sectionCodeLeft;

	// private Right

	// 道路事件:只需要确认当前收费站涉及道路事件，有没有Flag;需要"路段CODE"或路段ID
	private int roadInfoRight;
	// 服务区：只需要确认当前收费站涉及道路事件，有没有Flag;需要"路段CODE"或路段ID
	private int serviceAreaRight;

	// 车友分享：每个路段都有，需要"路段CODE"或路段ID,三种颜色：拥堵信息指数
	// 拥堵信息指数:1代表：绿色10条以下，2代表：橙色10-20条，3代表：红色20条以上
	private Integer carShareInfoFlagRight;
	// 路段CODE:当前收费站Code+下一个收费站Code，例如：05210522
	// 使用路段CODE 替代 SectionID的作用
	private String sectionCodeRight;

	public Integer getPlazId() {
		return plazId;
	}

	public void setPlazId(Integer plazId) {
		this.plazId = plazId;
	}

	public String getPlazCode() {
		return plazCode;
	}

	public void setPlazCode(String plazCode) {
		this.plazCode = plazCode;
	}

	public String getStickId() {
		return stickId;
	}

	public void setStickId(String stickId) {
		this.stickId = stickId;
	}

	public Integer getPlazState() {
		return plazState;
	}

	public void setPlazState(Integer plazState) {
		this.plazState = plazState;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public int getRoadInfoLeft() {
		return roadInfoLeft;
	}

	public void setRoadInfoLeft(int roadInfoLeft) {
		this.roadInfoLeft = roadInfoLeft;
	}

	public int getServiceAreaLeft() {
		return serviceAreaLeft;
	}

	public void setServiceAreaLeft(int serviceAreaLeft) {
		this.serviceAreaLeft = serviceAreaLeft;
	}

	public Integer getCarShareInfoFlagLeft() {
		return carShareInfoFlagLeft;
	}

	public void setCarShareInfoFlagLeft(Integer carShareInfoFlagLeft) {
		this.carShareInfoFlagLeft = carShareInfoFlagLeft;
	}

	public String getSectionCodeLeft() {
		return sectionCodeLeft;
	}

	public void setSectionCodeLeft(String sectionCodeLeft) {
		this.sectionCodeLeft = sectionCodeLeft;
	}

	public int getRoadInfoRight() {
		return roadInfoRight;
	}

	public void setRoadInfoRight(int roadInfoRight) {
		this.roadInfoRight = roadInfoRight;
	}

	public int getServiceAreaRight() {
		return serviceAreaRight;
	}

	public void setServiceAreaRight(int serviceAreaRight) {
		this.serviceAreaRight = serviceAreaRight;
	}

	public Integer getCarShareInfoFlagRight() {
		return carShareInfoFlagRight;
	}

	public void setCarShareInfoFlagRight(Integer carShareInfoFlagRight) {
		this.carShareInfoFlagRight = carShareInfoFlagRight;
	}

	public String getSectionCodeRight() {
		return sectionCodeRight;
	}

	public void setSectionCodeRight(String sectionCodeRight) {
		this.sectionCodeRight = sectionCodeRight;
	}

	public String getPlazName() {
		return plazName;
	}

	public void setPlazName(String plazName) {
		this.plazName = plazName;
	}

	public String getManageName() {
		return manageName;
	}

	public void setManageName(String manageName) {
		this.manageName = manageName;
	}

}

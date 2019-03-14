package example;


import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Report")
public class Report  extends Result  {

	private String reportId;

	private String longitude;

	private String latitude;

	private String status;

	private String memo;

	private String shop_location;

	private String shop_name;

	private String reason_area;

	private String reason_point;

	private String requirement_open;

	private String requirement_allday;

	private String requirement_wine;

	private String requirement_area;

	private String requirement_tobacco;

	private String requirement_tobacco_dis;

	private Integer requirement_competitor_count;

	private String road_oneway_startTime;

	private String road_oneway_endTime;

	private String road_noentry_startTime;

	private String road_noentry_endTime;

	private String road_nowait_startTime;

	private String road_nowait_endTime;

	private String road_nostop_startTime;

	private String road_nostop_endTime;

	private Integer traffic_car_11_positive;

	private Integer traffic_car_11_negative;

	private Integer traffic_car_12_positive;

	private Integer traffic_car_12_negative;

	private Integer traffic_car_13_positive;

	private Integer traffic_car_13_negative;

	private Integer traffic_car_14_positive;

	private Integer traffic_car_14_negative;

	private Integer traffic_car_21_positive;

	private Integer traffic_car_21_negative;

	private Integer traffic_car_22_positive;

	private Integer traffic_car_22_negative;

	private Integer traffic_car_23_positive;

	private Integer traffic_car_23_negative;

	private Integer traffic_car_24_positive;

	private Integer traffic_car_24_negative;

	private Integer traffic_car_31_positive;

	private Integer traffic_car_31_negative;

	private Integer traffic_car_32_positive;

	private Integer traffic_car_32_negative;

	private Integer traffic_car_33_positive;

	private Integer traffic_car_33_negative;

	private Integer traffic_car_34_positive;

	private Integer traffic_car_34_negative;

	private Integer traffic_car_41_positive;
	
	private Integer traffic_car_41_negative;

	private Integer traffic_car_42_positive;

	private Integer traffic_car_42_negative;

	private Integer traffic_car_43_positive;

	private Integer traffic_car_43_negative;

	private Integer traffic_car_44_positive;

	private Integer traffic_car_44_negative;

	private String pic_url_1;

	private String pic_url_2;

	private String pic_url_3;

	private String pic_url_4;

	private Integer create_id;

	private String create_time;

	private Integer update_id;

	private String update_time;
	
	//
	private String interviews;
	
	//方法标识
	
	private String methodFlag;
	
	//private  
	
	private String userId;
	
	private  List<Picture> chartList;
	
	private  List<Interview> interviewList;
	
	private String startDate;
	
	private String endDate;
	
	private String location;
	
	private String time;
	
	private String name;
	@DynamoDBHashKey(attributeName = "ReportId")
	
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	// ����
	@DynamoDBAttribute(attributeName = "Longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	// γ��
	@DynamoDBAttribute(attributeName = "Latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@DynamoDBAttribute(attributeName = "Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@DynamoDBAttribute(attributeName = "Memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	@DynamoDBAttribute(attributeName = "ShopLocation")
	public String getShop_location() {
		return shop_location;
	}

	public void setShop_location(String shop_location) {
		this.shop_location = shop_location;
	}
	@DynamoDBAttribute(attributeName = "ShopName")
	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	@DynamoDBAttribute(attributeName = "ReasonArea")
	public String getReason_area() {
		return reason_area;
	}

	public void setReason_area(String reason_area) {
		this.reason_area = reason_area;
	}
	@DynamoDBAttribute(attributeName = "ReasonPoint")
	public String getReason_point() {
		return reason_point;
	}

	public void setReason_point(String reason_point) {
		this.reason_point = reason_point;
	}
	@DynamoDBAttribute(attributeName = "RequirementOpen")
	public String getRequirement_open() {
		return requirement_open;
	}

	public void setRequirement_open(String requirement_open) {
		this.requirement_open = requirement_open;
	}
	@DynamoDBAttribute(attributeName = "RequirementAllday")
	public String getRequirement_allday() {
		return requirement_allday;
	}

	public void setRequirement_allday(String requirement_allday) {
		this.requirement_allday = requirement_allday;
	}
	@DynamoDBAttribute(attributeName = "RequirementWine")
	public String getRequirement_wine() {
		return requirement_wine;
	}

	public void setRequirement_wine(String requirement_wine) {
		this.requirement_wine = requirement_wine;
	}
	@DynamoDBAttribute(attributeName = "RequirementArea")
	public String getRequirement_area() {
		return requirement_area;
	}

	public void setRequirement_area(String requirement_area) {
		this.requirement_area = requirement_area;
	}
	@DynamoDBAttribute(attributeName = "RequirementTobacco")
	public String getRequirement_tobacco() {
		return requirement_tobacco;
	}

	public void setRequirement_tobacco(String requirement_tobacco) {
		this.requirement_tobacco = requirement_tobacco;
	}
	@DynamoDBAttribute(attributeName = "RequirementTobaccoDis")
	public String getRequirement_tobacco_dis() {
		return requirement_tobacco_dis;
	}

	public void setRequirement_tobacco_dis(String requirement_tobacco_dis) {
		this.requirement_tobacco_dis = requirement_tobacco_dis;
	}
	@DynamoDBAttribute(attributeName = "RequirementCompetitorCount")
	public Integer getRequirement_competitor_count() {
		return requirement_competitor_count;
	}

	public void setRequirement_competitor_count(Integer requirement_competitor_count) {
		this.requirement_competitor_count = requirement_competitor_count;
	}
	@DynamoDBAttribute(attributeName = "RoadOnewayStartTime")
	public String getRoad_oneway_startTime() {
		return road_oneway_startTime;
	}

	public void setRoad_oneway_startTime(String road_oneway_startTime) {
		this.road_oneway_startTime = road_oneway_startTime;
	}
	@DynamoDBAttribute(attributeName = "RoadOnewayEndTime")
	public String getRoad_oneway_endTime() {
		return road_oneway_endTime;
	}

	public void setRoad_oneway_endTime(String road_oneway_endTime) {
		this.road_oneway_endTime = road_oneway_endTime;
	}
	@DynamoDBAttribute(attributeName = "RoadNoentryStartTime")
	public String getRoad_noentry_startTime() {
		return road_noentry_startTime;
	}

	public void setRoad_noentry_startTime(String road_noentry_startTime) {
		this.road_noentry_startTime = road_noentry_startTime;
	}
	@DynamoDBAttribute(attributeName = "RoadNoentryEndTime")
	public String getRoad_noentry_endTime() {
		return road_noentry_endTime;
	}

	public void setRoad_noentry_endTime(String road_noentry_endTime) {
		this.road_noentry_endTime = road_noentry_endTime;
	}
	@DynamoDBAttribute(attributeName = "RoadNowaitStartTime")
	public String getRoad_nowait_startTime() {
		return road_nowait_startTime;
	}

	public void setRoad_nowait_startTime(String road_nowait_startTime) {
		this.road_nowait_startTime = road_nowait_startTime;
	}
	@DynamoDBAttribute(attributeName = "RoadNowaitEndTime")
	public String getRoad_nowait_endTime() {
		return road_nowait_endTime;
	}

	public void setRoad_nowait_endTime(String road_nowait_endTime) {
		this.road_nowait_endTime = road_nowait_endTime;
	}
	@DynamoDBAttribute(attributeName = "RoadNostopStartTime")
	public String getRoad_nostop_startTime() {
		return road_nostop_startTime;
	}

	public void setRoad_nostop_startTime(String road_nostop_startTime) {
		this.road_nostop_startTime = road_nostop_startTime;
	}
	@DynamoDBAttribute(attributeName = "RoadNostopEndTime")
	public String getRoad_nostop_endTime() {
		return road_nostop_endTime;
	}

	public void setRoad_nostop_endTime(String road_nostop_endTime) {
		this.road_nostop_endTime = road_nostop_endTime;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar11Positive")
	public Integer getTraffic_car_11_positive() {
		return traffic_car_11_positive;
	}

	public void setTraffic_car_11_positive(Integer traffic_car_11_positive) {
		this.traffic_car_11_positive = traffic_car_11_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar11Negative")
	public Integer getTraffic_car_11_negative() {
		return traffic_car_11_negative;
	}

	public void setTraffic_car_11_negative(Integer traffic_car_11_negative) {
		this.traffic_car_11_negative = traffic_car_11_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar12Positive")
	public Integer getTraffic_car_12_positive() {
		return traffic_car_12_positive;
	}

	public void setTraffic_car_12_positive(Integer traffic_car_12_positive) {
		this.traffic_car_12_positive = traffic_car_12_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar12Negative")
	public Integer getTraffic_car_12_negative() {
		return traffic_car_12_negative;
	}

	public void setTraffic_car_12_negative(Integer traffic_car_12_negative) {
		this.traffic_car_12_negative = traffic_car_12_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar13Positive")
	public Integer getTraffic_car_13_positive() {
		return traffic_car_13_positive;
	}

	public void setTraffic_car_13_positive(Integer traffic_car_13_positive) {
		this.traffic_car_13_positive = traffic_car_13_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar13Negative")
	public Integer getTraffic_car_13_negative() {
		return traffic_car_13_negative;
	}

	public void setTraffic_car_13_negative(Integer traffic_car_13_negative) {
		this.traffic_car_13_negative = traffic_car_13_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar14Positive")
	public Integer getTraffic_car_14_positive() {
		return traffic_car_14_positive;
	}

	public void setTraffic_car_14_positive(Integer traffic_car_14_positive) {
		this.traffic_car_14_positive = traffic_car_14_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar14Negative")
	public Integer getTraffic_car_14_negative() {
		return traffic_car_14_negative;
	}

	public void setTraffic_car_14_negative(Integer traffic_car_14_negative) {
		this.traffic_car_14_negative = traffic_car_14_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar21Positive")
	public Integer getTraffic_car_21_positive() {
		return traffic_car_21_positive;
	}

	public void setTraffic_car_21_positive(Integer traffic_car_21_positive) {
		this.traffic_car_21_positive = traffic_car_21_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar21Negative")
	public Integer getTraffic_car_21_negative() {
		return traffic_car_21_negative;
	}

	public void setTraffic_car_21_negative(Integer traffic_car_21_negative) {
		this.traffic_car_21_negative = traffic_car_21_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar22Positive")
	public Integer getTraffic_car_22_positive() {
		return traffic_car_22_positive;
	}

	public void setTraffic_car_22_positive(Integer traffic_car_22_positive) {
		this.traffic_car_22_positive = traffic_car_22_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar22Negative")
	public Integer getTraffic_car_22_negative() {
		return traffic_car_22_negative;
	}

	public void setTraffic_car_22_negative(Integer traffic_car_22_negative) {
		this.traffic_car_22_negative = traffic_car_22_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar23Positive")
	public Integer getTraffic_car_23_positive() {
		return traffic_car_23_positive;
	}

	public void setTraffic_car_23_positive(Integer traffic_car_23_positive) {
		this.traffic_car_23_positive = traffic_car_23_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar23Negative")
	public Integer getTraffic_car_23_negative() {
		return traffic_car_23_negative;
	}

	public void setTraffic_car_23_negative(Integer traffic_car_23_negative) {
		this.traffic_car_23_negative = traffic_car_23_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar24Positive")
	public Integer getTraffic_car_24_positive() {
		return traffic_car_24_positive;
	}

	public void setTraffic_car_24_positive(Integer traffic_car_24_positive) {
		this.traffic_car_24_positive = traffic_car_24_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar24Negative")
	public Integer getTraffic_car_24_negative() {
		return traffic_car_24_negative;
	}

	public void setTraffic_car_24_negative(Integer traffic_car_24_negative) {
		this.traffic_car_24_negative = traffic_car_24_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar31Positive")
	public Integer getTraffic_car_31_positive() {
		return traffic_car_31_positive;
	}

	public void setTraffic_car_31_positive(Integer traffic_car_31_positive) {
		this.traffic_car_31_positive = traffic_car_31_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar31Negative")
	public Integer getTraffic_car_31_negative() {
		return traffic_car_31_negative;
	}

	public void setTraffic_car_31_negative(Integer traffic_car_31_negative) {
		this.traffic_car_31_negative = traffic_car_31_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar32Positive")
	public Integer getTraffic_car_32_positive() {
		return traffic_car_32_positive;
	}

	public void setTraffic_car_32_positive(Integer traffic_car_32_positive) {
		this.traffic_car_32_positive = traffic_car_32_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar32Negative")
	public Integer getTraffic_car_32_negative() {
		return traffic_car_32_negative;
	}

	public void setTraffic_car_32_negative(Integer traffic_car_32_negative) {
		this.traffic_car_32_negative = traffic_car_32_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar33Positive")
	public Integer getTraffic_car_33_positive() {
		return traffic_car_33_positive;
	}

	public void setTraffic_car_33_positive(Integer traffic_car_33_positive) {
		this.traffic_car_33_positive = traffic_car_33_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar33Negative")
	public Integer getTraffic_car_33_negative() {
		return traffic_car_33_negative;
	}

	public void setTraffic_car_33_negative(Integer traffic_car_33_negative) {
		this.traffic_car_33_negative = traffic_car_33_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar34Positive")
	public Integer getTraffic_car_34_positive() {
		return traffic_car_34_positive;
	}

	public void setTraffic_car_34_positive(Integer traffic_car_34_positive) {
		this.traffic_car_34_positive = traffic_car_34_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar34Negative")
	public Integer getTraffic_car_34_negative() {
		return traffic_car_34_negative;
	}

	public void setTraffic_car_34_negative(Integer traffic_car_34_negative) {
		this.traffic_car_34_negative = traffic_car_34_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar41Positive")
	public Integer getTraffic_car_41_positive() {
		return traffic_car_41_positive;
	}

	public void setTraffic_car_41_positive(Integer traffic_car_41_positive) {
		this.traffic_car_41_positive = traffic_car_41_positive;
	}
	
	@DynamoDBAttribute(attributeName = "TrafficCar41Negative")
	public Integer getTraffic_car_41_negative() {
		return traffic_car_41_negative;
	}

	public void setTraffic_car_41_negative(Integer traffic_car_41_negative) {
		this.traffic_car_41_negative = traffic_car_41_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar42Positive")
	public Integer getTraffic_car_42_positive() {
		return traffic_car_42_positive;
	}

	public void setTraffic_car_42_positive(Integer traffic_car_42_positive) {
		this.traffic_car_42_positive = traffic_car_42_positive;
	}

	@DynamoDBAttribute(attributeName = "TrafficCar42Negative")
	public Integer getTraffic_car_42_negative() {
		return traffic_car_42_negative;
	}

	public void setTraffic_car_42_negative(Integer traffic_car_42_negative) {
		this.traffic_car_42_negative = traffic_car_42_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar43Positive")
	public Integer getTraffic_car_43_positive() {
		return traffic_car_43_positive;
	}

	public void setTraffic_car_43_positive(Integer traffic_car_43_positive) {
		this.traffic_car_43_positive = traffic_car_43_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar43Negative")
	public Integer getTraffic_car_43_negative() {
		return traffic_car_43_negative;
	}

	public void setTraffic_car_43_negative(Integer traffic_car_43_negative) {
		this.traffic_car_43_negative = traffic_car_43_negative;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar44Positive")
	public Integer getTraffic_car_44_positive() {
		return traffic_car_44_positive;
	}

	public void setTraffic_car_44_positive(Integer traffic_car_44_positive) {
		this.traffic_car_44_positive = traffic_car_44_positive;
	}
	@DynamoDBAttribute(attributeName = "TrafficCar44Negative")
	public Integer getTraffic_car_44_negative() {
		return traffic_car_44_negative;
	}

	public void setTraffic_car_44_negative(Integer traffic_car_44_negative) {
		this.traffic_car_44_negative = traffic_car_44_negative;
	}
	@DynamoDBAttribute(attributeName = "PicUrl1")
	public String getPic_url_1() {
		return pic_url_1;
	}

	public void setPic_url_1(String pic_url_1) {
		this.pic_url_1 = pic_url_1;
	}
	@DynamoDBAttribute(attributeName = "PicUrl2")
	public String getPic_url_2() {
		return pic_url_2;
	}

	public void setPic_url_2(String pic_url_2) {
		this.pic_url_2 = pic_url_2;
	}
	@DynamoDBAttribute(attributeName = "PicUrl3")
	public String getPic_url_3() {
		return pic_url_3;
	}

	public void setPic_url_3(String pic_url_3) {
		this.pic_url_3 = pic_url_3;
	}
	@DynamoDBAttribute(attributeName = "PicUrl4")
	public String getPic_url_4() {
		return pic_url_4;
	}

	public void setPic_url_4(String pic_url_4) {
		this.pic_url_4 = pic_url_4;
	}
	@DynamoDBAttribute(attributeName = "CreateId")
	public Integer getCreate_id() {
		return create_id;
	}

	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}
	@DynamoDBAttribute(attributeName = "CreateTime")
	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@DynamoDBAttribute(attributeName = "UpdateId")
	public Integer getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(Integer update_id) {
		this.update_id = update_id;
	}
	@DynamoDBAttribute(attributeName = "UpdateTime")
	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	@DynamoDBAttribute(attributeName = "Interviews")
	public String getInterviews() {
		return interviews;
	}

	public void setInterviews(String interviews) {
		this.interviews = interviews;
	}

	public String getMethodFlag() {
		return methodFlag;
	}

	public void setMethodFlag(String methodFlag) {
		this.methodFlag = methodFlag;
	}
	@DynamoDBAttribute(attributeName = "UserId")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Picture> getChartList() {
		return chartList;
	}

	public void setChartList(List<Picture> chartList) {
		this.chartList = chartList;
	}

	public List<Interview> getInterviewList() {
		return interviewList;
	}

	public void setInterviewList(List<Interview> interviewList) {
		this.interviewList = interviewList;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@DynamoDBAttribute(attributeName = "Location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@DynamoDBAttribute(attributeName = "Time")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@DynamoDBAttribute(attributeName = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

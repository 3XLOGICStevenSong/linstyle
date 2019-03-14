package com.djb.highway.carpool.dtoclient;

import com.djb.highway.road.dtoclient.BaseClientDTO;

import java.util.Date;

public class CarpoolUserClientDTO extends BaseClientDTO {

	private Integer cu_id;

	private String name;

	private String nickname;

	private String identity;

	private String drivinglicense;

	private String carlicense;

	private String people_license;

	private String tele;

	private String carbrand;

	private String cartype;

	private String carcolor;

	private String carnum;

	private String longitude;

	private String latitude;

	private String level;

	private Integer carseatnum;

	private Integer d_success_count;

	private Integer d_fail_count;

	private Integer p_success_count;

	private Integer p_fail_count;

	private Integer verify;

	private String memo;

	private Date insert_time;

	private Date update_time;
	
	private String create_time;
	
	private Integer successcount;
	
    private String verificationCode;

	public Integer getCu_id() {
		return cu_id;
	}

	public void setCu_id(Integer cu_id) {
		this.cu_id = cu_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getDrivinglicense() {
		return drivinglicense;
	}

	public void setDrivinglicense(String drivinglicense) {
		this.drivinglicense = drivinglicense;
	}

	public String getCarlicense() {
		return carlicense;
	}

	public void setCarlicense(String carlicense) {
		this.carlicense = carlicense;
	}

	public String getPeople_license() {
		return people_license;
	}

	public void setPeople_license(String people_license) {
		this.people_license = people_license;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getCarbrand() {
		return carbrand;
	}

	public void setCarbrand(String carbrand) {
		this.carbrand = carbrand;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getCarcolor() {
		return carcolor;
	}

	public void setCarcolor(String carcolor) {
		this.carcolor = carcolor;
	}

	public String getCarnum() {
		return carnum;
	}

	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getCarseatnum() {
		return carseatnum;
	}

	public void setCarseatnum(Integer carseatnum) {
		this.carseatnum = carseatnum;
	}

	public Integer getD_success_count() {
		return d_success_count;
	}

	public void setD_success_count(Integer d_success_count) {
		this.d_success_count = d_success_count;
	}

	public Integer getD_fail_count() {
		return d_fail_count;
	}

	public void setD_fail_count(Integer d_fail_count) {
		this.d_fail_count = d_fail_count;
	}

	public Integer getP_success_count() {
		return p_success_count;
	}

	public void setP_success_count(Integer p_success_count) {
		this.p_success_count = p_success_count;
	}

	public Integer getP_fail_count() {
		return p_fail_count;
	}

	public void setP_fail_count(Integer p_fail_count) {
		this.p_fail_count = p_fail_count;
	}

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getSuccesscount() {
        return successcount;
    }

    public void setSuccesscount(Integer successcount) {
        this.successcount = successcount;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

  

    
}
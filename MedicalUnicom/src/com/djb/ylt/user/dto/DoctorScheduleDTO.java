package com.djb.ylt.user.dto;

import java.util.Date;
import java.util.List;

import com.djb.ylt.framework.dto.BaseDTO;

public class DoctorScheduleDTO  extends BaseDTO {
	
	
    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -6877402365314926892L;

	private Integer scheduleId;

    private Integer doctorId;

    private String startDate;

  //  private String startTime;

    private String timeInterval;

    private String appointStatus;

    private Integer patientId;

    private String status;

    private Date createTime;

    private Date updateTieme;
    
    private List<DoctorScheduleDTO> scheduleDTOList;
    
    private String  scheduleList;
    
    private String endDate;
    
    private List<ScheduleDateDTO>  timeList;
    
    private List<ScheduleDateDTO> scheduleDateList;
    
	//private Integer patientNum;

	//private Integer appointNum;

	private Date endTime;
	
	private String[] startDateArgs;
	
	private String startDateList;
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

   
    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }


    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTieme() {
        return updateTieme;
    }

    public void setUpdateTieme(Date updateTieme) {
        this.updateTieme = updateTieme;
    }

	/**
	 * 返回appointStatus的值
	 * @return String appointStatus的值
	 */
	public String getAppointStatus() {
		return appointStatus;
	}

	/**
	 * 设置appointStatus的值
	 * @param  appointStatus appointStatus的值
	 */
	public void setAppointStatus(String appointStatus) {
		this.appointStatus = appointStatus;
	}

	/**
	 * 返回startDate的值
	 * @return String startDate的值
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 设置startDate的值
	 * @param  startDate startDate的值
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	/**
	 * 返回scheduleDTOList的值
	 * @return List<DoctorScheduleDTO> scheduleDTOList的值
	 */
	public List<DoctorScheduleDTO> getScheduleDTOList() {
		return scheduleDTOList;
	}

	/**
	 * 设置scheduleDTOList的值
	 * @param  scheduleDTOList scheduleDTOList的值
	 */
	public void setScheduleDTOList(List<DoctorScheduleDTO> scheduleDTOList) {
		this.scheduleDTOList = scheduleDTOList;
	}

	/**
	 * 返回scheduleList的值
	 * @return String scheduleList的值
	 */
	public String getScheduleList() {
		return scheduleList;
	}

	/**
	 * 设置scheduleList的值
	 * @param  scheduleList scheduleList的值
	 */
	public void setScheduleList(String scheduleList) {
		this.scheduleList = scheduleList;
	}

	/**
	 * 返回endDate的值
	 * @return String endDate的值
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * 设置endDate的值
	 * @param  endDate endDate的值
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	/**
	 * 返回scheduleDateList的值
	 * @return List<ScheduleDateDTO> scheduleDateList的值
	 */
	public List<ScheduleDateDTO> getScheduleDateList() {
		return scheduleDateList;
	}

	/**
	 * 设置scheduleDateList的值
	 * @param  scheduleDateList scheduleDateList的值
	 */
	public void setScheduleDateList(List<ScheduleDateDTO> scheduleDateList) {
		this.scheduleDateList = scheduleDateList;
	}

	

	/**
	 * 返回endTime的值
	 * @return Date endTime的值
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置endTime的值
	 * @param  endTime endTime的值
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 返回startDateArgs的值
	 * @return String[] startDateArgs的值
	 */
	public String[] getStartDateArgs() {
		return startDateArgs;
	}

	/**
	 * 设置startDateArgs的值
	 * @param  startDateArgs startDateArgs的值
	 */
	public void setStartDateArgs(String[] startDateArgs) {
		this.startDateArgs = startDateArgs;
	}

	/**
	 * 返回startDateList的值
	 * @return String startDateList的值
	 */
	public String getStartDateList() {
		return startDateList;
	}

	/**
	 * 设置startDateList的值
	 * @param  startDateList startDateList的值
	 */
	public void setStartDateList(String startDateList) {
		this.startDateList = startDateList;
	}

	/**
	 * 返回timeList的值
	 * @return List<ScheduleDateDTO> timeList的值
	 */
	public List<ScheduleDateDTO> getTimeList() {
		return timeList;
	}

	/**
	 * 设置timeList的值
	 * @param  timeList timeList的值
	 */
	public void setTimeList(List<ScheduleDateDTO> timeList) {
		this.timeList = timeList;
	}

	
	
}
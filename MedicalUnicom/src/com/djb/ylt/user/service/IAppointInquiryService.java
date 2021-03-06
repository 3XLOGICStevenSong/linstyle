package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.user.entity.AppointInquiryEntity;

public interface IAppointInquiryService {

	public void addAppointInquiry(AppointInquiryEntity AppointInquiry);

	public void deleteAppointInquiry(AppointInquiryEntity AppointInquiry);

	public void deleteAppointInquiryBatch(List<AppointInquiryEntity> list);

	public void updateAppointInquiry(AppointInquiryEntity AppointInquiry);

	public AppointInquiryEntity getObject(AppointInquiryEntity AppointInquiry);
	
	public AppointInquiryEntity getServiceCount(AppointInquiryEntity AppointInquiry);

	public List<AppointInquiryEntity> getAppointInquiryList();

	public List<AppointInquiryEntity> getAppointInquiryList(AppointInquiryEntity AppointInquiry);

	public List<AppointInquiryEntity> getPrivateDoctorList(AppointInquiryEntity AppointInquiry);

	public List<AppointInquiryEntity> getAppointListByPatient(AppointInquiryEntity AppointInquiry);

	public List<AppointInquiryEntity> getAppointListByDoctor(AppointInquiryEntity AppointInquiry);

	public List<AppointInquiryEntity> getMemberInfo(AppointInquiryEntity AppointInquiry);

	public List<AppointInquiryEntity> getAppointListByRecent(AppointInquiryEntity AppointInquiry);

	public void updateBatch(List<AppointInquiryEntity> AppointInquiry);

	public AppointInquiryEntity getInfoForPush(AppointInquiryEntity AppointInquiry);

	public List<AppointInquiryEntity> getInfoForMinutesPush();
	
	public List<AppointInquiryEntity> getDoctorMemberInfo(AppointInquiryEntity AppointInquiry);
	
	public List<AppointInquiryEntity> getDoctorMemberInfoForPage(AppointInquiryEntity AppointInquiry);
	
	public List<AppointInquiryEntity> getRecordsInfo(AppointInquiryEntity AppointInquiry);

	public List<AppointInquiryEntity> getBatchInfo(AppointInquiryEntity AppointInquiry);
	
	public List<AppointInquiryEntity>  getAppointListByStatus(AppointInquiryEntity AppointInquiry);
	
	public List<AppointInquiryEntity>  getAppointGraphicListByStatus(AppointInquiryEntity AppointInquiry);
	
	public List<AppointInquiryEntity>  getAppointListByImage(AppointInquiryEntity AppointInquiry);
	
	public AppointInquiryEntity getMonthRecords(AppointInquiryEntity AppointInquiry);
	
	public void appointGeneDoctor(AppointInquiryEntity AppointInquiry);
	
	public AppointInquiryEntity  getHistoryAppoint(AppointInquiryEntity AppointInquiry);
	
	public void addAppointPhotoInquiry(AppointInquiryEntity AppointInquiry);
	
	public AppointInquiryEntity  getGraphicList(AppointInquiryEntity AppointInquiry);
	
	public List<AppointInquiryEntity> findGraphicListByStatus(AppointInquiryEntity AppointInquiry);
	
	public AppointInquiryEntity  getAppointFree(AppointInquiryEntity AppointInquiry);
	
}

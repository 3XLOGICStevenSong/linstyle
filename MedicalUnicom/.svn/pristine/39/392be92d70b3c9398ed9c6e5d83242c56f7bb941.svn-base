package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IDoctorInqueryViewDao;
import com.djb.ylt.user.entity.DoctorInquiryViewEntity;


@Service("iDoctorInqueryViewService")
public class DoctorInqueryViewServiceImpl extends BaseService implements IDoctorInqueryViewService {

    @Autowired
    @Qualifier("doctorInqueryViewDao")
    private IDoctorInqueryViewDao doctorInqueryViewDao;


	@Override
	public DoctorInquiryViewEntity getObject(DoctorInquiryViewEntity DoctorInqueryView) {
		
		  return doctorInqueryViewDao.getObject(DoctorInqueryView);
		
	}

	@Override
	public List<DoctorInquiryViewEntity> getInqueryViewList() {
		
		  return (List<DoctorInquiryViewEntity>) doctorInqueryViewDao.findList();
	}

	@Override
	public List<DoctorInquiryViewEntity> getInqueryViewList(DoctorInquiryViewEntity doctorInqueryView) {
		
		 List<DoctorInquiryViewEntity> list = (List<DoctorInquiryViewEntity>)doctorInqueryViewDao.findListByCondition(doctorInqueryView);
	return list;
	}

	@Override
	public List<DoctorInquiryViewEntity> getAllPatient(DoctorInquiryViewEntity doctorInquiryViewEntity) {
		 List<DoctorInquiryViewEntity> list = (List<DoctorInquiryViewEntity>)doctorInqueryViewDao.findOtherList(IDoctorInqueryViewDao.FINDALLPATIENT, doctorInquiryViewEntity, DoctorInquiryViewEntity.class);
			return list;
	}

	@Override
	public List<DoctorInquiryViewEntity> findAllPatientForPage(DoctorInquiryViewEntity doctorInquiryViewEntity) {
		
		 List<DoctorInquiryViewEntity> list = (List<DoctorInquiryViewEntity>)doctorInqueryViewDao.findOtherList(IDoctorInqueryViewDao.FINDALLPATIENTFORPAGE, doctorInquiryViewEntity, DoctorInquiryViewEntity.class);
			return list;
	}

	@Override
	public List<DoctorInquiryViewEntity> getAllPatientForStatus(DoctorInquiryViewEntity doctorInquiryViewEntity) {
		
		 List<DoctorInquiryViewEntity> list = (List<DoctorInquiryViewEntity>)doctorInqueryViewDao.findOtherList(IDoctorInqueryViewDao.FINDALLPATIENTBYSTATUS, doctorInquiryViewEntity, DoctorInquiryViewEntity.class);
			return list;
	}


}

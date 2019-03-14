package com.djb.ylt.user.dao;

import com.djb.ylt.framework.dao.BaseDAO;
import com.djb.ylt.user.entity.DoctorInquiryViewEntity;

public interface IDoctorInqueryViewDao extends BaseDAO<DoctorInquiryViewEntity> {
	
	public static String FINDALLPATIENT="findAllPatient";
	public static String FINDALLPATIENTFORPAGE="findAllPatientForPage";
	public static String FINDALLPATIENTBYSTATUS="findAllPatientByStatus";
}

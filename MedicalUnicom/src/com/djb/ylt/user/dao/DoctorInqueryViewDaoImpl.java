package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.DoctorInquiryViewEntity;






@Repository("doctorInqueryViewDao")
public class DoctorInqueryViewDaoImpl extends BaseDAOImpl<DoctorInquiryViewEntity> implements
		IDoctorInqueryViewDao {
}

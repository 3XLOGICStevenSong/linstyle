package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.AppointInquiryEntity;





@Repository("appointInquiryDao")
public class AppointInquiryDaoImpl extends BaseDAOImpl<AppointInquiryEntity> implements
		IAppointInquiryDao {
}

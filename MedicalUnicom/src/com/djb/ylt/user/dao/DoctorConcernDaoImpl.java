package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.AppointInquiryEntity;
import com.djb.ylt.user.entity.DoctorCommentEntity;
import com.djb.ylt.user.entity.DoctorConcernEntity;




@Repository("doctorConcernDao")
public class DoctorConcernDaoImpl extends BaseDAOImpl<DoctorConcernEntity> implements
		IDoctorConcernDao {
}

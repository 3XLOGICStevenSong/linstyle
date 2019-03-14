package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.DoctorCommentEntity;




@Repository("doctorCommentDao")
public class DoctorCommentDaoImpl extends BaseDAOImpl<DoctorCommentEntity> implements
		IDoctorCommentDao {
}

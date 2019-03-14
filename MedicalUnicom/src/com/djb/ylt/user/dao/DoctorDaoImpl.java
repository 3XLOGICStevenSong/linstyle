package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.DoctorEntity;



@Repository("doctorDao")
public class DoctorDaoImpl extends BaseDAOImpl<DoctorEntity> implements
		IDoctorDao {
}

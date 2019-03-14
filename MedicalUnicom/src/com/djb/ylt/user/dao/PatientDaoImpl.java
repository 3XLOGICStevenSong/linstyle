package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.user.entity.PatientEntity;


@Repository("patientDao")
public class PatientDaoImpl extends BaseDAOImpl<PatientEntity> implements
		IPatientDao {
}

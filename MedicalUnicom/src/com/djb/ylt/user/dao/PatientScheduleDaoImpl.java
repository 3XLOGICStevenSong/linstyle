package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;


import com.djb.ylt.user.entity.PatientScheduleEntity;




@Repository("patientScheduleDao")
public class PatientScheduleDaoImpl extends BaseDAOImpl<PatientScheduleEntity> implements
		IPatientScheduleDao {
}

package com.djb.ylt.user.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;

import com.djb.ylt.user.entity.DepartmentDoctorEntity;





@Repository("departmentDoctorDao")
public class DepartmentDoctorDaoImpl extends BaseDAOImpl<DepartmentDoctorEntity> implements
		IDepartmentDoctorDao {
}

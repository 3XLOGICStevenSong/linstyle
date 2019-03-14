package com.djb.ylt.health.dao;

import org.springframework.stereotype.Repository;
import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.health.entity.DepartmentClassEntity;



@Repository("departmentClassDao")
public class DepartmentClassDaoImpl extends BaseDAOImpl<DepartmentClassEntity> implements
		IDepartmentClassDao {
}

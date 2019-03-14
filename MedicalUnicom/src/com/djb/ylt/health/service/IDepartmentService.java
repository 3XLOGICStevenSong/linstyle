package com.djb.ylt.health.service;

import java.util.List;

import com.djb.ylt.health.entity.DepartmentEntity;






public interface IDepartmentService {
	
    public void addDepartment(DepartmentEntity department);

    public void deleteDepartment(DepartmentEntity department);

    public void deleteDepartmentBatch(List<DepartmentEntity> list);

    public void updateDepartment(DepartmentEntity department);

    public DepartmentEntity getObject(DepartmentEntity department);

    public List<DepartmentEntity> getDepartmentList();

    public List<DepartmentEntity> getDepartmentList(DepartmentEntity department);

}

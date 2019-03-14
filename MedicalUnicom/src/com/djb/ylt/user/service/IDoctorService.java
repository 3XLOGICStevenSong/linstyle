package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.user.entity.DoctorEntity;


public interface IDoctorService {
	
    public void addDoctor(DoctorEntity doctor);

    public void deleteDoctor(DoctorEntity doctor);

    public void deleteDoctorBatch(List<DoctorEntity> list);

    public void updateDoctor(DoctorEntity doctor);

    public DoctorEntity getObject(DoctorEntity doctor);

    public List<DoctorEntity> getDoctorList();

    public List<DoctorEntity> getDoctorList(DoctorEntity doctor);

    public DoctorEntity getDoctorInfo(DoctorEntity doctor);
    
    public DoctorEntity getDoctorTel(DoctorEntity doctor);
    
    public List<DoctorEntity> getDoctorListByDepId(DoctorEntity doctor);
    
    public List<DoctorEntity> getAllDoctorList(DoctorEntity doctor);
    
    public DoctorEntity getDoctoForInquery(DoctorEntity doctor);
    
    public List<DoctorEntity> findListForGene(DoctorEntity doctor);
    
    public DoctorEntity getDoctorLoginInfo(DoctorEntity doctor);
    
}

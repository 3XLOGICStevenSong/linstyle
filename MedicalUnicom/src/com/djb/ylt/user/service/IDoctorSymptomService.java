package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.user.entity.DoctorSymptomEntity;


public interface IDoctorSymptomService {
	
    public void addDoctorSymptom(DoctorSymptomEntity doctorSymptom);

    public void deleteDoctorSymptom(DoctorSymptomEntity doctorSymptom);

    public void deleteDoctorSymptomBatch(List<DoctorSymptomEntity> list);

    public void updateDoctorSymptom(DoctorSymptomEntity doctorSymptom);

    public DoctorSymptomEntity getObject(DoctorSymptomEntity doctorSymptom);

    public List<DoctorSymptomEntity> getDoctorSymptomList();

    public List<DoctorSymptomEntity> getDoctorSymptomList(DoctorSymptomEntity doctorSymptom);

}

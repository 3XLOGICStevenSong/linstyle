package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.user.entity.PatientScheduleEntity;


public interface IPatientScheduleService {
	
    public void addPatientSchedule(PatientScheduleEntity PatientSchedule);

    public void deletePatientSchedule(PatientScheduleEntity PatientSchedule);

    public void deletePatientScheduleBatch(List<PatientScheduleEntity> list);

    public void updatePatientSchedule(PatientScheduleEntity PatientSchedule);

    public PatientScheduleEntity getObject(PatientScheduleEntity PatientSchedule);

    public List<PatientScheduleEntity> getPatientScheduleList();

    public List<PatientScheduleEntity> getPatientScheduleList(PatientScheduleEntity PatientSchedule);

    public void addPatientScheduleBatch(List<PatientScheduleEntity> PatientSchedule);
    
    public void updateBatch(List<PatientScheduleEntity> PatientSchedule);
}

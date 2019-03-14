package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.user.entity.PatientEntity;


public interface IPatientService {
    public void addPatient(PatientEntity patient);

    public void deletePatient(PatientEntity patient);

    public void deletePatientBatch(List<PatientEntity> list);

    public void updatePatient(PatientEntity patient);

    public PatientEntity getObject(PatientEntity patient);

    public List<PatientEntity> getPatientList();

    public List<PatientEntity> getPatientList(PatientEntity patient);

}

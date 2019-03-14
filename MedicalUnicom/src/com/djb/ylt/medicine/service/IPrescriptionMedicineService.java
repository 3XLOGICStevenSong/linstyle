package com.djb.ylt.medicine.service;

import java.util.List;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.medicine.entity.PrescriptionMedicineEntity;



public interface IPrescriptionMedicineService {
    public void addPrescriptionMedicine(PrescriptionMedicineEntity prescriptionMedicine);

    public void deletePrescriptionMedicine(PrescriptionMedicineEntity prescriptionMedicine);

    public void deletePrescriptionMedicineBatch(List<PrescriptionMedicineEntity> list);

    public void updatePrescriptionMedicine(PrescriptionMedicineEntity prescriptionMedicine);

    public PrescriptionMedicineEntity getObject(PrescriptionMedicineEntity prescriptionMedicine);

    public List<PrescriptionMedicineEntity> getPrescriptionMedicineList();

    public PageModel findPagedList(PrescriptionMedicineEntity prescriptionMedicine);

    public List<PrescriptionMedicineEntity> getPrescriptionMedicineList(PrescriptionMedicineEntity prescriptionMedicine);

}

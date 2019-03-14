package com.djb.ylt.medicine.service;

import java.util.List;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.medicine.entity.MedicineInfoEntity;

public interface IMedicineInfoService {
    public void addMedicineInfo(MedicineInfoEntity medicineInfo);

    public void deleteMedicineInfo(MedicineInfoEntity medicineInfo);

    public void deleteMedicineInfoBatch(List<MedicineInfoEntity> list);

    public void updateMedicineInfo(MedicineInfoEntity medicineInfo);

    public MedicineInfoEntity getObject(MedicineInfoEntity medicineInfo);

    public List<MedicineInfoEntity> getMedicineInfoList();

    public PageModel findPagedList(MedicineInfoEntity medicineInfo);

    public List<MedicineInfoEntity> getMedicineInfoList(MedicineInfoEntity medicineInfo);

    public MedicineInfoEntity getAverageGrade(MedicineInfoEntity medicineInfo);
}

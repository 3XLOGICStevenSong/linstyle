package com.djb.ylt.medicine.service;

import java.util.List;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.medicine.entity.MedicineCollectionEntity;

public interface IMedicineCollectionService {
    public void addMedicineCollection(MedicineCollectionEntity medicineCollection);

    public void deleteMedicineCollection(MedicineCollectionEntity medicineCollection);

    public void deleteMedicineCollectionBatch(List<MedicineCollectionEntity> list);

    public void updateMedicineCollection(MedicineCollectionEntity medicineCollection);

    public MedicineCollectionEntity getObject(MedicineCollectionEntity medicineCollection);

    public List<MedicineCollectionEntity> getMedicineCollectionList();

    public PageModel findPagedList(MedicineCollectionEntity medicineCollection);

    public List<MedicineCollectionEntity> getMedicineCollectionList(MedicineCollectionEntity medicineCollection);

}

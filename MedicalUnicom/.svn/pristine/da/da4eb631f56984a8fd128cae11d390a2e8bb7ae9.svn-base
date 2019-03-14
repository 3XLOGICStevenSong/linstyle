package com.djb.ylt.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IMedicineCollectionDao;
import com.djb.ylt.medicine.entity.MedicineCollectionEntity;

@Service("iMedicineCollectionService")
public class MedicineCollectionServiceImpl extends BaseService implements IMedicineCollectionService {

    @Autowired
    @Qualifier("medicineCollectionDao")
    private IMedicineCollectionDao medicineCollectionDao;

    @Override
    public void addMedicineCollection(MedicineCollectionEntity medicineCollection) {
        // TODO Auto-generated method stub
        try {
            medicineCollectionDao.insert(medicineCollection);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineCollection(MedicineCollectionEntity medicineCollection) {
        // TODO Auto-generated method stub
        try {
            medicineCollectionDao.delete(medicineCollection);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineCollectionBatch(List<MedicineCollectionEntity> list) {
        // TODO Auto-generated method stub
        try {
            medicineCollectionDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateMedicineCollection(MedicineCollectionEntity medicineCollection) {
        // TODO Auto-generated method stub
        try {
            medicineCollectionDao.update(medicineCollection);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public MedicineCollectionEntity getObject(MedicineCollectionEntity medicineCollection) {
        return medicineCollectionDao.getObject(medicineCollection);
    }

    @Override
    public List<MedicineCollectionEntity> getMedicineCollectionList() {
        // TODO Auto-generated method stub
        return (List<MedicineCollectionEntity>) medicineCollectionDao.findList();
    }

    @Override
    public List<MedicineCollectionEntity> getMedicineCollectionList(MedicineCollectionEntity medicineCollection) {
        List<MedicineCollectionEntity> list = (List<MedicineCollectionEntity>) medicineCollectionDao.findListByCondition(medicineCollection);
        return list;
    }

    @Override
    public PageModel findPagedList(MedicineCollectionEntity medicineCollection) {
        return null;
    }

}

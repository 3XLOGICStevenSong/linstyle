package com.djb.ylt.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IMedicineTypeDao;
import com.djb.ylt.medicine.entity.MedicineTypeEntity;

@Service("iMedicineTypeService")
public class MedicineTypeServiceImpl extends BaseService implements IMedicineTypeService {

    @Autowired
    @Qualifier("medicineTypeDao")
    private IMedicineTypeDao medicineTypeDao;

    @Override
    public void addMedicineType(MedicineTypeEntity medicineType) {
        // TODO Auto-generated method stub
        try {
            medicineTypeDao.insert(medicineType);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineType(MedicineTypeEntity medicineType) {
        // TODO Auto-generated method stub
        try {
            medicineTypeDao.delete(medicineType);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineTypeBatch(List<MedicineTypeEntity> list) {
        // TODO Auto-generated method stub
        try {
            medicineTypeDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateMedicineType(MedicineTypeEntity medicineType) {
        // TODO Auto-generated method stub
        try {
            medicineTypeDao.update(medicineType);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public MedicineTypeEntity getObject(MedicineTypeEntity medicineType) {
        return medicineTypeDao.getObject(medicineType);
    }

    @Override
    public List<MedicineTypeEntity> getMedicineTypeList() {
        // TODO Auto-generated method stub
        return (List<MedicineTypeEntity>) medicineTypeDao.findList();
    }

    @Override
    public PageModel findPagedList(MedicineTypeEntity medicineType) {
        return medicineTypeDao.findPagedList(medicineType);
    }

    @Override
    public List<MedicineTypeEntity> getMedicineTypeList(MedicineTypeEntity medicineType) {
        List<MedicineTypeEntity> list = (List<MedicineTypeEntity>) medicineTypeDao.findListByCondition(medicineType);
        return list;
    }

}

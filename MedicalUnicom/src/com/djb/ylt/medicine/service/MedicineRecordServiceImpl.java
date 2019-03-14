package com.djb.ylt.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IMedicineRecordDao;
import com.djb.ylt.medicine.entity.MedicalRecordEntity;

@Service("iMedicineRecordService")
public class MedicineRecordServiceImpl extends BaseService implements IMedicineRecordService {

    @Autowired
    @Qualifier("medicineRecordDao")
    private IMedicineRecordDao medicineRecordDao;

    @Override
    public void addMedicineRecord(MedicalRecordEntity medicineRecord) {
        // TODO Auto-generated method stub
        try {
            medicineRecordDao.insert(medicineRecord);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineRecord(MedicalRecordEntity medicineRecord) {
        // TODO Auto-generated method stub
        try {
            medicineRecordDao.delete(medicineRecord);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineRecordBatch(List<MedicalRecordEntity> list) {
        // TODO Auto-generated method stub
        try {
            medicineRecordDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateMedicineRecord(MedicalRecordEntity medicineRecord) {
        // TODO Auto-generated method stub
        try {
            medicineRecordDao.update(medicineRecord);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public MedicalRecordEntity getObject(MedicalRecordEntity medicineRecord) {
        return medicineRecordDao.getObject(medicineRecord);
    }

    @Override
    public List<MedicalRecordEntity> getMedicineRecordList() {
        // TODO Auto-generated method stub
        return (List<MedicalRecordEntity>) medicineRecordDao.findList();
    }

    @Override
    public PageModel findPagedList(MedicalRecordEntity medicineRecord) {
        return medicineRecordDao.findPagedList(medicineRecord);
    }

    @Override
    public List<MedicalRecordEntity> getMedicineRecordList(MedicalRecordEntity medicineRecord) {
        List<MedicalRecordEntity> list = (List<MedicalRecordEntity>) medicineRecordDao.findListByCondition(medicineRecord);
        return list;
    }

}

package com.djb.ylt.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IMedicineInfoDao;
import com.djb.ylt.medicine.entity.MedicineInfoEntity;

@Service("iMedicineInfoService")
public class MedicineInfoServiceImpl extends BaseService implements IMedicineInfoService {

    @Autowired
    @Qualifier("medicineInfoDao")
    private IMedicineInfoDao medicineInfoDao;

    @Override
    public void addMedicineInfo(MedicineInfoEntity medicineInfo) {
        // TODO Auto-generated method stub
        try {
            medicineInfoDao.insert(medicineInfo);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineInfo(MedicineInfoEntity medicineInfo) {
        // TODO Auto-generated method stub
        try {
            medicineInfoDao.delete(medicineInfo);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineInfoBatch(List<MedicineInfoEntity> list) {
        // TODO Auto-generated method stub
        try {
            medicineInfoDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateMedicineInfo(MedicineInfoEntity medicineInfo) {
        // TODO Auto-generated method stub
        try {
            medicineInfoDao.update(medicineInfo);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public MedicineInfoEntity getObject(MedicineInfoEntity medicineInfo) {
        return medicineInfoDao.getObject(medicineInfo);
    }

    @Override
    public List<MedicineInfoEntity> getMedicineInfoList() {
        // TODO Auto-generated method stub
        return (List<MedicineInfoEntity>) medicineInfoDao.findList();
    }

    @Override
    public PageModel findPagedList(MedicineInfoEntity medicineInfo) {
        return medicineInfoDao.findPagedList(medicineInfo);
    }

    @Override
    public List<MedicineInfoEntity> getMedicineInfoList(MedicineInfoEntity medicineInfo) {
        List<MedicineInfoEntity> list = (List<MedicineInfoEntity>) medicineInfoDao.findListByCondition(medicineInfo);
        return list;
    }

    @Override
    public MedicineInfoEntity getAverageGrade(MedicineInfoEntity medicineInfo) {
        return medicineInfoDao.getOtherObject(IMedicineInfoDao.GETAVERAGEGRADE, medicineInfo, MedicineInfoEntity.class);

    }

}

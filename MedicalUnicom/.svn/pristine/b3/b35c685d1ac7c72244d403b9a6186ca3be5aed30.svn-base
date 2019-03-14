package com.djb.ylt.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IMedicineAnswerDao;
import com.djb.ylt.medicine.entity.MedicineAnswerEntity;

@Service("iMedicineAnswerService")
public class MedicineAnswerServiceImpl extends BaseService implements IMedicineAnswerService {

    @Autowired
    @Qualifier("medicineAnswerDao")
    private IMedicineAnswerDao medicineAnswerDao;

    @Override
    public void addMedicineAnswer(MedicineAnswerEntity medicineAnswer) {
        // TODO Auto-generated method stub
        try {
            medicineAnswerDao.insert(medicineAnswer);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineAnswer(MedicineAnswerEntity medicineAnswer) {
        // TODO Auto-generated method stub
        try {
            medicineAnswerDao.delete(medicineAnswer);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineAnswerBatch(List<MedicineAnswerEntity> list) {
        // TODO Auto-generated method stub
        try {
            medicineAnswerDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateMedicineAnswer(MedicineAnswerEntity medicineAnswer) {
        // TODO Auto-generated method stub
        try {
            medicineAnswerDao.update(medicineAnswer);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public MedicineAnswerEntity getObject(MedicineAnswerEntity medicineAnswer) {
        return medicineAnswerDao.getObject(medicineAnswer);
    }

    @Override
    public List<MedicineAnswerEntity> getMedicineAnswerList() {
        // TODO Auto-generated method stub
        return (List<MedicineAnswerEntity>) medicineAnswerDao.findList();
    }

    @Override
    public List<MedicineAnswerEntity> getMedicineAnswerList(MedicineAnswerEntity medicineAnswer) {
        List<MedicineAnswerEntity> list = (List<MedicineAnswerEntity>) medicineAnswerDao.findListByCondition(medicineAnswer);
        return list;
    }

    @Override
    public PageModel findPagedList(MedicineAnswerEntity medicineAnswer) {
        return null;
    }

}

package com.djb.ylt.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IMedicineQuestionDao;
import com.djb.ylt.medicine.entity.MedicineQuestionEntity;

@Service("iMedicineQuestionService")
public class MedicineQuestionServiceImpl extends BaseService implements IMedicineQuestionService {

    @Autowired
    @Qualifier("medicineQuestionDao")
    private IMedicineQuestionDao medicineQuestionDao;

    @Override
    public void addMedicineQuestion(MedicineQuestionEntity medicineQuestion) {
        // TODO Auto-generated method stub
        try {
            medicineQuestionDao.insert(medicineQuestion);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineQuestion(MedicineQuestionEntity medicineQuestion) {
        // TODO Auto-generated method stub
        try {
            medicineQuestionDao.delete(medicineQuestion);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineQuestionBatch(List<MedicineQuestionEntity> list) {
        // TODO Auto-generated method stub
        try {
            medicineQuestionDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateMedicineQuestion(MedicineQuestionEntity medicineQuestion) {
        // TODO Auto-generated method stub
        try {
            medicineQuestionDao.update(medicineQuestion);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public MedicineQuestionEntity getObject(MedicineQuestionEntity medicineQuestion) {
        return medicineQuestionDao.getObject(medicineQuestion);
    }

    @Override
    public List<MedicineQuestionEntity> getMedicineQuestionList() {
        // TODO Auto-generated method stub
        return (List<MedicineQuestionEntity>) medicineQuestionDao.findList();
    }

    @Override
    public List<MedicineQuestionEntity> getMedicineQuestionList(MedicineQuestionEntity medicineQuestion) {
        List<MedicineQuestionEntity> list = (List<MedicineQuestionEntity>) medicineQuestionDao.findListByCondition(medicineQuestion);
        return list;
    }

    @Override
    public PageModel findPagedList(MedicineQuestionEntity medicineQuestion) {
        return null;
    }

}

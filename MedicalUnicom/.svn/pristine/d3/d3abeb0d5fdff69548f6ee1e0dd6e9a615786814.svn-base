package com.djb.ylt.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IMedicineCommentDao;
import com.djb.ylt.medicine.entity.MedicineCommentEntity;

@Service("iMedicineCommentService")
public class MedicineCommentServiceImpl extends BaseService implements IMedicineCommentService {

    @Autowired
    @Qualifier("medicineCommentDao")
    private IMedicineCommentDao medicineCommentDao;

    @Override
    public void addMedicineComment(MedicineCommentEntity medicineComment) {
        // TODO Auto-generated method stub
        try {
            medicineCommentDao.insert(medicineComment);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineComment(MedicineCommentEntity medicineComment) {
        // TODO Auto-generated method stub
        try {
            medicineCommentDao.delete(medicineComment);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicineCommentBatch(List<MedicineCommentEntity> list) {
        // TODO Auto-generated method stub
        try {
            medicineCommentDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void updateMedicineComment(MedicineCommentEntity medicineComment) {
        // TODO Auto-generated method stub
        try {
            medicineCommentDao.update(medicineComment);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public MedicineCommentEntity getObject(MedicineCommentEntity medicineComment) {
        return medicineCommentDao.getObject(medicineComment);
    }

    @Override
    public List<MedicineCommentEntity> getMedicineCommentList() {
        // TODO Auto-generated method stub
        return (List<MedicineCommentEntity>) medicineCommentDao.findList();
    }

    @Override
    public List<MedicineCommentEntity> getMedicineCommentList(MedicineCommentEntity medicineComment) {
        List<MedicineCommentEntity> list = (List<MedicineCommentEntity>) medicineCommentDao.findListByCondition(medicineComment);
        return list;
    }

    @Override
    public PageModel findPagedList(MedicineCommentEntity medicineComment) {
        return null;
    }

}

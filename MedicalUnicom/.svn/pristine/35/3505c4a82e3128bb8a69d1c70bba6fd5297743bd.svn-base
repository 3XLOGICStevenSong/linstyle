package com.djb.ylt.medicine.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IPrescriptionInfoDao;
import com.djb.ylt.medicine.entity.PrescriptionInfoEntity;




@Service("iPrescriptionInfoService")
public class PrescriptionInfoServiceImpl extends BaseService implements IPrescriptionInfoService {

    @Autowired
    @Qualifier("prescriptionInfoDao")
    private IPrescriptionInfoDao prescriptionInfoDao;

    @Override
    public void addPrescriptionInfo(PrescriptionInfoEntity prescriptionInfo) {
        // TODO Auto-generated method stub
        try {
            prescriptionInfoDao.insert(prescriptionInfo);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deletePrescriptionInfo(PrescriptionInfoEntity prescriptionInfo) {
        // TODO Auto-generated method stub
        try {
            prescriptionInfoDao.delete(prescriptionInfo);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

 

    @Override
    public void updatePrescriptionInfo(PrescriptionInfoEntity prescriptionInfo) {
        // TODO Auto-generated method stub
        try {
            prescriptionInfoDao.update(prescriptionInfo);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public PrescriptionInfoEntity getObject(PrescriptionInfoEntity prescriptionInfo) {
        return prescriptionInfoDao.getObject(prescriptionInfo);
    }

    @Override
    public List<PrescriptionInfoEntity> getPrescriptionInfoList() {
        // TODO Auto-generated method stub
        return (List<PrescriptionInfoEntity>) prescriptionInfoDao.findList();
    }

    @Override
    public PageModel findPagedList(PrescriptionInfoEntity prescriptionInfo) {
        return prescriptionInfoDao.findPagedList(prescriptionInfo);
    }

    @Override
    public List<PrescriptionInfoEntity> getPrescriptionInfoList(PrescriptionInfoEntity prescriptionInfo) {
        List<PrescriptionInfoEntity> list = (List<PrescriptionInfoEntity>) prescriptionInfoDao.findListByCondition(prescriptionInfo);
        return list;
    }

    @Override
    public void deletePrescriptionInfoBatch(List<PrescriptionInfoEntity> list) {
        // TODO Auto-generated method stub
        try {
            prescriptionInfoDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

}

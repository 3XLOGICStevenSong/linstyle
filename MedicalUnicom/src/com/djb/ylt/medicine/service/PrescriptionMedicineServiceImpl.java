package com.djb.ylt.medicine.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.entity.PageModel;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.medicine.dao.IPrescriptionMedicineDao;
import com.djb.ylt.medicine.entity.PrescriptionMedicineEntity;






@Service("iPrescriptionMedicineService")
public class PrescriptionMedicineServiceImpl extends BaseService implements IPrescriptionMedicineService {

    @Autowired
    @Qualifier("prescriptionMedicineDao")
    private IPrescriptionMedicineDao prescriptionMedicineDao;

    @Override
    public void addPrescriptionMedicine(PrescriptionMedicineEntity prescriptionMedicine) {
        // TODO Auto-generated method stub
        try {
            prescriptionMedicineDao.insert(prescriptionMedicine);
        } catch (KeyExistException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public void deletePrescriptionMedicine(PrescriptionMedicineEntity prescriptionMedicine) {
        // TODO Auto-generated method stub
        try {
            prescriptionMedicineDao.delete(prescriptionMedicine);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

 

    @Override
    public void updatePrescriptionMedicine(PrescriptionMedicineEntity prescriptionMedicine) {
        // TODO Auto-generated method stub
        try {
            prescriptionMedicineDao.update(prescriptionMedicine);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    @Override
    public PrescriptionMedicineEntity getObject(PrescriptionMedicineEntity prescriptionMedicine) {
        return prescriptionMedicineDao.getObject(prescriptionMedicine);
    }

    @Override
    public List<PrescriptionMedicineEntity> getPrescriptionMedicineList() {
        // TODO Auto-generated method stub
        return (List<PrescriptionMedicineEntity>) prescriptionMedicineDao.findList();
    }

    @Override
    public PageModel findPagedList(PrescriptionMedicineEntity prescriptionMedicine) {
        return prescriptionMedicineDao.findPagedList(prescriptionMedicine);
    }

    @Override
    public List<PrescriptionMedicineEntity> getPrescriptionMedicineList(PrescriptionMedicineEntity prescriptionMedicine) {
        List<PrescriptionMedicineEntity> list = (List<PrescriptionMedicineEntity>) prescriptionMedicineDao.findListByCondition(prescriptionMedicine);
        return list;
    }

    @Override
    public void deletePrescriptionMedicineBatch(List<PrescriptionMedicineEntity> list) {
        // TODO Auto-generated method stub
        try {
            prescriptionMedicineDao.deleteBatch(list);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

}

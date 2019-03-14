package com.djb.ylt.medicine.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.medicine.entity.MedicalRecordEntity;

@Repository("medicineRecordDao")
public class MedicineRecordDaoImpl extends BaseDAOImpl<MedicalRecordEntity> implements IMedicineRecordDao {
}

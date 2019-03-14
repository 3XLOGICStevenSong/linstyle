package com.djb.ylt.medicine.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.medicine.entity.MedicineTypeEntity;

@Repository("medicineTypeDao")
public class MedicineTypeDaoImpl extends BaseDAOImpl<MedicineTypeEntity> implements IMedicineTypeDao {
}

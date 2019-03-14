package com.djb.ylt.medicine.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.medicine.entity.MedicineCollectionEntity;

@Repository("medicineCollectionDao")
public class MedicineCollectionDaoImpl extends BaseDAOImpl<MedicineCollectionEntity> implements IMedicineCollectionDao {
}

package com.djb.ylt.medicine.dao;

import org.springframework.stereotype.Repository;

import com.djb.ylt.framework.dao.BaseDAOImpl;
import com.djb.ylt.medicine.entity.PrescriptionInfoEntity;



@Repository("prescriptionInfoDao")
public class PrescriptionInfoDaoImpl extends BaseDAOImpl<PrescriptionInfoEntity> implements IPrescriptionInfoDao {
}

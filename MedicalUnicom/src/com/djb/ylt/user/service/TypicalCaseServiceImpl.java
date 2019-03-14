package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IAppointInquiryDao;
import com.djb.ylt.user.dao.ITypicalCaseDao;
import com.djb.ylt.user.entity.AppointInquiryEntity;
import com.djb.ylt.user.entity.TypicalCaseEntity;

/**
 * @date 2017年04月25日 下午11:08:54
 * @author fanzeng
 * 内容：android端首页轮播图service实现
 */
@Service("iTypicalCaseService")
public class TypicalCaseServiceImpl extends BaseService implements ITypicalCaseService {
    @Autowired
    @Qualifier("typicalCaseDao")
    private ITypicalCaseDao typicalCaseDao;
    
	@Override
	public void addTypicalCase(TypicalCaseEntity TypicalCase) {
	}

	@Override
	public void deleteTypicalCase(TypicalCaseEntity TypicalCase) {
	}

	@Override
	public void deleteTypicalCaseBatch(List<TypicalCaseEntity> list) {
		
	}

	@Override
	public void updateTypicalCase(TypicalCaseEntity TypicalCase) throws DataNotFoundException {
		typicalCaseDao.update(TypicalCase);
	}

	@Override
	public TypicalCaseEntity getObject(TypicalCaseEntity TypicalCase) {
		
		return typicalCaseDao.getObject(TypicalCase);
		
	}

	@Override
	public List<TypicalCaseEntity> getTypicalCaseList() {
		
        return (List<TypicalCaseEntity>) typicalCaseDao.findList();
	}

	@Override
	public List<TypicalCaseEntity> getTypicalCaseList(TypicalCaseEntity TypicalCase) {
		 List<TypicalCaseEntity> list = (List<TypicalCaseEntity>) typicalCaseDao.findListByCondition(TypicalCase);
	        return list;
	}

	@Override
	public TypicalCaseEntity getTypicalCaseDc(TypicalCaseEntity typicalCaseEntity) {

		TypicalCaseEntity resultEntity = (TypicalCaseEntity) typicalCaseDao
				.getObject(ITypicalCaseDao.FINDTYPICALCASEDOCTORCOMMENT, typicalCaseEntity);
		return resultEntity;
	}
	
}

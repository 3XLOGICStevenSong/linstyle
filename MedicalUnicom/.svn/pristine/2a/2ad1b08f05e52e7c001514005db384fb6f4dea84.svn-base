package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IInqueryViewDao;
import com.djb.ylt.user.entity.InqueryViewEntity;


@Service("iInqueryViewService")
public class InqueryViewServiceImpl extends BaseService implements IInqueryViewService {

    @Autowired
    @Qualifier("inqueryViewDao")
    private IInqueryViewDao inqueryViewDao;


	@Override
	public InqueryViewEntity getObject(InqueryViewEntity InqueryView) {
		
		  return inqueryViewDao.getObject(InqueryView);
		
	}

	@Override
	public List<InqueryViewEntity> getInqueryViewList() {
		
        return (List<InqueryViewEntity>) inqueryViewDao.findList();
	}

	@Override
	public List<InqueryViewEntity> getInqueryViewList(InqueryViewEntity InqueryView) {
		 List<InqueryViewEntity> list = (List<InqueryViewEntity>) inqueryViewDao.findListByCondition(InqueryView);
	        return list;
	}
}

package com.djb.ylt.docFollow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.docFollow.dao.IDocFollowDao;
import com.djb.ylt.docFollow.entity.DocFollowEntity;
import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;

@Service("iDocFollowService")
public class DocFollowServiceImpl  extends BaseService implements IDocFollowService {
	
	@Autowired
	@Qualifier("docFollowDao")
	private IDocFollowDao docFollowDao;
	
	@Override
	public List<DocFollowEntity> getList(DocFollowEntity docFollowEntity) {
		List<DocFollowEntity> list = (List<DocFollowEntity>) docFollowDao.findList(docFollowEntity);
		return list;
	}
	
	public List<DocFollowEntity> getFollowList(DocFollowEntity docFollowEntity) {
		List<DocFollowEntity> list = (List<DocFollowEntity>) docFollowDao.findListByCondition(docFollowEntity);
		return list;
	}
	
	@Override
	public DocFollowEntity getObject(DocFollowEntity docFollowEntity) {
		return docFollowDao.getObject(docFollowEntity);
	}
	
	@Override
	public void addFollowInterest(DocFollowEntity docFollowEntity) {

		// TODO Auto-generated method stub
		try {
			docFollowDao.insert(docFollowEntity);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteFollowInterest(DocFollowEntity docFollowEntity) {

		try {
			docFollowDao.delete(docFollowEntity);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
}

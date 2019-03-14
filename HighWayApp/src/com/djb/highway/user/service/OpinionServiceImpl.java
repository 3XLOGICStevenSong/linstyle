package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IOpinionDao;
import com.djb.highway.user.entity.OpinionEntity;

@Service("iOpinionService")
public class OpinionServiceImpl extends BaseService implements
		IOpinionService {

	@Autowired
	@Qualifier("opinionDao")
	private IOpinionDao opinionDao;

	@Override
	public void addOpinion(OpinionEntity Opinion) {
		// TODO Auto-generated method stub
		try {
			opinionDao.insert(Opinion);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteOpinion(OpinionEntity Opinion) {
		// TODO Auto-generated method stub
		try {
			opinionDao.delete(Opinion);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteOpinionBatch(List<OpinionEntity> list) {
		// TODO Auto-generated method stub
		try {
			opinionDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateOpinion(OpinionEntity Opinion) {
		// TODO Auto-generated method stub
		try {
			opinionDao.update(Opinion);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public OpinionEntity getOpinionById(int ur_id) {
		// TODO Auto-generated method stub
		return opinionDao.getObject(ur_id);
	}

	@Override
	public OpinionEntity getOpinionById(OpinionEntity Opinion) {
		// TODO Auto-generated method stub
		return opinionDao.getObject(Opinion);
	}

	@Override
	public List<OpinionEntity> getOpinionList() {
		// TODO Auto-generated method stub
		return opinionDao.findList();
	}

	@Override
	public PageModel findPagedList(OpinionEntity Opinion) {
		return opinionDao.findPagedList(Opinion);
	}

	@Override
	public List<OpinionEntity> getOpinionList(OpinionEntity Opinion) {
		List<OpinionEntity> list = opinionDao
				.findListByCondition(Opinion);
		return list;
	}

}

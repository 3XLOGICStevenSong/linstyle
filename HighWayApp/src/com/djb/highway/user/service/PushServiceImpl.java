package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IPushDao;
import com.djb.highway.user.entity.PushEntity;


@Service("iPushService")
public class PushServiceImpl extends BaseService implements
		IPushService {

	@Autowired
	@Qualifier("pushDao")
	private IPushDao pushDao;

	@Override
	public void addPush(PushEntity push) {
		// TODO Auto-generated method stub
		try {
			pushDao.insert(push);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deletePush(PushEntity push) {
		// TODO Auto-generated method stub
		try {
			pushDao.delete(push);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deletePushBatch(List<PushEntity> list) {
		// TODO Auto-generated method stub
		try {
			pushDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updatePush(PushEntity push) {
		// TODO Auto-generated method stub
		try {
			pushDao.update(push);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public PushEntity getPushById(int p_id) {
		// TODO Auto-generated method stub
		return pushDao.getObject(p_id);
	}

	public PushEntity getPushById(PushEntity push) {
		// TODO Auto-generated method stub
		return pushDao.getObject(push);
	}

	public List<PushEntity> getPushList() {
		// TODO Auto-generated method stub
		return pushDao.findList();
	}

	//@Override
	//public PageModel findPagedList(PhoneBookEntity phoneBook) {
//		return phoneBookDao.findPagedList(phoneBook);
//	}

	@Override
	public List<PushEntity> getPushList(PushEntity push) {
		List<PushEntity> list = pushDao
				.findListByCondition(push);
		return list;
	}

	@Override
	public PageModel findPagedList(PushEntity phoneBook) {
		// TODO Auto-generated method stub
		return null;
	}

	
	}

	

	



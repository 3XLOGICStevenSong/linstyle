package com.djb.highway.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.entity.PageModel;
import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.user.dao.IPhoneBookDao;
import com.djb.highway.user.entity.PhoneBookEntity;

@Service("iPhoneBookService")
public class PhoneBookServiceImpl extends BaseService implements
		IPhoneBookService {

	@Autowired
	@Qualifier("phoneBookDao")
	private IPhoneBookDao phoneBookDao;

	@Override
	public void addPhoneBook(PhoneBookEntity phoneBook) {
		// TODO Auto-generated method stub
		try {
			phoneBookDao.insert(phoneBook);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deletePhoneBook(PhoneBookEntity phoneBook) {
		// TODO Auto-generated method stub
		try {
			phoneBookDao.delete(phoneBook);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deletePhoneBookBatch(List<PhoneBookEntity> list) {
		// TODO Auto-generated method stub
		try {
			phoneBookDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updatePhoneBook(PhoneBookEntity phoneBook) {
		// TODO Auto-generated method stub
		try {
			phoneBookDao.update(phoneBook);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public PhoneBookEntity getPhoneBookById(int p_id) {
		// TODO Auto-generated method stub
		return phoneBookDao.getObject(p_id);
	}

	public PhoneBookEntity getPhoneBookById(PhoneBookEntity phoneBook) {
		// TODO Auto-generated method stub
		return phoneBookDao.getObject(phoneBook);
	}

	public List<PhoneBookEntity> getPhoneBookList() {
		// TODO Auto-generated method stub
		return phoneBookDao.findList();
	}

	//@Override
	//public PageModel findPagedList(PhoneBookEntity phoneBook) {
//		return phoneBookDao.findPagedList(phoneBook);
//	}

	@Override
	public List<PhoneBookEntity> getPhoneBookList(PhoneBookEntity phoneBook) {
		List<PhoneBookEntity> list = phoneBookDao
				.findListByCondition(phoneBook);
		return list;
	}

	@Override
	public PageModel findPagedList(PhoneBookEntity phoneBook) {
		// TODO Auto-generated method stub
		return null;
	}

	
	}

	

	



package com.djb.highway.user.service;

import java.util.List;

import com.djb.highway.framework.entity.PageModel;

import com.djb.highway.user.entity.PhoneBookEntity;

public interface IPhoneBookService {
	public void addPhoneBook(PhoneBookEntity phoneBook);

	public void deletePhoneBook(PhoneBookEntity phoneBook);

	public void deletePhoneBookBatch(List<PhoneBookEntity> list);

	public void updatePhoneBook(PhoneBookEntity phoneBook);

	public PhoneBookEntity getPhoneBookById(int p_id);

	public PhoneBookEntity getPhoneBookById(PhoneBookEntity phoneBook);

	public List<PhoneBookEntity> getPhoneBookList(PhoneBookEntity paramEntity);

   public PageModel findPagedList(PhoneBookEntity phoneBook);
}
	

package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.ISectionDao;
import com.djb.highway.road.entity.SectionEntity;

@Service("iSectionService")
public class SectionServiceImpl extends BaseService implements ISectionService {

	@Autowired
	@Qualifier("sectionDao")
	private ISectionDao sectionDao;

	@Override
	public void addSection(SectionEntity section) {
		
		try {
			sectionDao.insert(section);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteSection(SectionEntity section) {
		
		try {
			sectionDao.delete(section);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteSectionBatch(List<SectionEntity> list) {
		
		try {
			sectionDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateSection(SectionEntity section) {
		
		try {
			sectionDao.update(section);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public SectionEntity getObject(SectionEntity section) {
		return sectionDao.getObject(section);
	}

	@Override
	public List<SectionEntity> getSectionList() {
		
		return (List<SectionEntity>) sectionDao.findList();
	}

	@Override
	public List<SectionEntity> getSectionList(SectionEntity section) {
		List<SectionEntity> list = (List<SectionEntity>) sectionDao
				.findList(section);
		return list;
	}

}

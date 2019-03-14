package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IInfoBoardDao;
import com.djb.highway.road.entity.InfoBoardEntity;

@Service("iInfoBoardService")
public class InfoBoardServiceImpl extends BaseService implements IInfoBoardService {

	@Autowired
	@Qualifier("infoBoardDao")
	private IInfoBoardDao infoBoardDao;

	@Override
	public void addInfoBoard(InfoBoardEntity infoBoard) {
		
		try {
			infoBoardDao.insert(infoBoard);
		} catch (KeyExistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteInfoBoard(InfoBoardEntity infoBoard) {
		
		try {
			infoBoardDao.delete(infoBoard);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteInfoBoardBatch(List<InfoBoardEntity> list) {
		
		try {
			infoBoardDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void updateInfoBoard(InfoBoardEntity infoBoard) {
		
		try {
			infoBoardDao.update(infoBoard);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public InfoBoardEntity getObject(InfoBoardEntity infoBoard) {
		return infoBoardDao.getObject(infoBoard);
	}

	@Override
	public List<InfoBoardEntity> getInfoBoardList() {
		
		return (List<InfoBoardEntity>) infoBoardDao.findList();
	}

	@Override
	public List<InfoBoardEntity> getInfoBoardList(InfoBoardEntity infoBoard) {
		List<InfoBoardEntity> list = (List<InfoBoardEntity>) infoBoardDao
				.findListByCondition(infoBoard);
		return list;
	}

	  @Override
	    public List<InfoBoardEntity> getInfoBoardPointList(InfoBoardEntity infoBoard) {
	        List<InfoBoardEntity> list = (List<InfoBoardEntity>) infoBoardDao.findOtherList(IInfoBoardDao.GETINFOBOARDPOINTLIST, infoBoard, InfoBoardEntity.class);

	        return list;
	    }
}

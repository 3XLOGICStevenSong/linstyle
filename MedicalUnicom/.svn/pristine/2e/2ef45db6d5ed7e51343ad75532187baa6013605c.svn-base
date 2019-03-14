package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IDoctorCommentDao;
import com.djb.ylt.user.entity.DoctorCommentEntity;



@Service("iDoctorCommentService")
public class DoctorCommentServiceImpl extends BaseService implements IDoctorCommentService {

    @Autowired
    @Qualifier("doctorCommentDao")
    private IDoctorCommentDao doctorCommentDao;
	@Override
	public void addDoctorComment(DoctorCommentEntity DoctorComment) {
		
		// TODO Auto-generated method stub
		  try {
			  doctorCommentDao.insert(DoctorComment);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteDoctorComment(DoctorCommentEntity DoctorComment) {
		
		try {
			doctorCommentDao.delete(DoctorComment);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteDoctorCommentBatch(List<DoctorCommentEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDoctorComment(DoctorCommentEntity DoctorComment) {
		
		 try {
			 doctorCommentDao.update(DoctorComment);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}
	@Override
	public DoctorCommentEntity getObject(DoctorCommentEntity DoctorComment) {
		
		  return doctorCommentDao.getObject(DoctorComment);
		
	}

	@Override
	public List<DoctorCommentEntity> getDoctorCommentList() {
		
        return (List<DoctorCommentEntity>) doctorCommentDao.findList();
	}

	@Override
	public List<DoctorCommentEntity> getDoctorCommentList(DoctorCommentEntity DoctorComment) {
		 List<DoctorCommentEntity> list = (List<DoctorCommentEntity>) doctorCommentDao.findListByCondition(DoctorComment);
	return list;
	}
	 @Override
	    public Integer getCount(DoctorCommentEntity doctorComment) {
	        return doctorCommentDao.getOtherObject(IDoctorCommentDao.GETCOUNT, doctorComment, Integer.class);

	    }
	 
	  @Override
	    public DoctorCommentEntity getAverageGrade(DoctorCommentEntity doctorComment) {
	        return doctorCommentDao.getOtherObject(IDoctorCommentDao.GETAVERAGEGRADE, doctorComment, DoctorCommentEntity.class);

	    }
}

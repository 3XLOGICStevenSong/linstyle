package com.djb.ylt.user.dao;

import com.djb.ylt.framework.dao.BaseDAO;
import com.djb.ylt.user.entity.DoctorCommentEntity;



public interface IDoctorCommentDao extends BaseDAO<DoctorCommentEntity> {
	
	public static String GETCOUNT = "getCount";
	public static String GETAVERAGEGRADE="getAverageGrade";

}

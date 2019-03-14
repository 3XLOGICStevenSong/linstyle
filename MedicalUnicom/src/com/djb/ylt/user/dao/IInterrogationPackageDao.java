package com.djb.ylt.user.dao;

import com.djb.ylt.framework.dao.BaseDAO;

import com.djb.ylt.user.entity.InterrogationPackageEntity;

public interface IInterrogationPackageDao extends BaseDAO<InterrogationPackageEntity> {

	public static String FINDLISTBYSYMPTOMID = "findListBySymptomId";

	public static String FINDLISTBYDOCTORID = "findListByDoctorId";
	// 修改履历：2016年12月21日 下午3:02:09 chengming start
	// 常用医生返回医生价格
	public static String GETTOTAL = "getTotal";
	// 修改履历：2016年12月21日 下午3:02:09 chengming end
}

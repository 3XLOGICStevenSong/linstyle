package com.djb.ylt.user.service;

import java.util.List;

import com.djb.ylt.user.entity.InterrogationPackageEntity;


public interface IInterrogationPackageService {
	
    public void addInterrogationPackage(InterrogationPackageEntity InterrogationPackage);

    public void deleteInterrogationPackage(InterrogationPackageEntity InterrogationPackage);

    public void deleteInterrogationPackageBatch(List<InterrogationPackageEntity> list);

    public void updateInterrogationPackage(InterrogationPackageEntity InterrogationPackage);

    public InterrogationPackageEntity getObject(InterrogationPackageEntity InterrogationPackage);

    public List<InterrogationPackageEntity> getInterrogationPackageList();

    public List<InterrogationPackageEntity> getInterrogationPackageList(InterrogationPackageEntity InterrogationPackage);
    
    public List<InterrogationPackageEntity> findListBySymptomId(InterrogationPackageEntity InterrogationPackage);
    
    public List<InterrogationPackageEntity> findListByDoctorId(InterrogationPackageEntity InterrogationPackage);
    
 // 修改履历：2016年12月21日 下午3:02:09 chengming start
 	public InterrogationPackageEntity getOtherObject(InterrogationPackageEntity InterrogationPackage);
 	// 修改履历：2016年12月21日 下午3:02:09 chengming end
}

package com.djb.ylt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IInterrogationPackageDao;
import com.djb.ylt.user.entity.InterrogationPackageEntity;


@Service("iInterrogationPackageService")
public class InterrogationPackageServiceImpl extends BaseService implements IInterrogationPackageService {

    @Autowired
    @Qualifier("interrogationPackageDao")
    private IInterrogationPackageDao interrogationPackageDao;
	@Override
	public void addInterrogationPackage(InterrogationPackageEntity InterrogationPackage) {
		
		// TODO Auto-generated method stub
		  try {
			  interrogationPackageDao.insert(InterrogationPackage);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteInterrogationPackage(InterrogationPackageEntity InterrogationPackage) {
		
		try {
			interrogationPackageDao.delete(InterrogationPackage);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteInterrogationPackageBatch(List<InterrogationPackageEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInterrogationPackage(InterrogationPackageEntity InterrogationPackage) {
		
		 try {
			 interrogationPackageDao.update(InterrogationPackage);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public InterrogationPackageEntity getObject(InterrogationPackageEntity InterrogationPackage) {
		
		  return interrogationPackageDao.getObject(InterrogationPackage);
		
	}

	@Override
	public List<InterrogationPackageEntity> getInterrogationPackageList() {
		
        return (List<InterrogationPackageEntity>) interrogationPackageDao.findList();
	}

	@Override
	public List<InterrogationPackageEntity> getInterrogationPackageList(InterrogationPackageEntity InterrogationPackage) {
		 List<InterrogationPackageEntity> list = (List<InterrogationPackageEntity>) interrogationPackageDao.findListByCondition(InterrogationPackage);
	        return list;
	}

	@Override
	public List<InterrogationPackageEntity> findListBySymptomId(InterrogationPackageEntity InterrogationPackage) {
		List<InterrogationPackageEntity> list = (List<InterrogationPackageEntity>)interrogationPackageDao.findOtherList(IInterrogationPackageDao.FINDLISTBYSYMPTOMID, InterrogationPackage, InterrogationPackageEntity.class);
		
		return list;
	}

	@Override
	public List<InterrogationPackageEntity> findListByDoctorId(InterrogationPackageEntity InterrogationPackage) {
		List<InterrogationPackageEntity> list = (List<InterrogationPackageEntity>)interrogationPackageDao.findOtherList(IInterrogationPackageDao.FINDLISTBYDOCTORID, InterrogationPackage, InterrogationPackageEntity.class);
		
		return list;
	}
	// 修改履历：2016年12月21日 下午3:26:27 chengming start
	@Override
	public InterrogationPackageEntity getOtherObject(InterrogationPackageEntity InterrogationPackage) {
		return interrogationPackageDao.getOtherObject(IInterrogationPackageDao.GETTOTAL, InterrogationPackage,InterrogationPackageEntity.class);

	}
	// 修改履历：2016年12月21日 下午3:26:27 chengming end
}

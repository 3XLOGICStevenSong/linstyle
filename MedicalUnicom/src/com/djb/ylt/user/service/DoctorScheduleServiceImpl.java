package com.djb.ylt.user.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.djb.ylt.framework.exception.dao.DataNotFoundException;
import com.djb.ylt.framework.exception.dao.KeyExistException;
import com.djb.ylt.framework.service.BaseService;
import com.djb.ylt.user.dao.IDoctorScheduleDao;
import com.djb.ylt.user.dto.DoctorScheduleDTO;
import com.djb.ylt.user.dto.ScheduleDateDTO;
import com.djb.ylt.user.entity.DoctorScheduleEntity;


@Service("iDoctorScheduleService")
public class DoctorScheduleServiceImpl extends BaseService implements IDoctorScheduleService {

    @Autowired
    @Qualifier("doctorScheduleDao")
    private IDoctorScheduleDao doctorScheduleDao;
    @Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addDoctorSchedule(DoctorScheduleEntity doctorSchedule)  {
		
		// TODO Auto-generated method stub
		  try {
			  
			// 删除没被预定的日程 预定人数大于0的
				DoctorScheduleEntity deleteEntity = new DoctorScheduleEntity();
				deleteEntity.setDoctorId(doctorSchedule.getDoctorId());
				deleteEntity.setStartDateArgs(doctorSchedule.getStartDateArgs());
				int i=doctorScheduleDao.delete(deleteEntity);
			//	System.out.println("iiiiiiii"+i);
				// 更新和添加操作
				if (doctorSchedule.getScheduleDTOList() != null && doctorSchedule.getScheduleDTOList().size() > 0) {
					// 参数Entity
					for (DoctorScheduleDTO doctorScheduleParam : doctorSchedule.getScheduleDTOList()) {
						
						if (doctorScheduleParam.getScheduleDateList() != null
								&& doctorScheduleParam.getScheduleDateList().size() > 0) {
							for (ScheduleDateDTO schDate : doctorScheduleParam.getScheduleDateList()) {
								// 如果日程ID的存在并且更新的数量>=预约人数,更新数量
								if (schDate.getScheduleId() != null) {
									DoctorScheduleEntity paramSchedule = new DoctorScheduleEntity();
									DoctorScheduleEntity resultSchedule = new DoctorScheduleEntity();
									paramSchedule.setScheduleId(schDate.getScheduleId());
									resultSchedule = doctorScheduleDao.getObject(paramSchedule);
									if (schDate.getPatientNum() >= resultSchedule.getAppointNum()
											&& resultSchedule.getAppointNum() > 0) {
										paramSchedule.setPatientNum(schDate.getPatientNum());
										doctorScheduleDao.update(paramSchedule);
									}
								} else {
									// 以天为单位进行插入
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
									
									DoctorScheduleEntity paramSchedule = new DoctorScheduleEntity();
									if (doctorScheduleParam.getStartDate() != null) {
										paramSchedule.setStartDate(sdf1.parse(doctorScheduleParam.getStartDate()));
									}
									if (schDate.getStartTime() != null) {
										paramSchedule.setStartTime(sdf.parse(schDate.getStartTime()));
									}
									if (schDate.getEndTime() != null) {
										paramSchedule.setEndTime(sdf.parse(schDate.getEndTime()));
									}
									paramSchedule.setPatientNum(schDate.getPatientNum());
									paramSchedule.setDoctorId(doctorSchedule.getDoctorId());
									paramSchedule.setStatus("1");
									paramSchedule.setAppointNum(0);
									doctorScheduleDao.insert(paramSchedule);
								}
							}
						}
//						if (paramScheduleList != null && paramScheduleList.size() > 0) {
//							doctorScheduleDao.insertBatch(paramScheduleList);
//						}
					}
				}

			//  doctorScheduleDao.insert(doctorSchedule);
	        } catch (KeyExistException | DataNotFoundException | ParseException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void deleteDoctorSchedule(DoctorScheduleEntity DoctorSchedule) {
		
		try {
			doctorScheduleDao.delete(DoctorSchedule);
        } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
		
	}

	@Override
	public void deleteDoctorScheduleBatch(List<DoctorScheduleEntity> list) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDoctorSchedule(DoctorScheduleEntity DoctorSchedule) {
		
		 try {
			 doctorScheduleDao.update(DoctorSchedule);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public DoctorScheduleEntity getObject(DoctorScheduleEntity DoctorSchedule) {
		
		  return doctorScheduleDao.getObject(DoctorSchedule);
		
	}

	@Override
	public List<DoctorScheduleEntity> getDoctorScheduleList() {
		
        return (List<DoctorScheduleEntity>) doctorScheduleDao.findList();
	}

	@Override
	public List<DoctorScheduleEntity> getDoctorScheduleList(DoctorScheduleEntity DoctorSchedule) {
		 List<DoctorScheduleEntity> list = (List<DoctorScheduleEntity>) doctorScheduleDao.findListByCondition(DoctorSchedule);
	        return list;
	}

	@Override
	public void addDoctorScheduleBatch(List<DoctorScheduleEntity> doctorSchedule) {
		
		 try {
			  doctorScheduleDao.insertBatch(doctorSchedule);
	        } catch (KeyExistException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
	}

	@Override
	public void updateDoctorScheduleBatch(List<DoctorScheduleEntity> doctorSchedule) {
		
		try {
			 doctorScheduleDao.updateBatch(doctorSchedule);
	        } catch (DataNotFoundException e) {
	            // TODO Auto-generated catch block
	            // e.printStackTrace();
	        }
		
	}

}

package cn.com.dbridge.jtraining.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.jtraining.dao.po.TrainSourceRalationPO;

public interface TrainSourceRalationPOMapper {
    //后台
    int deleteByPrimaryKey(@Param("sourceNo") Integer sourceNo);

    //后台
    int insert(TrainSourceRalationPO record);

    TrainSourceRalationPO selectByPrimaryKey(@Param("trainId") Integer trainId, @Param("sourceNo") Integer sourceNo);

    List<TrainSourceRalationPO> selectAll();

    int updateByPrimaryKey(TrainSourceRalationPO record);

    // TODO 陈军
	List<TrainSourceRalationPO> selectByTrainId(int trainId);
}
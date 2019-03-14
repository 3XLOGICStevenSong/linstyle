package cn.com.dbridge.jtraining.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.jtraining.dao.po.TrainSourcePO;

public interface TrainSourcePOMapper {
    //后台
    int deleteByPrimaryKey(Integer sourceNo);

    //后台
    int insert(TrainSourcePO record);

    //后台
    TrainSourcePO selectByPrimaryKey(Integer sourceNo);

    List<TrainSourcePO> selectAll();

    //后台
    int updateByPrimaryKey(TrainSourcePO record);

    /**
     * 
     * @Title: checkSourceTitle
     * @Description: 检测培训资料标题
     * @param sourceTitle
     * @return 培训资料信息
     */
    TrainSourcePO checkSourceTitle(@Param("sourceTitle") String sourceTitle);

}
package cn.com.dbridge.jtraining.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.jtraining.dao.po.TrainCategoryInformationPO;
import cn.com.dbridge.jtraining.dao.po.TrainCategoryPO;

public interface TrainCategoryPOMapper {
    int deleteByPrimaryKey(Integer trainType);

    //后台
    int insert(TrainCategoryPO record);

    //后台
    TrainCategoryPO selectByPrimaryKey(Integer trainType);

    //后台
    List<TrainCategoryPO> selectAll();

    //后台
    int updateByPrimaryKey(TrainCategoryPO record);

    /**
     * 
     * @Title: selectAllName
     * @Description: 培训种别查询
     * @return 种别信息
     */
    List<TrainCategoryInformationPO> selectAllName();

    /**
     * 
     * @Title: selectCheck
     * @Description: 检查培训种别信息是否重复
     * @param typeName
     * @return 种别信息
     */
    //后台
    TrainCategoryPO selectCheck(@Param("typeName") String typeName);

    String selectTypeNameByTrainType(
            @Param("trainType") Integer trainType);
}
package cn.com.dbridge.jtraining.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.jtraining.dao.po.TecherPO;
import cn.com.dbridge.jtraining.dao.po.TrainInfoPO;
import cn.com.dbridge.jtraining.dao.po.TrainInformationPO;
import cn.com.dbridge.jtraining.dao.po.TrainItemPO;
import cn.com.dbridge.jtraining.dao.po.TrainItemQueryPO;
import cn.com.dbridge.jtraining.dao.po.TrainMaterialPO;

public interface TrainItemPOMapper {

    //后台
    int deleteByPrimaryKey(Integer trainId);

    //后台
    int insert(TrainItemPO record);

    //后台
    TrainItemPO selectByPrimaryKey(Integer trainId);

    //后台
    List<TrainItemPO> selectAll();

    //后台
    int updateByPrimaryKey(TrainItemPO record);


    /**
     * 
     * @Title: selectList
     * @Description: 通过条件查询
     * @param trainItemQueryPO
     * @return 查询出培训信息
     */
    //后台
    List<TrainInformationPO> selectList(TrainItemQueryPO trainItemQueryPO);


    /**
     * 
     * @Title: selectNameList
     * @Description: 通过培训种别查询培训老师
     * @param trainType
     * @return 培训老师信息
     */

    //后台
    List<TecherPO> selectNameList(@Param("trainType") Integer trainType);


    /**
     * 
     * @Title: selectTrainMaterialList
     * @Description: 通过培训id查询培训资料
     * @param trainId
     * @return 培训资料信息
     */

    //后台

    List<TrainMaterialPO> selectTrainMaterialList(
            @Param("trainId") Integer trainId);
    /**
     * 
     * @Title: selectCheckTitle
     * @Description: check 培训标题
     * @param trainTitle
     * @return 培训信息
     */
    //后台
    TrainItemPO selectCheckTitle(@Param("trainTitle") String trainTitle);

    //陳軍
	List<TrainItemPO> selectByTeacherId(
			@Param("teacherId") String teacherId);

    //郭健
    List<TrainItemPO> selectAllTrainItem();

	/**
	 * トレーニングIDでトレーニングと教師の情報 陳軍
	 * 
	 * @param trainId
	 * @return
	 */
	TrainInfoPO selectTrainInfo(Integer trainId);
}
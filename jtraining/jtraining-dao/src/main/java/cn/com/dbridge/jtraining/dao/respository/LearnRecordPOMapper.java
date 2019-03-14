package cn.com.dbridge.jtraining.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.jtraining.dao.po.LearnRecordPO;
import cn.com.dbridge.jtraining.dao.po.LearnSourceQueryPO;
import cn.com.dbridge.jtraining.dao.po.LearnedSourcePO;
import cn.com.dbridge.jtraining.dao.po.LearningSourcePO;
import cn.com.dbridge.jtraining.dao.po.TrainListQueryPO;
import cn.com.dbridge.jtraining.dao.po.TrainSourceItemPO;

public interface LearnRecordPOMapper {
	int deleteByPrimaryKey(@Param("studentId") String studentId,
			@Param("trainId") Integer trainId,
			@Param("trainTitle") String trainTitle,
			@Param("sourceType") Integer sourceType,
			@Param("sourceNo") Integer sourceNo);

    int deleteByStudentId(@Param("studentId") String studentId);

    //手机
    int insert(LearnRecordPO record);

	LearnRecordPO selectByPrimaryKey(@Param("studentId") String studentId,
			@Param("trainId") Integer trainId,
			@Param("trainTitle") String trainTitle,
			@Param("sourceType") Integer sourceType,
			@Param("sourceNo") Integer sourceNo);

	LearnRecordPO selectByNo(@Param("studentId") String studentId,
			@Param("trainId") Integer trainId,
			@Param("sourceNo") Integer sourceNo);

    List<LearnRecordPO> selectAll();

    //手机
    int updateByPrimaryKey(LearnRecordPO record);

	int updateByNo(LearnRecordPO record);

	// TODO 陳軍
	TrainSourceItemPO selectSource(TrainListQueryPO trainListQueryPO);

    // TODO 陳軍
    List<LearningSourcePO> selectLearningSource(
            LearnSourceQueryPO learnSourceQueryPO);
    /**
     * 
     * @Title: checkSourceNo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param sourceNo
     * @return
     */
    LearnRecordPO checkSourceNo(@Param("sourceNo") Integer sourceNo);

	// TODO 陳軍
	List<LearnedSourcePO> selectLearnedSource(
			LearnSourceQueryPO learnSourceQueryPO);
}
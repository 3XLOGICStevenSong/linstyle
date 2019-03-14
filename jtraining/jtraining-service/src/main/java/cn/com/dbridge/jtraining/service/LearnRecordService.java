/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: LearnRecordService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description:TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月6日 下午1:55:19
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目的
 */
package cn.com.dbridge.jtraining.service;

import java.util.List;

import cn.com.dbridge.jtraining.framework.dto.LearnRecordUpdateDTO;
import cn.com.dbridge.jtraining.framework.vo.LearnedSourceVO;
import cn.com.dbridge.jtraining.framework.vo.LearningSourceVO;

/**
 * @ClassName:LearnRecordService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 陈健飞
 * @date:   2018年12月6日 下午1:55:19
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public interface LearnRecordService {

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: updateLearnRecord
	 * @Description: 勉強記録情報の更新
	 * @param learnRecordUpdateDTO
	 * @return 更新件数
	 */
	public int updateLearnRecord(LearnRecordUpdateDTO learnRecordUpdateDTO);

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: queryLearningSource
	 * @Description: 学生の勉強中のトレーニング資料を取得する
	 * @param studentId
	 * @return List<LearningSourceVO>
	 */
	public List<LearningSourceVO> queryLearningSource(String studentId);

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: queryLearnedSource
	 * @Description: 学生の勉強完了のトレーニング資料を取得する
	 * @param studentId
	 * @return List<LearnedSourceVO>
	 */
	public List<LearnedSourceVO> queryLearnedSource(String studentId);
}

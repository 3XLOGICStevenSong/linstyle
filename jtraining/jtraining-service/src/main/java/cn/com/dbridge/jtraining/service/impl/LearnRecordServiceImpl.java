/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: LearnRecordService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月6日 下午1:55:19
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dbridge.jtraining.dao.po.LearnRecordPO;
import cn.com.dbridge.jtraining.dao.po.LearnSourceQueryPO;
import cn.com.dbridge.jtraining.dao.po.LearnedSourcePO;
import cn.com.dbridge.jtraining.dao.po.LearningSourcePO;
import cn.com.dbridge.jtraining.dao.po.TrainSourcePO;
import cn.com.dbridge.jtraining.dao.respository.LearnRecordPOMapper;
import cn.com.dbridge.jtraining.dao.respository.TrainSourcePOMapper;
import cn.com.dbridge.jtraining.framework.dto.LearnRecordUpdateDTO;
import cn.com.dbridge.jtraining.framework.vo.LearnSourceItemVO;
import cn.com.dbridge.jtraining.framework.vo.LearnedSourceItemVO;
import cn.com.dbridge.jtraining.framework.vo.LearnedSourceVO;
import cn.com.dbridge.jtraining.framework.vo.LearningSourceVO;
import cn.com.dbridge.jtraining.service.LearnRecordService;

/**
 * @ClassName: LearnRecordService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 陳軍
 * @date: 2018年12月17日 下午1:55:19
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class LearnRecordServiceImpl implements LearnRecordService {

	private static final String COMPLETE = "完成";
	private static final String TOTAL = "合計";

	@Autowired
	LearnRecordPOMapper learnRecordPOMapper;

	@Autowired
	TrainSourcePOMapper trainSourcePOMapper;

	@Override
	@Transactional
	public int updateLearnRecord(LearnRecordUpdateDTO learnRecordUpdateDTO) {
		// マインキーで勉強記録表を検索する
		LearnRecordPO learnRecordPO = learnRecordPOMapper.selectByNo(learnRecordUpdateDTO.getStudentId(),
				learnRecordUpdateDTO.getTrainId(), learnRecordUpdateDTO.getSourceNo());
		TrainSourcePO trainSourcePO = trainSourcePOMapper.selectByPrimaryKey(learnRecordUpdateDTO.getSourceNo());
		int sourceLength = 0;
		int sourceType = 0;
		if (null != trainSourcePO) {
			sourceLength = trainSourcePO.getSourceLength();
			sourceType = trainSourcePO.getSourceType();
		}
		int result = 0;
		// 更新の場合
		if (null != learnRecordPO) {
			if (2 != learnRecordPO.getUseStatus()) {
				BeanUtils.copyProperties(learnRecordUpdateDTO, learnRecordPO);
				learnRecordPO.setSourceLength(sourceLength);
				// 完成パーセントを設定する
				BigDecimal learnPercent = (0 != sourceLength)
						? BigDecimal.valueOf(learnRecordUpdateDTO.getSourceLearnLength()).divide(
								BigDecimal.valueOf(sourceLength), 2, RoundingMode.HALF_DOWN)
						: BigDecimal.valueOf(0.00);
				learnRecordPO.setLearnPercent(BigDecimal.valueOf(100).multiply(learnPercent));
				if (0 == BigDecimal.valueOf(0.00).compareTo(learnPercent)) {
					// 見ていない
					learnRecordPO.setUseStatus(new Byte("0"));
				} else if (0 == BigDecimal.valueOf(1.00).compareTo(learnPercent)) {
					// もう読み終わった
					learnRecordPO.setUseStatus(new Byte("2"));
				} else {
					// 見ている
					learnRecordPO.setUseStatus(new Byte("1"));
				}
				learnRecordPO.setUpdateDate(new Timestamp(System.currentTimeMillis()));
				learnRecordPO.setUpdatePerson(learnRecordPO.getStudentId());
				result = learnRecordPOMapper.updateByNo(learnRecordPO);
			} else {
				learnRecordPO.setUpdateDate(new Timestamp(System.currentTimeMillis()));
				result = learnRecordPOMapper.updateByNo(learnRecordPO);
			}
		}
		// 追加の場合
		else {
			LearnRecordPO learnRecordPOInsert = new LearnRecordPO();
			BeanUtils.copyProperties(learnRecordUpdateDTO, learnRecordPOInsert);
			learnRecordPOInsert.setSourceType(sourceType);
			learnRecordPOInsert.setSourceLength(sourceLength);
			// 完成パーセントを設定する
			BigDecimal learnPercent = (0 != sourceLength)
					? BigDecimal.valueOf(learnRecordUpdateDTO.getSourceLearnLength()).divide(
							BigDecimal.valueOf(sourceLength), 2, RoundingMode.HALF_DOWN)
					: BigDecimal.valueOf(0.00);
			learnRecordPOInsert.setLearnPercent(BigDecimal.valueOf(100).multiply(learnPercent));
			if (0 == BigDecimal.valueOf(0.00).compareTo(learnPercent)) {
				// 見ていない
				learnRecordPOInsert.setUseStatus(new Byte("0"));
			} else if (0 == BigDecimal.valueOf(1.00).compareTo(learnPercent)) {
				// もう読み終わった
				learnRecordPOInsert.setUseStatus(new Byte("2"));
			} else {
				// 見ている
				learnRecordPOInsert.setUseStatus(new Byte("1"));
			}
			learnRecordPOInsert.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			learnRecordPOInsert.setUpdatePerson(learnRecordPOInsert.getStudentId());
			learnRecordPOInsert.setInsertDate(new Timestamp(System.currentTimeMillis()));
			learnRecordPOInsert.setInsertPerson(learnRecordPOInsert.getStudentId());
			result = learnRecordPOMapper.insert(learnRecordPOInsert);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LearningSourceVO> queryLearningSource(String studentId) {
		// インスタンス化
		List<LearningSourceVO> learningSourceVOList = new ArrayList<LearningSourceVO>();
		LearnSourceQueryPO learnSourceQueryPO = new LearnSourceQueryPO();
		// 検索条件のパラメータを設定する
		learnSourceQueryPO.setStudentId(studentId);
		learnSourceQueryPO.setLearnPercent(BigDecimal.valueOf(100));
		// 勉強中の資料を取得する
		List<LearningSourcePO> learningSourcePOList = learnRecordPOMapper.selectLearningSource(learnSourceQueryPO);
		// 取得できる場合
		if (null != learningSourcePOList) {
			// 取得する結果をトレーニングIDでグループする
			Map<Integer, List<LearningSourcePO>> groupBy = learningSourcePOList.stream()
					.collect(Collectors.groupingBy(LearningSourcePO::getTrainId));
			Set<Map.Entry<Integer, List<LearningSourcePO>>> entrySet = groupBy.entrySet();
			// グループしたentrySetをループする
			for (Map.Entry<Integer, List<LearningSourcePO>> entry : entrySet) {
				List<LearningSourcePO> learningSourcePOListTemp = entry.getValue();
				int trainId = entry.getKey();
				LearningSourceVO learningSourceVO = new LearningSourceVO();
				learningSourceVO.setTrainId(trainId);
				// 一件目のトレーニングタイトルでタイトルを設定する
				learningSourceVO.setTrainTitle(learningSourcePOListTemp.get(0).getTrainTitle());
				// トレーニング資料種別で勉強資料をグループする
				List<LearnSourceItemVO> learnSourceVideoItemVOListTemp = new ArrayList<LearnSourceItemVO>();
				List<LearnSourceItemVO> learnSourceTextItemVOListTemp = new ArrayList<LearnSourceItemVO>();
				Map<Integer, List<LearningSourcePO>> groupBySourceType = learningSourcePOListTemp.stream()
						.collect(Collectors.groupingBy(LearningSourcePO::getSourceType));
				Set<Map.Entry<Integer, List<LearningSourcePO>>> entrySetTemp = groupBySourceType.entrySet();
				// グループしたentrySetをループする
				for (Map.Entry<Integer, List<LearningSourcePO>> entryTemp : entrySetTemp) {
					List<LearningSourcePO> learningSourcePOListTemp2 = entryTemp.getValue();
					int sourceType = entryTemp.getKey();
					// 勉強中の資料をループする
					for (LearningSourcePO learningSourcePO : learningSourcePOListTemp2) {
						LearnSourceItemVO learnSourceItemVO = new LearnSourceItemVO();
						BeanUtils.copyProperties(learningSourcePO, learnSourceItemVO);
						learnSourceItemVO.setSourceLength(learningSourcePO.getSourceLength());
						learnSourceItemVO.setSourceLearnLength(learningSourcePO.getSourceLearnLength());
						learnSourceItemVO.setLearnPercent(String.valueOf(learningSourcePO.getLearnPercent()) + "%");
						if (1 == sourceType) {
							learnSourceVideoItemVOListTemp.add(learnSourceItemVO);
						} else if (2 == sourceType) {
							learnSourceTextItemVOListTemp.add(learnSourceItemVO);
						}
					}
					learningSourceVO.setVideoList(learnSourceVideoItemVOListTemp);
					learningSourceVO.setTextList(learnSourceTextItemVOListTemp);
				}
				learningSourceVOList.add(learningSourceVO);
			}
		}

		return learningSourceVOList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LearnedSourceVO> queryLearnedSource(String studentId) {
		// インスタンス化
		List<LearnedSourceVO> learnedSourceVOList = new ArrayList<LearnedSourceVO>();
		LearnSourceQueryPO learnSourceQueryPO = new LearnSourceQueryPO();
		// 検索条件のパラメータを設定する
		learnSourceQueryPO.setStudentId(studentId);
		// 勉強記録の資料を取得する
		List<LearnedSourcePO> learnedSourcePOList = learnRecordPOMapper.selectLearnedSource(learnSourceQueryPO);
		// 取得できる場合
		if (null != learnedSourcePOList) {
			// 取得する結果をトレーニングIDでグループする
			Map<Integer, List<LearnedSourcePO>> groupBy = learnedSourcePOList.stream()
					.collect(Collectors.groupingBy(LearnedSourcePO::getTrainId));
			Set<Map.Entry<Integer, List<LearnedSourcePO>>> entrySet = groupBy.entrySet();
			// グループしたentrySetをループする
			for (Map.Entry<Integer, List<LearnedSourcePO>> entry : entrySet) {
				List<LearnedSourcePO> learnedSourcePOListTemp = entry.getValue();
				int trainId = entry.getKey();
				LearnedSourceVO learnedSourceVO = new LearnedSourceVO();
				// 勉強完了の資料の件数
				int learnedSourceTrain = learnedSourcePOListTemp.stream().filter(
						learnedSourcePO -> (0 == BigDecimal.valueOf(100).compareTo(learnedSourcePO.getLearnPercent())))
						.collect(Collectors.toList()).size();
				if (0 != learnedSourceTrain) {
					learnedSourceVO.setTrainId(trainId);
					// 一件目のトレーニングタイトルでタイトルを設定する
					learnedSourceVO.setTrainTitle(learnedSourcePOListTemp.get(0).getTrainTitle());
					// 資料の総件数
					int sourceTrain = learnedSourcePOListTemp.size();
					// 勉強完了の資料パーセント
					StringBuffer learnedTrainPercent = new StringBuffer();
					learnedTrainPercent.append(COMPLETE);
					learnedTrainPercent.append(String.valueOf(learnedSourceTrain));
					learnedTrainPercent.append("/");
					learnedTrainPercent.append(TOTAL);
					learnedTrainPercent.append(String.valueOf(sourceTrain));
					learnedSourceVO.setTrainPercent(learnedTrainPercent.toString());
					// 資料種別で勉強資料をグループする
					List<LearnedSourceItemVO> learnedSourceVideoItemVOListTemp = new ArrayList<LearnedSourceItemVO>();
					List<LearnedSourceItemVO> learnedSourceTextItemVOListTemp = new ArrayList<LearnedSourceItemVO>();
					Map<Integer, List<LearnedSourcePO>> groupBySourceType = learnedSourcePOListTemp.stream()
							.collect(Collectors.groupingBy(LearnedSourcePO::getSourceType));
					Set<Map.Entry<Integer, List<LearnedSourcePO>>> entrySetTemp = groupBySourceType.entrySet();
					// グループしたentrySetをループする
					for (Map.Entry<Integer, List<LearnedSourcePO>> entryTemp : entrySetTemp) {
						List<LearnedSourcePO> learnedSourcePOListTemp2 = entryTemp.getValue();
						int sourceType = entryTemp.getKey();
						// 勉強完了の資料の件数
						int learnedSource = learnedSourcePOListTemp2.stream()
								.filter(learnedSourcePO -> (0 == BigDecimal.valueOf(100)
										.compareTo(learnedSourcePO.getLearnPercent())))
								.collect(Collectors.toList()).size();
						// 資料の総件数
						int source = learnedSourcePOListTemp2.size();
						// 勉強完了の資料パーセント
						StringBuffer learnedTypePercent = new StringBuffer();
						learnedTypePercent.append(COMPLETE);
						learnedTypePercent.append(String.valueOf(learnedSource));
						learnedTypePercent.append("/");
						learnedTypePercent.append(TOTAL);
						learnedTypePercent.append(String.valueOf(source));
						// 勉強完了の資料リスト
						List<LearnedSourcePO> learnedSourcePOListTemp3 = learnedSourcePOListTemp2.stream()
								.filter(learnedSourcePO -> (0 == BigDecimal.valueOf(100)
										.compareTo(learnedSourcePO.getLearnPercent())))
								.collect(Collectors.toList());
						// 勉強完了の資料をループする
						for (LearnedSourcePO learnedSourcePO : learnedSourcePOListTemp3) {
							LearnedSourceItemVO learnedSourceItemVO = new LearnedSourceItemVO();
							BeanUtils.copyProperties(learnedSourcePO, learnedSourceItemVO);
							learnedSourceItemVO.setSourceLength(learnedSourcePO.getSourceLength());
							learnedSourceItemVO.setLearnedPercent(learnedTypePercent.toString());
							if (1 == sourceType) {
								learnedSourceVideoItemVOListTemp.add(learnedSourceItemVO);
							} else if (2 == sourceType) {
								learnedSourceTextItemVOListTemp.add(learnedSourceItemVO);
							}
						}
						learnedSourceVO.setVideoList(learnedSourceVideoItemVOListTemp);
						learnedSourceVO.setTextList(learnedSourceTextItemVOListTemp);
					}
					learnedSourceVOList.add(learnedSourceVO);
				} else {
					continue;
				}
			}
		}
		return learnedSourceVOList;
	}
}

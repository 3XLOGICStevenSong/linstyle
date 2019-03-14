/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: TrainItemService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月6日 下午1:55:59
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service.impl;

import java.math.BigDecimal;
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
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.dbridge.jtraining.dao.po.LearnRecordPO;
import cn.com.dbridge.jtraining.dao.po.TrainInfoPO;
import cn.com.dbridge.jtraining.dao.po.TrainInformationPO;
import cn.com.dbridge.jtraining.dao.po.TrainItemPO;
import cn.com.dbridge.jtraining.dao.po.TrainItemQueryPO;
import cn.com.dbridge.jtraining.dao.po.TrainListQueryPO;
import cn.com.dbridge.jtraining.dao.po.TrainMaterialPO;
import cn.com.dbridge.jtraining.dao.po.TrainSourceItemPO;
import cn.com.dbridge.jtraining.dao.po.TrainSourcePO;
import cn.com.dbridge.jtraining.dao.po.TrainSourceRalationPO;
import cn.com.dbridge.jtraining.dao.respository.LearnRecordPOMapper;
import cn.com.dbridge.jtraining.dao.respository.TrainItemPOMapper;
import cn.com.dbridge.jtraining.dao.respository.TrainSourcePOMapper;
import cn.com.dbridge.jtraining.dao.respository.TrainSourceRalationPOMapper;
import cn.com.dbridge.jtraining.framework.dto.PageDTO;
import cn.com.dbridge.jtraining.framework.dto.TeacherQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemUpdateDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainListDTO;
import cn.com.dbridge.jtraining.framework.exception.CustomException;
import cn.com.dbridge.jtraining.framework.vo.CategoryItemVO;
import cn.com.dbridge.jtraining.framework.vo.ItemVO;
import cn.com.dbridge.jtraining.framework.vo.MyTrainItemVO;
import cn.com.dbridge.jtraining.framework.vo.TeacherInformation;
import cn.com.dbridge.jtraining.framework.vo.TeacherVO;
import cn.com.dbridge.jtraining.framework.vo.TrainCategoryInformationVO;
import cn.com.dbridge.jtraining.framework.vo.TrainInformationVO;
import cn.com.dbridge.jtraining.framework.vo.TrainItemVO;
import cn.com.dbridge.jtraining.framework.vo.TrainMaterialVO;
import cn.com.dbridge.jtraining.framework.vo.TrainSourceItemTVO;
import cn.com.dbridge.jtraining.framework.vo.TrainSourceItemVO;
import cn.com.dbridge.jtraining.framework.vo.TrainSourceTVO;
import cn.com.dbridge.jtraining.framework.vo.TrainSourceVO;
import cn.com.dbridge.jtraining.service.TrainItemService;

/**
 * @ClassName:  TrainItemService
 * @Description:トレーニング情報実装クラス
 * @author: 陈健飞
 * @date:   2018年12月6日 下午1:55:59
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class TrainItemServiceImpl implements TrainItemService {

    @Autowired
    private TrainItemPOMapper trainItemPOMapper;
    @Autowired
    private DownLoadList downLoadList;
    // TODO
    @Autowired
    private TrainSourceRalationPOMapper trainSourceRalationPOMapper;
    @Autowired
    private TrainSourcePOMapper trainSourcePOMapper;
    @Autowired
    private LearnRecordPOMapper learnRecordPOMapper;

    /**后台
     * 
     * Title: queryTrainItem
     * Description: クエリのトレーニング情報
     * @param trainItemQueryDTO
     * @return トレーニング情報の結果
     * @see cn.com.dbridge.jtraining.service.TrainItemService#queryTrainItem(cn.com.dbridge.jtraining.framework.dto.TrainItemQueryDTO)
     */
    @Override
    public List<TrainItemVO> queryTrainItem(
            TrainItemQueryDTO trainItemQueryDTO) {
        //クエリ条件のインスタンス化
        TrainItemQueryPO trainItemQueryPO = new TrainItemQueryPO();
        //設定トレーニング種別
        trainItemQueryPO.setTrainType(trainItemQueryDTO.getTrainType());
        //開始時刻を設定する
        trainItemQueryPO.setStratTime(trainItemQueryDTO.getStratTime());
        //終了時間を設定する
        trainItemQueryPO.setEndTime(trainItemQueryDTO.getEndTime());
        Integer offset = trainItemQueryDTO.getOffset();
        Integer limit = trainItemQueryDTO.getLimit();
        if ((offset != null) && (limit != null)) {
            PageHelper.startPage(offset, limit);
        }
        //クエリ条件によるトレーニング情報のクエリ
        List<TrainInformationPO> trainInformationPOList = trainItemPOMapper
                .selectList(trainItemQueryPO);
        PageInfo<TrainInformationPO> page = new PageInfo<TrainInformationPO>(
                trainInformationPOList);
        //トレーニング情報結果クラスを示すステートメント
        TrainItemVO trainItemVO = null;
        List<TrainItemVO> trainItemVOList = new ArrayList<TrainItemVO>();
        for (TrainInformationPO trainInformationPO : trainInformationPOList) {
            //インスタンス化された表示トレーニング情報結果クラス
            trainItemVO = new TrainItemVO();
            //トレーニング情報の結果に入力データを格納する
            BeanUtils.copyProperties(trainInformationPO, trainItemVO);
            trainItemVO.setCount(page.getTotal());
            //トレーニング情報の結果を対応するコレクションに追加する
            trainItemVOList.add(trainItemVO);
        }
        return trainItemVOList;
    }

    /**后台
     * 
     * Title: addTrainItem
     * Description: トレーニング情報を追加する
     * @param trainItemAddDTO
     * @return 影響を受けるレコードの数
     * @see cn.com.dbridge.jtraining.service.TrainItemService#addTrainItem(cn.com.dbridge.jtraining.framework.dto.TrainItemAddDTO)
     */
    @Override
    public int addTrainItem(TrainItemAddDTO trainItemAddDTO) {
        //トレーニングタイトル
        String trainTitle = trainItemAddDTO.getTrainTitle();
        TrainItemPO trainPO = trainItemPOMapper.selectCheckTitle(trainTitle);
        //トレーニング情報が空であるかどうかを判断する
        if (trainPO == null) {
            //インスタンス化されたトレーニング情報データベースのマッピング
            TrainItemPO trainItemPO = new TrainItemPO();
            //トレーニング情報エンティティクラスに入力データを格納する
            BeanUtils.copyProperties(trainItemAddDTO, trainItemPO);
            //作成日
            trainItemPO.setInsertDate(new Timestamp(System.currentTimeMillis()));
            //更新日
            trainItemPO.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            //作成人
            trainItemPO.setInsertPerson("Admin");
            //データを追加する
            trainItemPOMapper.insert(trainItemPO);
            //トレーニングID
            return trainItemPO.getTrainId();
        } else {
            throw new CustomException("トレーニングタイトルは既に存在します");
        }

    }

    /**后台
     * 
     * Title: updateTrainItem
     * Description: トレーニング情報を更新する
     * @param trainItemUpdateDTO
     * @return 影響を受けるレコードの数
     * @see cn.com.dbridge.jtraining.service.TrainItemService#updateTrainItem(cn.com.dbridge.jtraining.framework.dto.TrainItemUpdateDTO)
     */
    @Override
    public int updateTrainItemById(TrainItemUpdateDTO trainItemUpdateDTO) {
        //トレーニングタイトル
        String trainTitle = trainItemUpdateDTO.getTrainTitle();
        TrainItemPO trainPO = trainItemPOMapper.selectCheckTitle(trainTitle);
        //トレーニング情報が空であるかどうかを判断する
        if ((trainPO == null) || (trainPO.getTrainId()
                .equals(trainItemUpdateDTO.getTrainId()))) {
            //インスタンス化されたトレーニング情報データベースのマッピング
            TrainItemPO trainItemPO = new TrainItemPO();
            //トレーニング情報エンティティクラスに入力データを格納する
            BeanUtils.copyProperties(trainItemUpdateDTO, trainItemPO);
            //更新日
            trainItemPO.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            //更新人
            trainItemPO.setUpdatePerson("Admin");
            //影響を受けるレコードの数
            return trainItemPOMapper.updateByPrimaryKey(trainItemPO);
        } else {
            throw new CustomException("トレーニングタイトルは既に存在します");
        }
    }

    /**后台
     * 
     * Title: getTrainItem
     * Description: 主キーを使用してトレーニング情報とトレーニング資料を検索する
     * @param trainId
     * @return トレーニング情報とトレーニング資料の情報の結果
     * @see cn.com.dbridge.jtraining.service.TrainItemService#getTrainItem(int)
     */
    @Override
    public TrainItemVO getTrainItemById(Integer trainId) {
        //プライマリキーによるトレーニング情報の照会
        TrainItemPO trainItemPO = trainItemPOMapper.selectByPrimaryKey(trainId);
        //トレーニング情報とトレーニング資料をインスタンス化して結果を表示する
        TrainItemVO trainItemVO = new TrainItemVO();
        //教師情報収集をインスタンス化する
        List<TeacherInformation> teacherInformationVOList = new ArrayList<TeacherInformation>();
        //クエリ結果が空であるかどうかを判断する
        if (trainItemPO != null) {
            //照会結果を表示結果クラスに保管する
            BeanUtils.copyProperties(trainItemPO, trainItemVO);
            //先生の情報収集を取得する
            teacherInformationVOList = downLoadList
                    .queryUser(trainItemPO.getTrainType());
        }
        //プライマリキーによるトレーニング情報の照会
        List<TrainMaterialPO> trainMaterialPOList = trainItemPOMapper
                .selectTrainMaterialList(trainId);
        //トレーニング教材の結果クラスを示すステートメント
        TrainMaterialVO trainMaterialVO = null;
        //トレーニング教材の結果セットを示すステートメント
        List<TrainMaterialVO> trainMaterialVOList = new ArrayList<TrainMaterialVO>();
        for (TrainMaterialPO trainMaterialPO : trainMaterialPOList) {
            //インスタンス化されたトレーニング教材の結果セット
            trainMaterialVO = new TrainMaterialVO();
            //データを表示するトレーニング教材を保存する
            BeanUtils.copyProperties(trainMaterialPO, trainMaterialVO);
            //表示データの結果を対応するコレクションに追加する
            trainMaterialVOList.add(trainMaterialVO);
        }
        //トレーニングカテゴリの情報収集を取得する
        List<TrainCategoryInformationVO> trainCategoryInformationVOList = downLoadList
                .queryTrainCategoryList();
        //トレーニング資料の設定
        trainItemVO.setTrainMaterialVOList(trainMaterialVOList);
        //トレーニングカテゴリ情報収集を設定する
        trainItemVO.setTrainCategoryInformationVOList(
                trainCategoryInformationVOList);
        //教師情報収集を設定する
        trainItemVO.setTeacherInformationVOList(teacherInformationVOList);
        return trainItemVO;
    }

    /**后台
     * 
     * Title: deleteTrainItem
     * Description: トレーニング情報の削除
     * @param trainId
     * @return 影響を受けるレコードの数
     * @see cn.com.dbridge.jtraining.service.TrainItemService#deleteTrainItem(int)
     */
    @Override
    public int deleteTrainItem(Integer trainId) {
        List<TrainSourceRalationPO> trainSourceRalationPOList = trainSourceRalationPOMapper
                .selectByTrainId(trainId);
        if (CollectionUtils.isEmpty(trainSourceRalationPOList)) {
            //影響を受けるレコードの数
            return trainItemPOMapper.deleteByPrimaryKey(trainId);
        } else {
            throw new CustomException("最初にトレーニング教材を削除してください");
        }
    }

    /**后台
     * 
     * Title: queryCategoryItem
     * Description: クエリ情報の初期化
     * @return トレーニング情報とトレーニングカテゴリの情報収集
     * @see cn.com.dbridge.jtraining.service.TrainItemService#queryCategoryItem()
     */
    @Override
    public CategoryItemVO queryCategoryItem(PageDTO pageDTO) {
        Integer offset = pageDTO.getOffset();
        Integer limit = pageDTO.getLimit();
        if ((offset != null) && (limit != null)) {
            PageHelper.startPage(offset, limit);
        }
        //クエリのトレーニング情報
        List<TrainItemPO> trainItemPOList = trainItemPOMapper.selectAll();
        PageInfo<TrainItemPO> page = new PageInfo<TrainItemPO>(trainItemPOList);
        //トレーニング情報とトレーニングカテゴリ情報を示すステートメント
        TrainInformationVO trainInformationVO = null;
        //表示トレーニング情報とトレーニングカテゴリ情報収集のインスタンス化
        List<TrainInformationVO> trainInformationVOList = new ArrayList<TrainInformationVO>();
        for (TrainItemPO trainItemPO : trainItemPOList) {
            //トレーニング情報とトレーニングカテゴリ情報の表示をインスタンス化する
            trainInformationVO = new TrainInformationVO();
            //表示結果クラスにデータを格納する
            BeanUtils.copyProperties(trainItemPO, trainInformationVO);
            //表示結果を対応するコレクションに追加する
            trainInformationVOList.add(trainInformationVO);
        }
        //トレーニングカテゴリの情報収集を取得する
        List<TrainCategoryInformationVO> trainCategoryInformationVOList = downLoadList
                .queryTrainCategoryList();
        //トレーニング情報とトレーニングカテゴリ情報のコレクションをインスタンス化する
        CategoryItemVO categoryItemVO = new CategoryItemVO();
        //トレーニング情報収集を設定する
        categoryItemVO.setTrainCategoryInformationVOList(
                trainCategoryInformationVOList);
        //トレーニング資料の設定
        categoryItemVO.setTrainInformationVOList(trainInformationVOList);
        categoryItemVO.setCount(page.getTotal());
        return categoryItemVO;
    }

    /**后台
     * 
     * Title: queryItemTeacher
     * Description: シード情報を初期化する
     * @return 種のコレクション
     * @see cn.com.dbridge.jtraining.service.TrainItemService#queryItem()
     */
    @Override
    public ItemVO queryItem() {
        //クエリの種類情報
        List<TrainCategoryInformationVO> trainCategoryInformationVOList = downLoadList
                .queryTrainCategoryList();
        //シード情報の表示結果のインスタンス化
        ItemVO itemVO = new ItemVO();
        //コレクションに追加する
        itemVO.setTrainCategoryInformationVOList(
                trainCategoryInformationVOList);
        return itemVO;
    }

    /**后台
     * 
     * Title: queryTeacher
     * Description: すべての関連する教師情報をカテゴリ別に問い合わせる
     * @param trainType
     * @return 教師情報収集
     * @see cn.com.dbridge.jtraining.service.TrainItemService#queryTeacher(java.lang.Integer)
     */
    @Override
    public TeacherVO queryTeacher(TeacherQueryDTO teacherQueryDTO) {
        //カテゴリー別教師情報収集の質問
        List<TeacherInformation> teacherInformationVOList = downLoadList
                .queryUser(teacherQueryDTO.getTrainType());
        //インスタンス化された教師の表示結果
        TeacherVO teacherVO = new TeacherVO();
        //コレクションに追加する
        teacherVO.setTeacherInformationVOList(teacherInformationVOList);
        return teacherVO;
    }
    
    /**后台
     * 
     * Title: getTrainSource
     * Description: 指定のトレーニングを取得した研修資料の一覧
     * @param trainList
     * @return トレーニング資料情報
     * @see cn.com.dbridge.jtraining.service.TrainItemService#getTrainSource(cn.com.dbridge.jtraining.framework.dto.TrainListDTO)
     */
    @Override
    @Transactional(readOnly = true)
    public TrainSourceVO getTrainSource(TrainListDTO trainListDTO) {
        TrainSourceVO trainSourceVO = new TrainSourceVO();
        // クエリ条件によるトレーニング情報のクエリ
        TrainInfoPO trainInfoPO = trainItemPOMapper
                .selectTrainInfo(trainListDTO.getTrainId());
        if (null != trainInfoPO) {
            trainSourceVO.setTrainId(trainListDTO.getTrainId());
            trainSourceVO.setTrainTitle(trainInfoPO.getTrainTitle());
            trainSourceVO.setTrainDesc(trainInfoPO.getTrainDesc());
            trainSourceVO.setTeacherName(trainInfoPO.getName());
            trainSourceVO.setTeacherDesc(trainInfoPO.getRemarks());
            trainSourceVO.setTeacherDraw(trainInfoPO.getPersonDraw());
            // トレーニング項目資料関連表を検索する
            List<TrainSourceRalationPO> trainSourceRalationPOList = trainSourceRalationPOMapper
                    .selectByTrainId(trainListDTO.getTrainId());
            if (null != trainSourceRalationPOList
                    && !trainSourceRalationPOList.isEmpty()) {
                List<TrainSourceItemVO> trainSourceItemVOListTemp = new ArrayList<TrainSourceItemVO>();
                // トレーニング項目資料関連表のデータをループする
                for (TrainSourceRalationPO trainSourceRalationPO : trainSourceRalationPOList) {
                    // トレーニング資料表を検索する
                    TrainSourcePO trainSourcePO = trainSourcePOMapper
                            .selectByPrimaryKey(
                                    trainSourceRalationPO.getSourceNo());
                    if (null != trainSourcePO) {
                        TrainSourceItemVO trainSourceItemVO = new TrainSourceItemVO();
                        BeanUtils.copyProperties(trainSourcePO,
                                trainSourceItemVO);
						LearnRecordPO learnRecordPO = learnRecordPOMapper
								.selectByNo(trainListDTO.getStudentId(),
										trainListDTO.getTrainId(),
										trainSourcePO.getSourceNo());
						if (null != learnRecordPO) {
							trainSourceItemVO.setSourceLength(
									trainSourcePO.getSourceLength());
							trainSourceItemVO.setSourceLearnLength(
									learnRecordPO.getSourceLearnLength());
							trainSourceItemVO.setLearnPercent(
									learnRecordPO.getLearnPercent());
						}
						else {
							trainSourceItemVO.setSourceLength(trainSourcePO.getSourceLength());
							trainSourceItemVO.setSourceLearnLength(0);
							trainSourceItemVO
									.setLearnPercent(BigDecimal.valueOf(0));
						}
                        // トレーニング資料のリストを作成する
                        trainSourceItemVOListTemp.add(trainSourceItemVO);
                    }
                }
                // トレーニング資料種別名称でトレーニング資料のリストをグループする
                Map<Integer, List<TrainSourceItemVO>> groupBy = trainSourceItemVOListTemp
                        .stream().collect(Collectors
                                .groupingBy(TrainSourceItemVO::getSourceType));
                Set<Map.Entry<Integer, List<TrainSourceItemVO>>> entrySet = groupBy
                        .entrySet();
                // グループしたトレーニング資料のリストをループする
                for (Map.Entry<Integer, List<TrainSourceItemVO>> entry : entrySet) {
                    List<TrainSourceItemVO> trainSourceItemVOList = entry
                            .getValue();
                    int sourceType = entry.getKey();
                    TrainSourceItemVO trainSourceItemVOTemp = new TrainSourceItemVO();
                    if (1 == sourceType) {
                        TrainListQueryPO trainListQueryPO = new TrainListQueryPO();
                        BeanUtils.copyProperties(trainListDTO,
                                trainListQueryPO);
                        trainListQueryPO.setSourceType(1);
                        // 最新の勉強資料を取得する
                        TrainSourceItemPO trainSourceItemPO = learnRecordPOMapper
                                .selectSource(trainListQueryPO);
                        if (null != trainSourceItemPO) {
                            BeanUtils.copyProperties(trainSourceItemPO,
                                    trainSourceItemVOTemp);
                            // 最新の勉強資料を追加する
                            trainSourceItemVOTemp.setWatchFlage(1);
                            // TODO
//                            for (TrainSourceItemVO itemVO : trainSourceItemVOList) {
//                            	if (trainSourceItemPO.getSourceNo().equals(itemVO.getSourceNo())) {
//                                	trainSourceItemVOList.remove(itemVO);
//                                }
//                            }
                            trainSourceItemVOList.add(trainSourceItemVOTemp);
                        }
                        trainSourceVO.setVideoList(trainSourceItemVOList);
                    } else if (2 == sourceType) {
                        TrainListQueryPO trainListQueryPO = new TrainListQueryPO();
                        BeanUtils.copyProperties(trainListDTO,
                                trainListQueryPO);
                        trainListQueryPO.setSourceType(2);
                        // 最新の勉強資料を取得する
                        TrainSourceItemPO trainSourceItemPO = learnRecordPOMapper
                                .selectSource(trainListQueryPO);
                        if (null != trainSourceItemPO) {
                            BeanUtils.copyProperties(trainSourceItemPO,
                                    trainSourceItemVOTemp);
                            // 最新の勉強資料を追加する
                            trainSourceItemVOTemp.setWatchFlage(1);
                            // TODO
//                            for (TrainSourceItemVO itemVO : trainSourceItemVOList) {
//                            	if (trainSourceItemPO.getSourceNo().equals(itemVO.getSourceNo())) {
//                                	trainSourceItemVOList.remove(itemVO);
//                                }
//                            }
                            trainSourceItemVOList.add(trainSourceItemVOTemp);
                        }
                        trainSourceVO.setTextList(trainSourceItemVOList);
                    }
                }
            }
            if ((null == trainSourceVO.getVideoList())) {
                trainSourceVO.setVideoList(new ArrayList<TrainSourceItemVO>());
            }
            if ((null == trainSourceVO.getTextList())) {
                trainSourceVO.setTextList(new ArrayList<TrainSourceItemVO>());
            }
        }
        return trainSourceVO;
    }

    /**后台
     * 
     * Title: queryTrainSource
     * Description: 担当していた各トレーニング項目下のトレーニング資料情報一覧
     * @param teacherId
     * @return トレーニング資料情報
     * @see cn.com.dbridge.jtraining.service.TrainItemService#queryTrainSource(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public List<TrainSourceTVO> queryTrainSource(String teacherId) {
        List<TrainSourceTVO> trainSourceTVOList = new ArrayList<TrainSourceTVO>();
        // 教師idでトレーニング項目表を検索する
        List<TrainItemPO> trainItemPOList = trainItemPOMapper
                .selectByTeacherId(teacherId);
        if (null != trainItemPOList) {
            // トレーニング項目表のデータをループする
            for (TrainItemPO trainItemPO : trainItemPOList) {
                TrainSourceTVO trainSourceTVO = new TrainSourceTVO();
                trainSourceTVO.setTrainId(trainItemPO.getTrainId());
                trainSourceTVO.setTrainTitle(trainItemPO.getTrainTitle());
                // トレーニングidでトレーニング項目資料関連表を検索する
                List<TrainSourceRalationPO> trainSourceRalationPOList = trainSourceRalationPOMapper
                        .selectByTrainId(trainItemPO.getTrainId());
                if (null != trainSourceRalationPOList) {
                    List<TrainSourceItemTVO> trainSourceItemTVOListTemp = new ArrayList<TrainSourceItemTVO>();
                    // トレーニング項目資料関連表のデータをループする
                    for (TrainSourceRalationPO trainSourceRalationPO : trainSourceRalationPOList) {
                        // トレーニング資料番号でトレーニング資料表を検索する
                        TrainSourcePO trainSourcePO = trainSourcePOMapper
                                .selectByPrimaryKey(
                                        trainSourceRalationPO.getSourceNo());
                        if (null != trainSourcePO) {
                            TrainSourceItemTVO trainSourceItemTVO = new TrainSourceItemTVO();
                            BeanUtils.copyProperties(trainSourcePO,
                                    trainSourceItemTVO);
                            // トレーニング資料リストを追加する
                            trainSourceItemTVOListTemp.add(trainSourceItemTVO);
                        }
                    }
                    // トレーニング資料種別でトレーニング資料リストをグループする
                    Map<Integer, List<TrainSourceItemTVO>> groupBy = trainSourceItemTVOListTemp
                            .stream().collect(Collectors.groupingBy(
                                    TrainSourceItemTVO::getSourceType));
                    Set<Map.Entry<Integer, List<TrainSourceItemTVO>>> entrySet = groupBy
                            .entrySet();
                    // グループしたトレーニング資料リストをループする
                    for (Map.Entry<Integer, List<TrainSourceItemTVO>> entry : entrySet) {
                        List<TrainSourceItemTVO> trainSourceItemTVOList = entry
                                .getValue();
                        int sourceType = entry.getKey();
                        if (1 == sourceType) {
                            trainSourceTVO.setVideoList(trainSourceItemTVOList);
                        } else if (2 == sourceType) {
                            trainSourceTVO.setTextList(trainSourceItemTVOList);
                        }
                    }
                }
                trainSourceTVOList.add(trainSourceTVO);
            }
        }

        return trainSourceTVOList;
    }

    /**   
     * Title: selectAllTrainItem  
     * Description:すべてのトレーニング情報のリストを入手する
     * @author 郭健
     * @return トレーニング情報
     * @see cn.com.dbridge.jtraining.service.TrainItemService#selectAllTrainItem()   
     */
    @Override
    public List<MyTrainItemVO> selectAllTrainItem() {
        List<MyTrainItemVO> myTrainItemVOList = new ArrayList<MyTrainItemVO>();
        MyTrainItemVO myTrainItemVO = null;
        List<TrainItemPO> trainItemPOList = trainItemPOMapper
                .selectAllTrainItem();
        if (trainItemPOList.size() != 0) {
            for (TrainItemPO trainItemPO : trainItemPOList) {
                myTrainItemVO = new MyTrainItemVO();
                BeanUtils.copyProperties(trainItemPO, myTrainItemVO);
                myTrainItemVOList.add(myTrainItemVO);
            }
        }
        return myTrainItemVOList;
    }

}

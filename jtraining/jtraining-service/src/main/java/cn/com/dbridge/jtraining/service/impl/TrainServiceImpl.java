/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: TrainService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月6日 下午1:56:13
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service.impl;

import java.io.FileNotFoundException;
import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dbridge.jtraining.dao.po.LearnRecordPO;
import cn.com.dbridge.jtraining.dao.po.TrainSourcePO;
import cn.com.dbridge.jtraining.dao.po.TrainSourceRalationPO;
import cn.com.dbridge.jtraining.dao.respository.LearnRecordPOMapper;
import cn.com.dbridge.jtraining.dao.respository.TrainSourcePOMapper;
import cn.com.dbridge.jtraining.dao.respository.TrainSourceRalationPOMapper;
import cn.com.dbridge.jtraining.framework.dto.TrainAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainUpdateDTO;
import cn.com.dbridge.jtraining.framework.exception.CustomException;
import cn.com.dbridge.jtraining.framework.vo.TrainVO;
import cn.com.dbridge.jtraining.service.TrainService;

/**
 * @ClassName:  TrainService
 * @Description:トレーニング教材の実装クラス
 * @author: 陈健飞
 * @date:   2018年12月6日 下午1:56:13
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainSourcePOMapper trainSourcePOMapper;
    @Autowired
    private TrainSourceRalationPOMapper trainSourceRalationPOMapper;
    @Autowired
    private LearnRecordPOMapper learnRecordPOMapper;

    /**后台
     * 
     * Title: addTrain
     * Description: データと関連するテーブルデータを追加する
     * @param trainAddDTO
     * @return トレーニング資料番号
     * @throws FileNotFoundException 
     * @see cn.com.dbridge.jtraining.service.TrainService#addTrain(cn.com.dbridge.jtraining.framework.dto.TrainAddDTO)
     */
    @Override
    @Transactional
    public int addTrain(TrainAddDTO trainAddDTO) {
        //トレーニング資料タイトル
        String sourceTitle = trainAddDTO.getSourceTitle();
        TrainSourcePO trainPO = trainSourcePOMapper
                .checkSourceTitle(sourceTitle);
        //トレーニング教材のタイトルが存在するかどうかを確認する
        if (trainPO == null) {
            //トレーニング教材データベースのマッピングをインスタンス化する
            TrainSourcePO trainSourcePO = new TrainSourcePO();
            //関連情報データベースのマッピングをインスタンス化する
            TrainSourceRalationPO trainSourceRalationPO = new TrainSourceRalationPO();
            //入力データを追加データに格納する
            BeanUtils.copyProperties(trainAddDTO, trainSourcePO);
            //作成日
            trainSourcePO.setInsertDate(new Timestamp(System.currentTimeMillis()));
            //作成人
            trainSourcePO.setInsertPerson("Admin");
            //追加された情報
            trainSourcePOMapper.insert(trainSourcePO);
            //入力データを関連情報に格納する
            BeanUtils.copyProperties(trainAddDTO, trainSourceRalationPO);
            //トレーニング資料番号を設定する
            trainSourceRalationPO.setSourceNo(trainSourcePO.getSourceNo());
            //関連するテーブルに追加する
            trainSourceRalationPOMapper.insert(trainSourceRalationPO);
            return trainSourcePO.getSourceNo();
        } else {
            throw new CustomException("データタイトルは既に存在します");
        }
    }

    /**后台
     * 
     * Title: updateTrain
     * Description: トレーニング教材情報を更新する
     * @param trainAddDTO
     * @return 影響を受けるレコードの数
     * @see cn.com.dbridge.jtraining.service.TrainService#updateTrain(cn.com.dbridge.jtraining.framework.dto.TrainAddDTO)
     */
    @Override
    public int updateTrain(TrainUpdateDTO trainUpdateDTO) {
        //トレーニング資料タイトル
        String sourceTitle = trainUpdateDTO.getSourceTitle();
        TrainSourcePO trainPO = trainSourcePOMapper
                .checkSourceTitle(sourceTitle);
        //トレーニング教材のタイトルが存在するかどうかを確認する
        if ((trainPO == null) || (trainPO.getSourceNo()
                .equals(trainUpdateDTO.getSourceNo()))) {
            //トレーニング教材データベースのマッピングをインスタンス化する
            TrainSourcePO trainSourcePO = new TrainSourcePO();
            //入力データを追加データに格納する
            BeanUtils.copyProperties(trainUpdateDTO, trainSourcePO);
            //更新日
            trainSourcePO.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            //更新人
            trainSourcePO.setUpdatePerson("Admin");
            //影響を受けるレコードの数
            return trainSourcePOMapper.updateByPrimaryKey(trainSourcePO);
        } else {
            throw new CustomException("データタイトルは既に存在します");
        }
    }

    /**后台
     * 
     * Title: deleteTrain
     * Description: トレーニング資料および関連情報を削除する
     * @param sourceNo
     * @see cn.com.dbridge.jtraining.service.TrainService#deleteTrain(java.lang.Integer)
     */
    @Override
    @Transactional
    public void deleteTrain(Integer sourceNo) {
        LearnRecordPO learnRecordPO = learnRecordPOMapper
                .checkSourceNo(sourceNo);
        if (learnRecordPO == null) {
            //トレーニングプロファイル情報を削除する
            trainSourcePOMapper.deleteByPrimaryKey(sourceNo);
            //関連情報の削除
            trainSourceRalationPOMapper.deleteByPrimaryKey(sourceNo);
        } else {
            throw new CustomException("この情報は使用中です");
        }
    }

    /**后台
     * 
     * Title: getTrain
     * Description: プライマリキーによるトレーニング情報の照会
     * @param sourceNo
     * @return トレーニング資料の情報の結果
     * @see cn.com.dbridge.jtraining.service.TrainService#getTrain(java.lang.Integer)
     */
    @Override
    public TrainVO getTrain(Integer sourceNo) {
        //プライマリキーによるトレーニング情報の照会
        TrainSourcePO trainSourcePO = trainSourcePOMapper
                .selectByPrimaryKey(sourceNo);
        //トレーニング教材情報の結果をインスタンス化する
        TrainVO trainVO = new TrainVO();
        //クエリ結果が空であるかどうかを判断する
        if (trainSourcePO != null) {
            //トレーニングデータの結果にデータを格納する
            BeanUtils.copyProperties(trainSourcePO, trainVO);
        }
        return trainVO;
    }
}

/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: TrainCategoryService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月6日 下午1:55:42
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.jtraining.dao.po.TrainCategoryPO;
import cn.com.dbridge.jtraining.dao.respository.TrainCategoryPOMapper;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryUpdateDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryUpdateStatusDTO;
import cn.com.dbridge.jtraining.framework.exception.CustomException;
import cn.com.dbridge.jtraining.framework.vo.TrainCategoryVO;
import cn.com.dbridge.jtraining.service.TrainCategoryService;

/**
 * @ClassName:  TrainCategoryService
 * @Description:トレーニングカテゴリ情報実装クラス
 * @author: 陈健飞
 * @date:   2018年12月6日 下午1:55:42
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class TrainCategoryServiceImpl implements TrainCategoryService {

    @Autowired
    private TrainCategoryPOMapper trainCategoryPOMapper;

    /**后台
     * 
     * Title: addTrainCategory
     * Description: トレーニングカテゴリを追加する
     * @param trainAddDTO
     * @return 影響を受けるレコードの数
     * @throws CustomException 
     * @see cn.com.dbridge.jtraining.service.TrainCategoryService#addTrainCategory(cn.com.dbridge.jtraining.framework.dto.TrainAddDTO)
     */
    @Override
    public int addTrainCategory(TrainCategoryAddDTO trainCategoryAddDTO) {
        TrainCategoryPO trainPO = trainCategoryPOMapper
                .selectCheck(trainCategoryAddDTO.getTypeName());
        //シード情報が空であるかどうかを判断する
        if (trainPO == null) {
            //インスタンス化されたトレーニングカテゴリのデータマッピング
            TrainCategoryPO trainCategoryPO = new TrainCategoryPO();
            //エンティティクラスに入力データを格納する
            BeanUtils.copyProperties(trainCategoryAddDTO, trainCategoryPO);
            //有効フラグ(0:有効 1:無効)
            trainCategoryPO.setUseStatus((byte) 0);
            //録音時間を設定する
            trainCategoryPO.setInsertTime(new Date());
            //作成日
            trainCategoryPO.setInsertDate(new Timestamp(System.currentTimeMillis()));
            //作成人
            trainCategoryPO.setInsertPerson("Admin");
            //影響を受けるレコードの数
            return trainCategoryPOMapper.insert(trainCategoryPO);
        } else {
            throw new CustomException("種情報は既に存在します");
        }

    }

    /**后台
     * 
     * Title: updateTrainCategory
     * Description: ステータスの更新
     * @param trainCategoryUpdateStatusDTO
     * @return 影響を受けるレコードの数
     * @see cn.com.dbridge.jtraining.service.TrainCategoryService#updateTrainCategory(java.lang.Byte)
     */
    @Override
    public int updateTrainCategory(
            TrainCategoryUpdateStatusDTO trainCategoryUpdateStatusDTO) {
        //インスタンス化されたトレーニングカテゴリのデータマッピング
        TrainCategoryPO trainCategoryPO = new TrainCategoryPO();
        //更新するステータスを設定する
        trainCategoryPO
                .setUseStatus(trainCategoryUpdateStatusDTO.getUseStatus());
        //主キーを設定する
        trainCategoryPO
                .setTrainType(trainCategoryUpdateStatusDTO.getTrainType());
        //影響を受けるレコードの数
        return trainCategoryPOMapper.updateByPrimaryKey(trainCategoryPO);

    }

    /**后台
     * 
     * Title: updateTrainCategoryById
     * Description: トレーニングカテゴリを更新する
     * @param trainUpdateDTO
     * @return 影響を受けるレコードの数
     * @see cn.com.dbridge.jtraining.service.TrainCategoryService#updateTrainCategoryById(cn.com.dbridge.jtraining.framework.dto.TrainUpdateDTO)
     */
    @Override
    public int updateTrainCategoryById(
            TrainCategoryUpdateDTO trainCategoryUpdateDTO) {
        TrainCategoryPO trainPO = trainCategoryPOMapper
                .selectCheck(trainCategoryUpdateDTO.getTypeName());
        //シード情報が空であるかどうかを判断する
        if ((trainPO == null) || (trainPO.getTrainType()
                .equals(trainCategoryUpdateDTO.getTrainType()))) {
            //インスタンス化されたトレーニングカテゴリのデータマッピング
            TrainCategoryPO trainCategoryPO = new TrainCategoryPO();
            //エンティティクラスに入力データを格納する
            BeanUtils.copyProperties(trainCategoryUpdateDTO, trainCategoryPO);
            //録音時間を設定する
            trainCategoryPO.setInsertTime(new Date());
            //更新日
            trainCategoryPO.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            //更新人
            trainCategoryPO.setUpdatePerson("Admin");
            //影響を受けるレコードの数
            return trainCategoryPOMapper.updateByPrimaryKey(trainCategoryPO);
        } else {
            throw new CustomException("種情報は既に存在します");
        }
    }

    /**后台
     * 
     * Title: queryTrainCategory
     * Description: クエリのトレーニングカテゴリ情報
     * @param trainCategoryQueryDTO
     * @return トレーニングカテゴリ情報の結果
     * @see cn.com.dbridge.jtraining.service.TrainCategoryService#queryTrainCategory(cn.com.dbridge.jtraining.framework.dto.TrainCategoryQueryDTO)
     */
    @Override
    public List<TrainCategoryVO> queryTrainCategory(
            TrainCategoryQueryDTO trainCategoryQueryDTO) {
        //すべてのトレーニングカテゴリ情報をデータベースに照会する
        List<TrainCategoryPO> trainCategoryPOList = trainCategoryPOMapper
                .selectAll();
        //トレーニングカテゴリを示すステートメント
        TrainCategoryVO trainCategoryVO = null;
        //インスタンス化された表示トレーニングカテゴリコレクション
        List<TrainCategoryVO> trainCategoryVOList = new ArrayList<TrainCategoryVO>();
        for (TrainCategoryPO trainCategoryPO : trainCategoryPOList) {
            //表示トレーニングのカテゴリをインスタンス化する
            trainCategoryVO = new TrainCategoryVO();
            //表示カテゴリカテゴリに入力データを格納する
            BeanUtils.copyProperties(trainCategoryPO, trainCategoryVO);
            //表示タイプを対応するコレクションに追加する
            trainCategoryVOList.add(trainCategoryVO);
        }
        return trainCategoryVOList;
    }
}

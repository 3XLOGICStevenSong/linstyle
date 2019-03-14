/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainCategoryService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 陈健飞 
 * @date:   2018年12月6日 下午1:55:42
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service;

import java.util.List;

import cn.com.dbridge.jtraining.framework.dto.TrainCategoryAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryUpdateDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryUpdateStatusDTO;
import cn.com.dbridge.jtraining.framework.vo.TrainCategoryVO;

/**
 * @ClassName:  TrainCategoryService
 * @Description: トレーニングタイプの情報インターフェイス
 * @author: 陈健飞
 * @date:   2018年12月6日 下午1:55:42
 * 
 * @Copyright:2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
public interface TrainCategoryService {

    /**后台
     * 
     * @Title: addTrainCategory
     * @Description: トレーニングカテゴリを追加する
     * @param trainCategoryAddDTO
     * @return 影響を受けるレコードの数 
     */
    int addTrainCategory(TrainCategoryAddDTO trainCategoryAddDTO);

    /**后台
     * 
     * @Title: updateTrainCategory
     * @Description: ステータスの更新
     * @param trainCategoryUpdateStatusDTO
     * @return 影響を受けるレコードの数
     */
    int updateTrainCategory(
            TrainCategoryUpdateStatusDTO trainCategoryUpdateStatusDTO);

    /**后台
     * 
     * @Title: updateTrainCategoryById
     * @Description: トレーニングカテゴリを更新する
     * @param trainCategoryUpdateDTO
     * @return 影響を受けるレコードの数
     */
    int updateTrainCategoryById(TrainCategoryUpdateDTO trainCategoryUpdateDTO);

    /**后台
     * 
     * @Title: queryTrainCategory
     * @Description: クエリのトレーニングカテゴリ情報
     * @param trainCategoryQueryDTO
     * @return トレーニングカテゴリ情報の結果
     */
    List<TrainCategoryVO> queryTrainCategory(
            TrainCategoryQueryDTO trainCategoryQueryDTO);
}

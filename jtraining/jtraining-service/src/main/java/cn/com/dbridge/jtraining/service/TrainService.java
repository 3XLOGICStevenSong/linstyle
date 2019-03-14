/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 陈健飞 
 * @date:   2018年12月6日 下午1:56:13
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service;


import cn.com.dbridge.jtraining.framework.dto.TrainAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainUpdateDTO;
import cn.com.dbridge.jtraining.framework.vo.TrainVO;

/**
 * @ClassName:  TrainService
 * @Description: トレーニングデータインターフェイス
 * @author: 陈健飞
 * @date:   2018年12月6日 下午1:56:13
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public interface TrainService {
    /**后台
     * 
     * @Title: addTrain
     * @Description: トレーニング資料と関連情報を追加する
     * @param trainAddDTO 
     */
    int addTrain(TrainAddDTO trainAddDTO);

    /**后台
     * 
     * @Title: updateTrain
     * @Description: トレーニング教材情報を更新する
     * @param trainUpdateDTO
     * @return 影響を受けるレコードの数
     */
    int updateTrain(TrainUpdateDTO trainUpdateDTO);

    /**后台
     * 
     * @Title: deleteTrain
     * @Description: トレーニング資料および関連情報を削除する
     * @param sourceNo
     */
    void deleteTrain(Integer sourceNo);

    /**后台
     * 
     * @Title: getTrain
     * @Description: プライマリキーによるトレーニング情報の照会
     * @param sourceNo
     * @return トレーニング資料の情報の結果
     */
    TrainVO getTrain(Integer sourceNo);
}

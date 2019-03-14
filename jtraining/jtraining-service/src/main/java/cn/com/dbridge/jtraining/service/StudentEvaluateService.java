/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: StudentEvaluateService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description: 学生評価サービス層
 * @author: 郭健
 * @date: 2018年12月6日 1:55:28 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.service;

import cn.com.dbridge.jtraining.framework.dto.AddOrUpdateEvaluateDTO;
import cn.com.dbridge.jtraining.framework.dto.EvaluatePersonDTO;
import cn.com.dbridge.jtraining.framework.vo.EvaluateInfoVO;

/**
 * @ClassName:  StudentEvaluateService
 * @Description:学生評価サービス層
 * @author: 郭健
 * @date: 2018年12月6日 1:55:28 PM
 * 
 * @Copyright:2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
public interface StudentEvaluateService {
    /**
     * 
     * @Title: selectEvaluate 
     * @Description:学生に関する教師の評価情報を入手する
     * @param evaluatePersonDTO クエリ条件
     * @author 郭健
     * @return 評価情報
     */
    EvaluateInfoVO selectEvaluate(EvaluatePersonDTO evaluatePersonDTO);

    /**
     * 
     * @Title: addOrUpdateEvaluate 
     * @Description: 評価情報が既に存在する場合にはデータを更新し、評価情報が存在しない場合にはデータを追加する 
     * @param addOrUpdateEvaluateDTO 運用情報
     * @author 郭健
     * @return 影響を受けるレコードの数
     */
    int addOrUpdateEvaluate(AddOrUpdateEvaluateDTO addOrUpdateEvaluateDTO);
}

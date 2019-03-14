/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:MyTrainItemVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:トレーニング情報
 * @author:郭健
 * @date:2018年12月19日 3:17:00 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *               *注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.vo;

import lombok.Data;

/**
 * @ClassName:MyTrainItemVO
 * @Description:トレーニング情報
 * @author:郭健
 * @date:2018年12月19日 3:17:00 PM
 *
 * @Copyright:2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class MyTrainItemVO {
    /**
     * 培训ID
     */
    private Integer trainId;
    /**
     * 培训标题
     */
    private String trainTitle;
    /**
     * 培训介绍
     */
    private String trainDesc;
    /**
     * 培训画像
     */
    private String trainDraw;
}

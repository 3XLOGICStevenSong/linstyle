/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainItemQueryPO.java
 * @Package cn.com.dbridge.jtraining.dao.po
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月11日 下午2:24:17
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:  TrainItemQueryPO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月11日 下午2:24:17
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TrainItemQueryPO implements Serializable{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 8277029912629456487L;

    /**
     * トレーニング種別
     */
    private Integer trainType;
    
    /**
     * 开始时间
     */
    private Date stratTime;

    /**
     * 结束时间
     */
    private Date endTime;

}

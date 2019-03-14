/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:PhoneTeacherVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:
 * @author:郭健
 * @date:2018年12月18日 上午11:32:10
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.vo;

import java.util.List;

import cn.com.dbridge.jtraining.dao.po.MyUserForTeacherPO;
import lombok.Data;

/**
 * @ClassName:PhoneTeacherVO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:郭健
 * @date:2018年12月18日 上午11:32:10
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class PhoneTeacherVO {
    private String typeName;
    private List<MyUserForTeacherPO> myUserForTeacherPOList;
}

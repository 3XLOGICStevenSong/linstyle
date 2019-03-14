/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:TeacherInfoForChatVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:
 * @author:郭健
 * @date:2018年12月14日 下午4:04:39
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.vo;

import lombok.Data;

/**
 * @ClassName:TeacherInfoForChatVO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:郭健
 * @date:2018年12月14日 下午4:04:39
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TeacherInfoForChatVO {
    //培训ID
    private int trainId;
    //培训种别名称
    private String typeName;
    //培训介绍
    private String trainDesc;
    //培训资料种别名称
    private String sourceTypeName;
    //培训资料番号
    private int sourceNo;
    //培训资料标题
    private String sourceTitle;
    //培训资料介绍
    private String sourceDesc;
    //培训资料路径
    private String sourcePath;
}

/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title:  GroupOrder.java   
 * @Package cn.com.dbridge.lifecare.framework.validator.group   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 刘旭虹     
 * @date:   2019年1月22日 下午7:11:35   
 * @version V1.0 
 * @Copyright: 2019 dbridge.com.cn Inc. All rights reserved. 
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.framework.validator.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**   
 * @ClassName:  GroupOrder   
 * @Description:hibernate.validator出力优先级顺序
 * @author: 刘旭虹
 * @date:   2019年1月22日 下午7:11:35   
 *     
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目 
 */
@GroupSequence({GroupA.class, GroupB.class, Default.class})
public interface GroupOrder {

}

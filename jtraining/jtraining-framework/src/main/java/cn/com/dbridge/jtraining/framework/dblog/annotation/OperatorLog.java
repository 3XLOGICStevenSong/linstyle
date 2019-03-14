package cn.com.dbridge.jtraining.framework.dblog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @ClassName:  OperatorLog
 * @Description:系统日志注解 
 * @author: 陈健飞
 * @date:   2018年12月7日 上午10:16:42
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperatorLog {

    String module() default "";
    String methods() default "";
    String description() default "";

}

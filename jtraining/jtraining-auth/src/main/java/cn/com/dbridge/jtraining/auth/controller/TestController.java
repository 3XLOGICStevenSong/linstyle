/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: TestController.java
 * @Package cn.com.dbridge.jtraining.auth.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月10日 下午3:35:34
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.auth.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:  TestController
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 陈健飞
 * @date:   2018年12月10日 下午3:35:34
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *  注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
@RestController
@RequestMapping(value="/api")
public class TestController {
    @GetMapping(value = "/hello1")
    @RequiresAuthentication
    public String hello1() {
        return "hello world";
    }

    @GetMapping(value = "/hello2")
    public String hello2() {
        return "hello world";
    }
}

/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  User.java
 * @Package cn.com.dbridge.jtraining.redis
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 陈健飞 
 * @date:   2018年12月7日 下午2:13:43
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.redis;

import java.io.Serializable;

/**
 * @ClassName:  User
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 陈健飞
 * @date:   2018年12月7日 下午2:13:43
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2366545636195141527L;
    private String name;
    private Integer age;
    public User(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }
    /**
     * @Title:  getName
     * @Description: please write your description
     * @return: String
     */
    public String getName() {
        return name;
    }
    /**
     * @Title:  setName
     * @Description: please write your description
     * @return: String
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @Title:  getAge
     * @Description: please write your description
     * @return: Integer
     */
    public Integer getAge() {
        return age;
    }
    /**
     * @Title:  setAge
     * @Description: please write your description
     * @return: Integer
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}

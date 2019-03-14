package cn.com.dbridge.jtraining.auth.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import cn.com.dbridge.jtraining.auth.shiro.cache.CustomCacheManager;
import cn.com.dbridge.jtraining.auth.shiro.jwt.JwtFilter;

/**
 * 
 * @ClassName:  ShiroConfig
 * @Description:Shiro配置
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:35:29
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
@SpringBootConfiguration
public class ShiroConfig {

    /**
     * 
     * @Title: getManager
     * @Description: カスタムレルムの使用を設定し、Shiro独自のセッションを閉じる
     * @param userRealm
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // カスタムレルムを使用する 
        manager.setRealm(userRealm);
        // Shiroさん自身のセッションを閉じる
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        //カスタムキャッシュキャッシュを設定する
        manager.setCacheManager(new CustomCacheManager());
        return manager;
    }

    /**
     * 
     * @Title: factory
     * @Description: 独自のフィルタ、カスタムURLルールを追加する
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>(16);
        filterMap.put("jwt", new JwtFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> filterRuleMap = new HashMap<>(16);
        filterRuleMap.put("/*/api/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题，https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}

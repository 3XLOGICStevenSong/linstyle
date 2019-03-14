package cn.com.dbridge.jtraining.auth.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 
 * @ClassName:  CustomCacheManager
 * @Description:Shiroキャッシュマネージャを書き直す
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:36:53
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
public class CustomCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new CustomCache<K,V>();
    }
}

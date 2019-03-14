package cn.com.dbridge.lifecare.auth.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import cn.com.dbridge.lifecare.framework.constant.Constant;
import cn.com.dbridge.lifecare.framework.util.JedisUtil;
import cn.com.dbridge.lifecare.framework.util.JwtUtil;
import cn.com.dbridge.lifecare.framework.util.SerializableUtil;
import cn.com.dbridge.lifecare.framework.util.common.PropertiesUtil;
/**
 * 
 * @ClassName:  CustomCache
 * @Description: 自定义缓存对过期时间进行处理，缓存有效地存储临时对象，
 * 				   主要是为了提高应用程序的性能。Shiro的Cache需要用户自己去重写
 * @author: 陈健飞
 * @date:   2019年1月5日 上午11:47:53
 * 
 * @param <K>
 * @param <V>
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class CustomCache<K, V> implements Cache<K, V> {
    /**
     * 
     * @Title: getKey
     * @Description: 返回存储在指定项下的缓存值，如果该项没有缓存项，则返回空值。
     * @param key
     * @return
     */
    private String getKey(Object key) {
        return Constant.PREFIX_SHIRO_CACHE
                + JwtUtil.getClaim(key.toString(), Constant.ACCOUNT);
    }
    /**
     * 
     * Title: get
     * Description:获取缓存
     * @param key
     * @return
     * @throws CacheException
     * @see org.apache.shiro.cache.Cache#get(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object get(Object key) throws CacheException {
        if (!JedisUtil.exists(this.getKey(key))) {
            return null;
        }
        return JedisUtil.getObject(this.getKey(key));
    }
    /**
     * 
     * Title: put
     * Description:添加缓存项 并设置缓存过期时间
     * @param key
     * @param value
     * @return
     * @throws CacheException
     * @see org.apache.shiro.cache.Cache#put(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) throws CacheException {
        PropertiesUtil.readProperties("config.properties");
        String shiroCacheExpireTime = PropertiesUtil
                .getProperty("shiroCacheExpireTime");
        return JedisUtil.setObject(this.getKey(key), value,
                Integer.parseInt(shiroCacheExpireTime));
    }
    /**
     * 
     * Title: remove
     * Description:删除缓存
     * @param key
     * @return
     * @throws CacheException
     * @see org.apache.shiro.cache.Cache#remove(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object remove(Object key) throws CacheException {
        if (!JedisUtil.exists(this.getKey(key))) {
            return null;
        }
        JedisUtil.delKey(this.getKey(key));
        return null;
    }
    /**
     * 
     * Title: clear
     * Description:清除所有缓存
     * @throws CacheException
     * @see org.apache.shiro.cache.Cache#clear()
     */
    @Override
    public void clear() throws CacheException {
        JedisUtil.getJedis().flushDB();
    }
    /**
     * 
     * Title: size
     * Description:缓存的个数
     * @return
     * @see org.apache.shiro.cache.Cache#size()
     */
    @Override
    public int size() {
        Long size = JedisUtil.getJedis().dbSize();
        return size.intValue();
    }
    /**
     * 
     * Title: keys
     * Description:遍历缓存的所有Key信息
     * @return
     * @see org.apache.shiro.cache.Cache#keys()
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Set keys() {
        Set<byte[]> keys = JedisUtil.getJedis()
                .keys(new String("*").getBytes());
        Set<Object> set = new HashSet<Object>();
        for (byte[] bs : keys) {
            set.add(SerializableUtil.unserializable(bs));
        }
        return set;
    }
    /**
     * 
     * Title: values
     * Description:遍历缓存的所有Value信息
     * @return
     * @see org.apache.shiro.cache.Cache#values()
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Collection values() {
        Set<?> keys = this.keys();
        List<Object> values = new ArrayList<Object>();
        for (Object key : keys) {
            values.add(JedisUtil.getObject(this.getKey(key)));
        }
        return values;
    }
}

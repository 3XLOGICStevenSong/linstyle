package cn.com.dbridge.jtraining.auth.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import cn.com.dbridge.jtraining.auth.common.Constant;
import cn.com.dbridge.jtraining.auth.util.JwtUtil;
import cn.com.dbridge.jtraining.framework.util.JedisUtil;
import cn.com.dbridge.jtraining.framework.util.SerializableUtil;
import cn.com.dbridge.jtraining.framework.util.common.PropertiesUtil;

/**
 * 
 * @ClassName:  CustomCache
 * @Description: Shiroのキャッシュを書き換えて読み込みを保存する
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:36:44
 * 
 * @param <K>
 * @param <V>
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
public class CustomCache<K,V> implements Cache<K,V> {

    /**
     * 
     * @Title: getKey
     * @Description:  キャッシュされたキー名は、shiro:cache:accountとして取得されます。
     * @param key
     * @return
     */
    private String getKey(Object key){
        return Constant.PREFIX_SHIRO_CACHE + JwtUtil.getClaim(key.toString(), Constant.ACCOUNT);
    }

    /**
     * 
     * Title: get
     * Description:キャッシュを取得する
     * @param key
     * @return
     * @throws CacheException
     * @see org.apache.shiro.cache.Cache#get(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object get(Object key) throws CacheException {
        if(!JedisUtil.exists(this.getKey(key))){
            return null;
        }
        return JedisUtil.getObject(this.getKey(key));
    }

    /**
     * 
     * Title: put
     * Description:キャッシュを保存する
     * @param key
     * @param value
     * @return
     * @throws CacheException
     * @see org.apache.shiro.cache.Cache#put(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) throws CacheException {
        // 读取配置文件，获取Redis的Shiro缓存过期时间
        PropertiesUtil.readProperties("config.properties");
        String shiroCacheExpireTime = PropertiesUtil.getProperty("shiroCacheExpireTime");
        // 设置Redis的Shiro缓存
        return JedisUtil.setObject(this.getKey(key), value, Integer.parseInt(shiroCacheExpireTime));
    }

    /**
     * 
     * Title: remove
     * Description:キャッシュを削除
     * @param key
     * @return
     * @throws CacheException
     * @see org.apache.shiro.cache.Cache#remove(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object remove(Object key) throws CacheException {
        if(!JedisUtil.exists(this.getKey(key))){
            return null;
        }
        JedisUtil.delKey(this.getKey(key));
        return null;
    }

    /**
     * 
     * Title: clear
     * Description:すべてのキャッシュをクリアする
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
     * Description:キャッシュの数
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
     * Description:すべてのキーを取得する
     * @return
     * @see org.apache.shiro.cache.Cache#keys()
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Set keys() {
        Set<byte[]> keys = JedisUtil.getJedis().keys(new String("*").getBytes());
        Set<Object> set = new HashSet<Object>();
        for (byte[] bs : keys) {
            set.add(SerializableUtil.unserializable(bs));
        }
        return set;
    }

    /**
     * 
     * Title: values
     * Description:すべての値を取得する
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

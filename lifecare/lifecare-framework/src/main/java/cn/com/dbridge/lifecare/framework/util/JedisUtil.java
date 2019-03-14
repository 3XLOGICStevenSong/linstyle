package cn.com.dbridge.lifecare.framework.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.dbridge.lifecare.framework.constant.JedisConstant;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * @ClassName:  JedisUtil
 * @Description: JedisUtil(推荐存Byte数组，存Json字符串效率更慢)
 * @author: 陈健飞
 * @date:   2018年12月5日 下午1:11:54
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *  注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
public class JedisUtil {

    /**
     * 静态注入JedisPool连接池
     * 本来是正常注入JedisUtil，可以在Controller和Service层使用，但是重写Shiro的CustomCache无法注入JedisUtil
     * 现在改为静态注入JedisPool连接池，JedisUtil直接调用静态方法即可
     * https://blog.csdn.net/W_Z_W_888/article/details/79979103
     */
    private static JedisPool jedisPool;

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        JedisUtil.jedisPool = jedisPool;
    }

    /**
     * 获取Jedis实例
     * @param 
     * @return redis.clients.jedis.Jedis
     * @author Andy
     * @date 2018/9/4 15:47
     */
    public static synchronized Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new CustomException("获取Jedis资源异常:" + e.getMessage());
        }
    }

    /**
     * 
     * @Title: closePool
     * @Description: 释放Jedis资源
     */
    public static void closePool() {
        try {
            jedisPool.close();
        } catch (Exception e) {
            throw new CustomException("释放Jedis资源异常:" + e.getMessage());
        }
    }

    /**
     * 
     * @Title: getObject
     * @Description: 获取redis键值-object
     * @param key
     * @return
     */
    public static Object getObject(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = jedis.get(key.getBytes());
            if (StringUtil.isNotNull(bytes)) {
                return SerializableUtil.unserializable(bytes);
            }
        } catch (Exception e) {
            throw new CustomException("获取Redis键值getObject方法异常:key=" + key
                    + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 
     * @Title: setObject
     * @Description: 设置redis键值-object
     * @param key
     * @param value
     * @return
     */
    public static String setObject(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key.getBytes(),
                    SerializableUtil.serializable(value));
        } catch (Exception e) {
            throw new CustomException("设置Redis键值setObject方法异常:key=" + key
                    + " value=" + value + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
    * 
    * @Title: setObject
    * @Description: 设置redis键值-object-expiretime
    * @param key
    * @param value
    * @param expiretime
    * @return
    */
    public static String setObject(String key, Object value, int expiretime) {
        String result = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key.getBytes(),
                    SerializableUtil.serializable(value));
            if (JedisConstant.OK.equals(result)) {
                jedis.expire(key.getBytes(), expiretime);
            }
            return result;
        } catch (Exception e) {
            throw new CustomException("设置Redis键值setObject方法异常:key=" + key
                    + " value=" + value + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 
     * @Title: getJson
     * @Description: 获取redis键值-Json
     * @param key
     * @return
     */
    public static String getJson(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            throw new CustomException("获取Redis键值getJson方法异常:key=" + key
                    + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 
     * @Title: setJson
     * @Description: 设置redis键值-Json
     * @param key
     * @param value
     * @return
     */
    public static String setJson(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            throw new CustomException("设置Redis键值setJson方法异常:key=" + key
                    + " value=" + value + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 
     * @Title: setJson
     * @Description: 设置redis键值-Json-expiretime
     * @param key
     * @param value
     * @param expiretime
     * @return
     */
    public static String setJson(String key, String value, int expiretime) {
        String result = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key, value);
            if (JedisConstant.OK.equals(result)) {
                jedis.expire(key, expiretime);
            }
            return result;
        } catch (Exception e) {
            throw new CustomException("设置Redis键值setJson方法异常:key=" + key
                    + " value=" + value + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 
     * @Title: delKey
     * @Description:删除key
     * @param key
     * @return
     */
    public static Long delKey(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            throw new CustomException("删除Redis的键delKey方法异常:key=" + key
                    + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 
     * @Title: exists
     * @Description: key是否存在
     * @param key
     * @return
     */
    public static Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            throw new CustomException("查询Redis的键是否存在exists方法异常:key=" + key
                    + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

   /**
    * 
    * @Title: keysS
    * @Description: 模糊查询获取key集合(keys的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，生产不推荐使用)
    * @param key
    * @return
    */
    public static Set<String> keysS(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.keys(key);
        } catch (Exception e) {
            throw new CustomException("模糊查询Redis的键集合keysS方法异常:key=" + key
                    + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 
     * @Title: keysB
     * @Description: 模糊查询获取key集合(keys的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，生产不推荐使用)
     * @param key
     * @return
     */
    public static Set<byte[]> keysB(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.keys(key.getBytes());
        } catch (Exception e) {
            throw new CustomException("模糊查询Redis的键集合keysB方法异常:key=" + key
                    + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 
     * @Title: ttl
     * @Description:获取过期剩余时间
     * @param key
     * @return
     */
    public static Long ttl(String key) {
        Long result = -2L;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.ttl(key);
            return result;
        } catch (Exception e) {
            throw new CustomException("获取Redis键过期剩余时间ttl方法异常:key=" + key
                    + " cause=" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}

package com.project.appinterface.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {
	protected final static Logger logger = Logger.getLogger(JedisUtil.class);
	private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";//毫秒
    private static final Long RELEASE_SUCCESS = 1L;
    public static final String PAY_LOCK_KEY="PAY_LOCK_KEY";//支付锁
    
	private static  JedisPool jedisPool;

	@Bean
	public JedisPool getJedisPool(){
		return new JedisPool("127.0.0.1",6379);
	}

	@Autowired(required = true)
	public void setJedisPool(JedisPool jedisPool) {
		JedisUtil.jedisPool = jedisPool;
	}

	/**
	 * 对某个键的值自增
	 * @author liboyi
	 * @param key 键
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static long setIncr(String key, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result =jedis.incr(key);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("set "+ key + " = " + result);
		} catch (Exception e) {
			logger.warn("set "+ key + " = " + result);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	public static Set<String> keys(String key) {
		Set<String> value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (StringUtils.isNotEmpty(key)) {
				value = jedis.keys(key);
				logger.debug("keys "+ key + " = " + value);
			}
		} catch (Exception e) {
			logger.warn("get "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				value = jedis.get(key);
//				value = StringUtil.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
				logger.debug("get "+ key + " = " + value);
			}
		} catch (Exception e) {
			logger.warn("get "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public static byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				value = jedis.get(key);
				logger.debug("get "+ key + " = " + value);
			}
		} catch (Exception e) {
			logger.warn("get "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public static Object getObject(String key,Class<?> clazz) {
		Object value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(JSON.toJSONBytes(key))) {
				value = JSON.parseObject(((JSONObject) JSON.parse(jedis.get(JSON.toJSONBytes(key)))).toString(), clazz);
				logger.debug("getObject "+ key + " = " + value);
			}
		} catch (Exception e) {
			logger.warn("getObject "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 设置缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String set(String key, String value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.set(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("set "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("set "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	/**
	 * 设置缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String set(byte[] key, byte[] value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.set(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("set "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("set "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 设置缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String setObject(String key, Object value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.set(JSON.toJSONBytes(key), JSON.toJSONBytes(value));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setObject "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("setObject "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 获取List缓存
	 * @param key 键
	 * @return 值
	 */
	public static List<String> getList(String key) {
		List<String> value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				value = jedis.lrange(key, 0, -1);
				logger.debug("getList "+ key + " = " + value);
			}
		} catch (Exception e) {
			logger.warn("getList "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	
	/**
	 * 设置List缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static long setList(String key, List<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.rpush(key, (String[])value.toArray());
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setList "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("setList "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	
	/**
	 * 向List缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static long listAdd(String key, String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.rpush(key, value);
			logger.debug("listAdd "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("listAdd "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
 
	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public static Set<String> getSet(String key) {
		Set<String> value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				value = jedis.smembers(key);
				logger.debug("getSet "+ key + " = " + value);
			}
		} catch (Exception e) {
			logger.warn("getSet "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 设置Set缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static long setSet(String key, Set<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.sadd(key, (String[])value.toArray());
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setSet "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("setSet "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	
	/**
	 * 向Set缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static long setSetAdd(String key, String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.sadd(key, value);
			logger.debug("setSetAdd "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("setSetAdd "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
 
	
	/**
	 * 获取Map缓存
	 * @param key 键
	 * @return 值
	 */
	public static Map<String, String> getMap(String key) {
		Map<String, String> value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				value = jedis.hgetAll(key);
				logger.debug("getMap "+ key + " = " + value);
			}
		} catch (Exception e) {
			logger.warn("getMap "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	
	/**
	 * 设置Map缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String setMap(String key, Map<String, String> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.hmset(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setMap "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("setMap "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	
	/**
	 * 向Map缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static String mapPut(String key, Map<String, String> value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hmset(key, value);
			logger.debug("mapPut "+ key + " = " + value);
		} catch (Exception e) {
			logger.warn("mapPut "+ key + " = " + value);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 移除Map缓存中的值
	 * @param key 键
	 * @return
	 */
	public static long mapRemove(String key, String mapKey) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hdel(key, mapKey);
			logger.debug("mapRemove "+ key + " = " + mapKey);
		} catch (Exception e) {
			logger.warn("mapRemove "+ key + " = " + mapKey);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 移除Map缓存中的值
	 * @param key 键
	 * @return
	 */
	public static long mapObjectRemove(String key, String mapKey) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hdel(JSON.toJSONBytes(key), JSON.toJSONBytes(mapKey));
			logger.debug("mapObjectRemove "+ key + " = " + mapKey);
		} catch (Exception e) {
			logger.warn("mapObjectRemove "+ key + " = " + mapKey);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 判断Map缓存中的Key是否存在
	 * @param key 键
	 * @return
	 */
	public static boolean mapExists(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hexists(key, mapKey);
			logger.debug("mapExists "+ key + " = " + mapKey);
		} catch (Exception e) {
			logger.warn("mapExists "+ key + " = " + mapKey);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 判断Map缓存中的Key是否存在
	 * @param key 键
	 * @return
	 */
	public static boolean mapObjectExists(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hexists(JSON.toJSONBytes(key), JSON.toJSONBytes(mapKey));
			logger.debug("mapObjectExists "+ key + " = " + mapKey);
		} catch (Exception e) {
			logger.warn("mapObjectExists "+ key + " = " + mapKey);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 删除缓存
	 * @param key 键
	 * @return
	 */
	public static long del(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)){
				result = jedis.del(key);
				logger.debug("del "+ key );
			}else{
				logger.debug("del "+ key + "not exists");
			}
		} catch (Exception e) {
			logger.warn("del "+ key);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 删除缓存
	 * @param key 键
	 * @return
	 */
	public static long del(String[] key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.del(key);
			logger.debug("del "+ key );
		} catch (Exception e) {
			logger.warn("del "+ key);
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
 
	/**
	 * 删除缓存
	 * @param key 键
	 * @return
	 */
	public static long delObject(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.exists(key)){
				result = jedis.del(key);
				logger.debug("delObject "+ key );
			}else{
				logger.debug("delObject "+ key + "not exists");
			}
		} catch (Exception e) {
			logger.warn("delObject "+ key + "not exists");
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 缓存是否存在
	 * @param key 键
	 * @return
	 */
	public static boolean exists(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.exists(key);
			logger.debug("exists "+ key );
		} catch (Exception e) {
			logger.warn("exists "+ key );
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	/**
	 * 缓存是否存在
	 * @param key 键
	 * @return
	 */
	public static boolean existsObject(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.exists(JSON.toJSONBytes(key));
			logger.debug("existsObject "+ key );
		} catch (Exception e) {
			logger.warn("existsObject "+ key );
		} finally {
			jedisPool.returnResource(jedis);
		}
		return result;
	}
	
	/**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
    	Jedis jedis = null;
    	try {
    		jedis = jedisPool.getResource();
    		String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
            return false;
		} catch (Exception e) {
			logger.warn("获取锁失败,requestId="+requestId);
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
    }
    
    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(String lockKey, String requestId) {
    	Jedis jedis = null;
    	try {
    		jedis = jedisPool.getResource();
    		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
            return false;
		} catch (Exception e) {
			logger.warn("释放锁失败,requestId="+requestId);
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
    }
}

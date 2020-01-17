package fun.findhappytime.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * Created by zhangshu on 2017/9/14.
 */

public class RedisUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    /**
     * 通过 setnx + expire 命令，原子性给某个 key 上锁并设置过期时间
     * 上锁成功返回 true ，上锁失败返回 false.
     *
     * @param redisTemplate
     * @param key
     * @param expire
     * @return
     */

    public static boolean lock(RedisTemplate redisTemplate, String key, Integer expire) {
        RedisConnection connection = null;
        try {
            connection = redisTemplate.getConnectionFactory().getConnection();
            Jedis jedis = (Jedis) connection.getNativeConnection();
            return jedis.set(key, "1", "nx", "ex", expire) != null;
        } catch (Exception e) {
            LOGGER.error("上锁出错:{}", e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

}

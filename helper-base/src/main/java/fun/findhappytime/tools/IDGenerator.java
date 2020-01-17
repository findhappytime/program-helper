package fun.findhappytime.tools;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 使用前在配置文件内配置 DATA_CENTER_ID
 * <p>
 * Created by zhangshu on 2017/9/14.
 */
@Component
public class IDGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(IDGenerator.class);


    private static final String REDIS_WORK_ID_KEY = "GLOBAL:WORK:ID:";
    private static final String ID_FORMAT = "yyyyMMddHHmmss";

    private static Lock lock = new ReentrantLock();

    // data center，默认为 1
    private static int DATA_CENTER_ID = 1;
    private static int WORKER_ID = 0;
    // 最高支持同时1W台机器
    private static final int MAX_WORK_ID = 10000;
    // 最高每秒发号 100w
    private static final int MAX_COUNT = 999999;

    private static AtomicInteger COUNTER = new AtomicInteger(0);
    private static long MAX_TIME_SECOND;

    // Worker ID 字符串
    private static String WORKER_ID_STR;
    // data center 字符串
    private static String DATA_CENTER_STR;
    // 最长回退时间，120 秒
    private static int MAX_BACK_SECOND = 120;

    static {
        Date now = new Date();
        Long timeSecond = now.getTime() / 1000;
        MAX_TIME_SECOND = timeSecond;

    }

    private static LoadingCache cache = CacheBuilder.newBuilder()
            .expireAfterWrite(MAX_BACK_SECOND, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, AtomicInteger>() {
                @Override
                public AtomicInteger load(Long key) throws Exception {
                    return new AtomicInteger(0);
                }
            });


    @Autowired
    private StringRedisTemplate redis;

    @Value("${data.center.id}")
    public void setDataCenterId(int dataCenterId) {
        DATA_CENTER_ID = dataCenterId;
    }

    @PostConstruct
    public void init() {
        WORKER_ID = (int) (redis.opsForValue().increment(REDIS_WORK_ID_KEY + DATA_CENTER_ID, 1) % MAX_WORK_ID);
        WORKER_ID_STR = String.format("%04d", WORKER_ID);
        DATA_CENTER_STR = String.format("%03d", DATA_CENTER_ID);
    }


    /**
     * 1. 需要获取 dataCenterId 和 workeId
     */
    public static String getId(String prefix) {
        Date now = new Date();
        Long timeSecond = now.getTime() / 1000;

        Integer counter = 0;
        if (timeSecond > MAX_TIME_SECOND) {
            lock.lock();
            if (timeSecond > MAX_TIME_SECOND) {
                cache.put(MAX_TIME_SECOND, COUNTER);
                COUNTER = new AtomicInteger(0);
                MAX_TIME_SECOND = timeSecond;
            }
            lock.unlock();
        }
        if (timeSecond == MAX_TIME_SECOND) {
            counter = COUNTER.incrementAndGet();
        }
        // 时间回退时到 cache 里拿，或者直接抛出错误
        if (timeSecond < MAX_TIME_SECOND) {
            if (timeSecond + MAX_BACK_SECOND < MAX_TIME_SECOND) {
                throw new RuntimeException("时间回撤, 请稍后再试");
            }
            try {
                AtomicInteger historyCounter = (AtomicInteger) cache.get(timeSecond);
                counter = historyCounter.incrementAndGet();
            } catch (ExecutionException e) {
                LOGGER.error("取出缓存时出错");
            }
        }

        // 达到计数器上上限, 休眠半秒并重试
        if (counter >= MAX_COUNT) {
            try {
                Thread.sleep(500);
                return getId(prefix);
            } catch (InterruptedException e) {
                LOGGER.error("发号器休眠时发生错误:{}", e);
            }
        }

        String currentTimeStr = new SimpleDateFormat(ID_FORMAT, Locale.SIMPLIFIED_CHINESE).format(now);
        return prefix + currentTimeStr + DATA_CENTER_STR + WORKER_ID_STR + String.format("%06d", counter);
    }

}

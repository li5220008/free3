package util;

import org.apache.commons.lang.StringUtils;
import play.Logger;
import play.Play;
import play.exceptions.ConfigurationException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 数据库记录消息变化redis队列服务器
 * User: wenzhihong
 * Date: 13-4-3
 * Time: 上午11:14
 */
public class MsgChangeRedis {
    private static JedisPool pool;

    //初始化作为 记录变化 的队列的redis
    public synchronized static void init() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxActive(150);
            config.setMaxIdle(20);
            config.setMaxWait(6 * 1000);
            config.setNumTestsPerEvictionRun(5);
            String uri = Play.configuration.getProperty("msgchange.redis.url");
            if (StringUtils.isNotBlank(uri)) {
                URI redisUri;
                try {
                    redisUri = new URI(uri);
                } catch (URISyntaxException e) {
                    throw new ConfigurationException("Bad configuration for redis: unable to parse redis url (" + uri + ")");
                }

                String host = redisUri.getHost();
                int port = Protocol.DEFAULT_PORT;
                if (redisUri.getPort() > 0) {
                    port = redisUri.getPort();
                }
                String password = null;
                String userInfo = redisUri.getUserInfo();
                if (userInfo != null) {
                    String[] parsedUserInfo = userInfo.split(":");
                    password = parsedUserInfo[parsedUserInfo.length - 1];
                } else {
                    password = null;
                }

                String timeoutStr = Play.configuration.getProperty("msgchange.redis.timeout");
                int timeout;
                if (timeoutStr == null) {
                    timeout = Protocol.DEFAULT_TIMEOUT;
                } else {
                    timeout = Integer.parseInt(timeoutStr);
                }

                if (password == null) {
                    pool = new JedisPool(config, host, port, timeout);
                } else {
                    pool = new JedisPool(config, host, port, timeout, password);
                }
                Logger.info("连接数据库记录消息变化redis队列服务器[%s, %d]", host, port);
            } else {
                throw new RuntimeException("没有配置数据库记录消息变化redis队列服务器");
            }


        }
    }

    //销毁
    public synchronized static void destroy() {
        if (pool != null) {
            Logger.info("销毁到数据库记录消息变化redis队列服务器的连接");
            pool.destroy();
            pool = null;
        }
    }

    public static Jedis getConnection() {
        return pool.getResource();
    }

    public static void closeConnection(Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

    public static void closeBrokenConnection(Jedis jedis) {
        if (jedis != null) {
            pool.returnBrokenResource(jedis);
        }
    }
}

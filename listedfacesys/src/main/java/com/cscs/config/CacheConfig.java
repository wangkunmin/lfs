package com.cscs.config;

import com.cscs.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 使用Redis作为缓存。替换ehcache。
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

  @Value("${spring.redis.host}")
  String host;
  @Value("${spring.redis.port}")
  int port;
  @Value("${spring.redis.password}")
  String password;
  @Value("${spring.redis.sentinel.master}")
  String master;
  @Value("${spring.redis.sentinel.nodes}")
  String nodes;
  @Value("${spring.redis.timeout}")
  int timeout;

  static final RedisSentinelConfiguration SENTINEL_CONFIG = new RedisSentinelConfiguration()
      .master("T1") //
      .sentinel("106.14.30.97", 26379) //
      .sentinel("106.14.30.97", 26380) //
      .sentinel("106.14.30.97", 26381);

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory cf;
    if (StringUtil.isEmpty(master) || StringUtil.isEmpty(nodes)) {
      cf = new JedisConnectionFactory();
      cf.setHostName(host);
      cf.setPort(port);
      cf.setTimeout(timeout);
    } else {

      RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
      sentinelConfiguration.master(master);
      String[] nodes = this.nodes.split(",");
      for (String node : nodes) {
        String[] items = node.split(":");
        String ip = items[0].trim();
        int port = Integer.valueOf(items[1].trim());
        sentinelConfiguration.sentinel(ip, port);
      }
      cf = new JedisConnectionFactory(sentinelConfig());
    }

    if (!StringUtil.isEmpty(password)) {
      cf.setPassword(password);
    }

    return cf;
  }

  @Bean
  public RedisSentinelConfiguration sentinelConfig() {
    return SENTINEL_CONFIG;
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
    redisTemplate.setConnectionFactory(cf);
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    //设置缓存过期时间
    // cacheManager.setDefaultExpiration(60); //秒
    return cacheManager;
  }


}

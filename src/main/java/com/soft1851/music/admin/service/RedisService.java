package com.soft1851.music.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * RedisTemplate默认的序列化方案是JdkSerializationRedisSerializer
 * @author CRQ
 */
@Service
@Slf4j
public class RedisService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void testRedis(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
//        ops.set("niit", "soft");
        System.out.println(ops.get("niit"));
    }

    /**
     * 验证码存放
     * @param key 键
     * @param value 值
     * @param duration 有效时长
     */
    public void save(String key,String value,long duration){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key,value,duration);
        log.info("验证码存储在Redis中"+ops.get(key));
    }

    public String get(String key) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String value = ops.get(key);
        if (value != null) {
            return value;
        } else {
            return "";
        }
    }


}

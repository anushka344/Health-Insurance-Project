package com.health.insurance.services;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.health.insurance.entities.IdempotencyRecord;

import com.health.insurance.entities.IdempotencyRecord;
import com.health.insurance.repositories.IdempotencyRepository;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@Slf4j
public class IdempotencyService {

	 //private static final Duration TTL = Duration.ofMinutes(15);
	  // Inject TTL from application.properties
    	@Value("${idempotency.ttl.minutes}")
    	private long ttlMinutes;
	    private static final String KEY_PREFIX = "idemp:";
	    private static final Logger log = LoggerFactory.getLogger(IdempotencyService.class);

	    @Autowired
	    private StringRedisTemplate redisTemplate;

	    public Optional<String> getCachedResponse(String key, String requestHash) {
	        String redisKey = KEY_PREFIX + key;
	        
	        try {
	            String response = (String) redisTemplate.opsForHash().get(redisKey, requestHash);
	            log.info("✅ Returning response from Redis for key: {}", redisKey);
	            return Optional.ofNullable(response);
	        } catch (Exception e) {
	            log.error("⚠️ Redis GET failed for key: {}", redisKey, e);
	            return Optional.empty(); // fail gracefully
	        }
	    }

	    public void store(String key, String requestHash, String responseBody) {
	        String redisKey = KEY_PREFIX + key;
	        try {
	            redisTemplate.opsForHash().put(redisKey, requestHash, responseBody);
	            redisTemplate.expire(redisKey,  Duration.ofMinutes(ttlMinutes));
	            log.info("✅ Stored response in Redis for key: {}", redisKey);
	        } catch (Exception e) {
	            log.error("⚠️ Redis SET failed for key: {}", redisKey, e);
	        }
	    }

	    public boolean isKeyUsedWithDifferentBody(String key, String requestHash) {
	        String redisKey = KEY_PREFIX + key;
	        try {
	            Map<Object, Object> all = redisTemplate.opsForHash().entries(redisKey);
	            return !all.isEmpty() && !all.containsKey(requestHash);
	        } catch (Exception e) {
	            log.error("⚠️ Redis HASH check failed for key: {}", redisKey, e);
	            return false; // assume no conflict
	        }
	    }

}

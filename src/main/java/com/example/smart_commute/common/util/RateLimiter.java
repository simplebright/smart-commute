package com.example.smart_commute.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class RateLimiter {

    @Autowired
    private StringRedisTemplate redis;

    private String script;

    @PostConstruct
    public void loadScript() throws IOException {
        script = new String(Files.readAllBytes(
            Paths.get("src/main/resources/rate_limiter.lua")));
    }

    public boolean tryAcquire(String key, int limit, int expireSeconds) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);

        Long result = redis.execute(redisScript,
            Collections.singletonList(key),
            String.valueOf(limit),
            String.valueOf(expireSeconds));

        return result != null && result == 1;
    }
}

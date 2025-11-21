package com.example.smart_commute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import com.example.smart_commute.client.MapClient;

import java.util.concurrent.TimeUnit;
@Service
public class RouteService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MapClient mapClient;

    private static final String CACHE_PREFIX="route";

    public String getRoute(String from,String to){
        String key=CACHE_PREFIX+from+"->"+to;
        String cached=stringRedisTemplate.opsForValue().get(key);
        if(cached!=null)
            return cached;
        String routeJson=mapClient.getRoute(from,to);

        stringRedisTemplate.opsForValue().set(key,routeJson,10,TimeUnit.MINUTES);
        return routeJson;
    }
}

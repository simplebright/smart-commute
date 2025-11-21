package com.example.smart_commute.controller;

import com.example.smart_commute.common.util.RateLimiter;
import com.example.smart_commute.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @Autowired
    private RateLimiter rateLimiter;

    @PostMapping("/payment")
    public String payment(@RequestParam String orderId,@RequestParam double amount){
        //todo
        return "{\"status\":\"ok\",\"orderId\":\"" + orderId + "\"}";
    }
    @GetMapping("/route")
    public Object getRoute(@RequestHeader(value = "X-Real-IP", required = false, defaultValue = "127.0.0.1") String ip,@RequestParam String from,@RequestParam String to) {

        if (!rateLimiter.tryAcquire("route:" + ip, 2, 60)) {
            throw new RuntimeException("访问过于频繁，请稍后再试");
        }

        return routeService.getRoute(from,to);
    }
}

package com.example.smart_commute.service;

import java.math.BigDecimal;
import java.util.Date;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.smart_commute.entity.OrderInfo;
import com.example.smart_commute.entity.PaymentLog;
import com.example.smart_commute.mapper.OrderMapper;
import com.example.smart_commute.mapper.PaymentLogMapper;

@Service
public class PaymentService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PaymentLogMapper PaymentLogMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public String createOrder(Long userId,BigDecimal amount){
        OrderInfo order=new OrderInfo();
        order.setUserId(userId);
        order.setAmount(amount);
        order.setOrderNo(UUID.randomUUID().toString());
        order.setStatus(0);
        orderMapper.insert(order);
        return order.getOrderNo();
    }

    @Transactional
    public String pay(String orderNo){
        OrderInfo order=orderMapper.findByOrderNo(orderNo);

        if(order.getStatus()!=0){
            throw new RuntimeException("订单状态异常");
        }

        orderMapper.updateStatus(orderNo, 1);
        return UUID.randomUUID().toString();
    }
    @Transactional
    public void paymentCallback(String orderNo,String transactionId){
        String lockKey="payment:lock:"+transactionId;
        try{
            Boolean locked=redisTemplate.opsForValue().setIfAbsent(lockKey,"1",Duration.ofSeconds(30));
            if(!locked)
                return;

            if(PaymentLogMapper.existsTransaction(transactionId)){
                return;
            }
            PaymentLog log=new PaymentLog();
            log.setOrderNo(orderNo);
            log.setTransactionId(transactionId);
            log.setPayStatus(1);
            log.setCreateTime(new Date());
            PaymentLogMapper.insert(log);

            orderMapper.updateStatus((orderNo), 2);
        }finally{
            redisTemplate.delete(lockKey);
        }

    }
}

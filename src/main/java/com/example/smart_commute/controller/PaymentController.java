package com.example.smart_commute.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_commute.service.PaymentService;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/order")
    public String createOrder(@RequestParam Long userId,@RequestParam BigDecimal amount){
        return paymentService.createOrder(userId, amount);
    }
    @PostMapping("/doPay")
    public String doPay(@RequestParam String orderNo){
        return paymentService.pay(orderNo);
    }

    @PostMapping("/callback")
    public String callback(@RequestParam String orderNo,@RequestParam String transactionId){
        paymentService.paymentCallback(orderNo, transactionId);
        return "success";
    }
}

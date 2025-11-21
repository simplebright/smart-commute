package com.example.smart_commute.entity;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentLog {
    private Long id;
    private String orderNo;
    private String transactionId;
    private Integer payStatus;
    private String rawData;
    private Date createTime;
}
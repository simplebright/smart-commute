package com.example.smart_commute.entity;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class OrderInfo {
    private Long id;
    private Long userId;
    private String orderNo;
    private BigDecimal amount;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}

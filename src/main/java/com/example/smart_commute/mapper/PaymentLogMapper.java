package com.example.smart_commute.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.smart_commute.entity.PaymentLog;

@Mapper
public interface PaymentLogMapper {
    @Insert("INSERT INTO payment_log (id,order_no,transaction_id,pay_status,raw_data,create_time)"
            +"VALUES(#{id},#{orderNo},#{transactionId},#{payStatus},#{rawData},#{createTime})")
    void insert(PaymentLog log);
    @Select("SELECT COUNT(*)>0 FROM payment_log WHERE id=#{TransactionId}")
    boolean existsTransaction(String TransactionId);
}

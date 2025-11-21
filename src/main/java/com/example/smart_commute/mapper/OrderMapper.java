package com.example.smart_commute.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.smart_commute.entity.OrderInfo;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO order_info (user_id,order_no,amount,status,create_time,update_time)"
            +"VALUES (#{userId},#{orderNo},#{amount},#{status},#{createTime},#{updateTime})")
    int insert(OrderInfo order);
    @Select("SELECT * FROM order_info WHERE order_no=#{orderNo}")
    OrderInfo findByOrderNo(String orderNo);
    @Update("UPDATE order_info SET status=#{status} WHERE order_no =#{orderNo}")
    int updateStatus(@Param("orderNo") String orderNo,
                    @Param("status")Integer status);
}
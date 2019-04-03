package com.ysj.spike.dao;

import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.domain.SpikeOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderDao {

    @Select("select * from busi_spike_order where user_id = #{userId} and goods_id = #{goodsId}")
    SpikeOrder getSpikeOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into busi_order_info(user_id,goods_id,goods_name,goods_count,goods_price,order_channel,status,create_date) " +
            "values(#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert into busi_spike_order(user_id,goods_id,order_id) values(#{userId},#{goodsId},#{orderId})")
    void insertSpikeOrder(SpikeOrder spikeOrder);

    @Select("select * from busi_order_info where id = #{orderId}")
    OrderInfo getOrderById(@Param("orderId") long orderId);
}

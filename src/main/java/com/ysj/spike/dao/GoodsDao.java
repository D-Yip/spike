package com.ysj.spike.dao;

import com.ysj.spike.domain.Goods;
import com.ysj.spike.domain.SpikeGoods;
import com.ysj.spike.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodsDao {

    @Select("select g.*,sg.spike_price,sg.stock_count,sg.start_date,sg.end_date from busi_spike_goods sg left join busi_goods g on sg.goods_id = g.id")
    List<GoodsVO> listGoodsVO();

    @Select("select g.*,sg.spike_price,sg.stock_count,sg.start_date,sg.end_date from busi_spike_goods sg left join busi_goods g on sg.goods_id = g.id where sg.goods_id = #{goodsId}")
    GoodsVO getGoodsVOByGoodsId(@Param("goodsId") long goodsId);

    @Update("update busi_spike_goods set stock_count = stock_count-1 where goods_id = #{goodsId}")
    int reduceStock(SpikeGoods goods);
}

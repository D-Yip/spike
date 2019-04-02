package com.ysj.spike.dao;

import com.ysj.spike.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodsDao {

    @Select("select g.*,sg.spike_price,sg.stock_count,sg.start_date,sg.end_date from spike_goods sg left join goods g on sg.goods_id = g.id")
    List<GoodsVO> listGoodsVO();
}

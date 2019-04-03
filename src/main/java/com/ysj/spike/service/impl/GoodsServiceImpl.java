package com.ysj.spike.service.impl;

import com.ysj.spike.dao.GoodsDao;
import com.ysj.spike.domain.SpikeGoods;
import com.ysj.spike.service.GoodsService;
import com.ysj.spike.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<GoodsVO> listGoodsVO() {
        return goodsDao.listGoodsVO();
    }

    @Override
    public GoodsVO getGoodsVOByGoodsId(long goodsId) {
        return goodsDao.getGoodsVOByGoodsId(goodsId);
    }

    @Override
    public boolean reduceStock(GoodsVO goodsVO) {
        SpikeGoods goods = new SpikeGoods();
        goods.setGoodsId(goodsVO.getId());
        int ret = goodsDao.reduceStock(goods);
        return ret > 0;
    }
}

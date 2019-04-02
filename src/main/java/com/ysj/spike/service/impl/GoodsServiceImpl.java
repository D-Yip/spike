package com.ysj.spike.service.impl;

import com.ysj.spike.dao.GoodsDao;
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
}

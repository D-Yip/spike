package com.ysj.spike.controller;

import com.ysj.spike.base.Result;
import com.ysj.spike.service.GoodsService;
import com.ysj.spike.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/list")
    public Result list() {
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        return Result.success(goodsVOList);
    }

    @GetMapping(value = "/detail/{goodsId}")
    public Result detail (@PathVariable("goodsId")long goodsId) {
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        return Result.success(goodsVO);
    }
}

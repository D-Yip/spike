package com.ysj.spike.controller;

import com.ysj.spike.base.Result;
import com.ysj.spike.domain.User;
import com.ysj.spike.service.GoodsService;
import com.ysj.spike.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/list")
    public Result list(Model model, User user) {
        model.addAttribute("user",user);
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        return Result.success(goodsVOList);
    }
}

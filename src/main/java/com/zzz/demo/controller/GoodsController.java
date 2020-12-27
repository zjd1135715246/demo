package com.zzz.demo.controller;

import com.zzz.demo.entity.Goods;
import com.zzz.demo.entity.MyCart;
import com.zzz.demo.back.GoodsReBack;
import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.request.OrderGoodsBean;
import com.zzz.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController()
@RequestMapping("/goods")
@Api(value = "商品")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/dataWrite")
    public ReBackMessage dataWrite(String data){
        goodsService.saveData(data);
        return new ReBackMessage(200);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "商品ID",dataType = "Integer",required = true),
            @ApiImplicitParam(paramType = "query",name = "name",value = "商品名称",dataType = "String",required = true),
            @ApiImplicitParam(paramType = "query",name = "price",value = "商品价格",dataType = "BigDecimal",required = true),
            @ApiImplicitParam(paramType = "query",name = "goodsTypeId",value = "商品类型ID",dataType = "Integer",required = true),
    })
    @GetMapping("/goodsDetailed")
    public ReBackMessage goodsDetailed(Goods goods){
        ReBackMessage message = goodsService.goodsDetailed(goods);
        return message;
    }


    @GetMapping("/goodsList")
    public ReBackMessage goodsList(HttpServletRequest request){
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        Integer typeId = Integer.parseInt(request.getParameter("typeId"));
        String searchName = request.getParameter("searchName");
        ReBackMessage message = goodsService.goodsList(typeId,pageSize,currentPage,searchName);
        return message;
    }

    @GetMapping("/myCart")
    public ReBackMessage myCart(OrderGoodsBean goodsBean){
        ReBackMessage message = goodsService.myCart(goodsBean);
        return message;
    }

    @GetMapping("/delCartOneGoods")
    public ReBackMessage delCartOneGoods(Integer id){
        ReBackMessage message = goodsService.delCartOneGoods(id);
        return message;
    }

    @GetMapping("/addMyCart")
    public ReBackMessage addMyCart(MyCart cart){
        ReBackMessage message = goodsService.addMyCart(cart);
        return message;
    }

    @PostMapping("/addGoods")
    public ReBackMessage addGoods(GoodsReBack goods){
        ReBackMessage message = goodsService.addGoods(goods);
        return message;
    }
}

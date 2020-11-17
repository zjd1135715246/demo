package com.zzz.demo.controller;

import com.zzz.demo.entity.Goods;
import com.zzz.demo.entity.MyCart;
import com.zzz.demo.back.GoodsReback;
import com.zzz.demo.back.RebackMessage;
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

@Api(value = "商品表操作控制器")
@RestController()
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/dataWrite")
    public RebackMessage dataWrite(String data){
        goodsService.saveData(data);
        return new RebackMessage(200);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "商品ID",dataType = "Integer",required = true),
            @ApiImplicitParam(paramType = "query",name = "name",value = "商品名称",dataType = "String",required = true),
            @ApiImplicitParam(paramType = "query",name = "price",value = "商品价格",dataType = "BigDecimal",required = true),
            @ApiImplicitParam(paramType = "query",name = "goodsTypeId",value = "商品类型ID",dataType = "Integer",required = true),
    })
    @GetMapping("/goodsDetailed")
    public RebackMessage goodsDetailed(Goods goods){
        RebackMessage message = goodsService.goodsDetailed(goods);
        return message;
    }


    @GetMapping("/goodsList")
    public RebackMessage goodsList(HttpServletRequest request){
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        Integer typeId = Integer.parseInt(request.getParameter("typeId"));
        String searchName = request.getParameter("searchName");
        RebackMessage message = goodsService.goodsList(typeId,pageSize,currentPage,searchName);
        return message;
    }

    @GetMapping("/myCart")
    public RebackMessage myCart(OrderGoodsBean goodsBean){
        RebackMessage message = goodsService.myCart(goodsBean);
        return message;
    }

    @GetMapping("/delCartOneGoods")
    public RebackMessage delCartOneGoods(Integer id){
        RebackMessage message = goodsService.delCartOneGoods(id);
        return message;
    }

    @GetMapping("/addMyCart")
    public RebackMessage addMyCart(MyCart cart){
        RebackMessage message = goodsService.addMyCart(cart);
        return message;
    }

    @PostMapping("/addGoods")
    public RebackMessage addGoods(GoodsReback goods){
        RebackMessage message = goodsService.addGoods(goods);
        return message;
    }
}

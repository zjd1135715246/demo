package com.zzz.demo.controller;

import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.request.OrderGoodsBean;
import com.zzz.demo.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Api(value = "订单测试")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/payOrder")
    public ReBackMessage payOrder(String data){
        ReBackMessage message = orderService.createOrder(data);
        return null;
    }

    @GetMapping("/getOrders")
    public ReBackMessage getOrders(OrderGoodsBean order){
        ReBackMessage message = orderService.getOrders(order);
        return message;
    }
}

package com.zzz.demo.controller;

import com.zzz.demo.reBack.RebackMessage;
import com.zzz.demo.requestBean.OrderGoodsBean;
import com.zzz.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/payOrder")
    public RebackMessage payOrder(String data){
        RebackMessage message = orderService.createOrder(data);
        return null;
    }

    @GetMapping("/getOrders")
    public RebackMessage getOrders(OrderGoodsBean order){
        RebackMessage message = orderService.getOrders(order);
        return message;
    }
}

package com.zzz.demo.service;

import com.zzz.demo.reBack.RebackMessage;
import com.zzz.demo.requestBean.OrderGoodsBean;

public interface OrderService {
    RebackMessage createOrder(String data);

    RebackMessage getOrders(OrderGoodsBean order);
}

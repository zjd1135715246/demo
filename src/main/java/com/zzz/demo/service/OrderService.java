package com.zzz.demo.service;

import com.zzz.demo.back.RebackMessage;
import com.zzz.demo.request.OrderGoodsBean;

public interface OrderService {
    RebackMessage createOrder(String data);

    RebackMessage getOrders(OrderGoodsBean order);
}

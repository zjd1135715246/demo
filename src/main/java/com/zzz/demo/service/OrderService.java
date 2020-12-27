package com.zzz.demo.service;

import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.request.OrderGoodsBean;

public interface OrderService {
    ReBackMessage createOrder(String data);

    ReBackMessage getOrders(OrderGoodsBean order);
}

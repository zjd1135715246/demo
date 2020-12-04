package com.zzz.demo.service;

import com.zzz.demo.entity.Goods;
import com.zzz.demo.entity.MyCart;
import com.zzz.demo.back.GoodsReBack;
import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.request.OrderGoodsBean;

public interface GoodsService {
    void saveData(String data);

    ReBackMessage goodsDetailed(Goods goods);

    ReBackMessage goodsList(Integer typeId, Integer pageSize, Integer currentPage, String searchName);

    ReBackMessage myCart(OrderGoodsBean goodsBean);

    ReBackMessage delCartOneGoods(Integer id);

    ReBackMessage addMyCart(MyCart cart);

    ReBackMessage addGoods(GoodsReBack goods);
}

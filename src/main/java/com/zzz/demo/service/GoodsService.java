package com.zzz.demo.service;

import com.zzz.demo.entity.Goods;
import com.zzz.demo.entity.MyCart;
import com.zzz.demo.back.GoodsReback;
import com.zzz.demo.back.RebackMessage;
import com.zzz.demo.request.OrderGoodsBean;

public interface GoodsService {
    void saveData(String data);

    RebackMessage goodsDetailed(Goods goods);

    RebackMessage goodsList(Integer typeId, Integer pageSize, Integer currentPage, String searchName);

    RebackMessage myCart(OrderGoodsBean goodsBean);

    RebackMessage delCartOneGoods(Integer id);

    RebackMessage addMyCart(MyCart cart);

    RebackMessage addGoods(GoodsReback goods);
}

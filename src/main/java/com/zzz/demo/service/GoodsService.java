package com.zzz.demo.service;

import com.zzz.demo.entity.Goods;
import com.zzz.demo.entity.MyCart;
import com.zzz.demo.reBack.GoodsReback;
import com.zzz.demo.reBack.RebackMessage;
import com.zzz.demo.requestBean.OrderGoodsBean;

public interface GoodsService {
    void saveData(String data);

    RebackMessage goodsDetailed(Goods goods);

    RebackMessage goodsList(Integer typeId, Integer pageSize, Integer currentPage, String searchName);

    RebackMessage myCart(OrderGoodsBean goodsBean);

    RebackMessage delCartOneGoods(Integer id);

    RebackMessage addMyCart(MyCart cart);

    RebackMessage addGoods(GoodsReback goods);
}

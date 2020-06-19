package com.zzz.demo.util;

import com.zzz.demo.entity.Coupon;
import com.zzz.demo.entity.OrderGoods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OtherUtil {

    public static List<Integer>  pageManager(Integer currentPage,Integer pageSize){
        List<Integer> list = new LinkedList();
        list.add((currentPage-1)*pageSize);
        list.add(pageSize);
        return list;
    }

    public static List<Coupon> getNewUserCoupon(Integer userId){
        List<Coupon> list = new ArrayList<>();
        Coupon coupon1 = new Coupon("新用户专享优惠券",new BigDecimal(5),1,userId);
        Coupon coupon2 = new Coupon("明日之星优惠券",new BigDecimal(15),1,userId);
        Coupon coupon3 = new Coupon("极品优惠券",new BigDecimal(35),1,userId);
        list.add(coupon1);list.add(coupon2);list.add(coupon3);
        return list;
    }

    public static String saveOrderGoodsList(List<OrderGoods> list) {
        StringBuffer sb = new StringBuffer("insert into OrderGoods(orderId,popOne,popTwo,price,quantity,goodsId) values");
        list.stream().forEach(p->{
            sb.append("('"+p.getOrderId()+"','"+p.getPopOne()+"','"+p.getPopTwo()+"','"+p.getPrice()+"','"+p.getQuantity()+"','"+p.getGoodsId()+"'),");
        });
        sb.setLength(sb.length()-1);
        return sb.toString();
    }
}

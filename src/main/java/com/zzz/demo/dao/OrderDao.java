package com.zzz.demo.dao;

import com.zzz.demo.entity.MyCart;
import com.zzz.demo.entity.Orders;
import com.zzz.demo.entity.User;
import com.zzz.demo.request.OrderQueryBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderDao {

    void saveOrder(Orders orders);

    void executeSql(String sql);

    @Update("update coupon set status=0 where id=#{couponId}")
    void updateCoupon(Integer couponId);

    void delMyCart(List<MyCart> carts);

    @Update("update usermallinfo set money=#{money} where userId=#{id}  ")
    void updateMail(User u);

    List getOrders(OrderQueryBean query);
}

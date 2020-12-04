package com.zzz.demo.service.serviceimpl;

import com.zzz.demo.Enum.OrderEnum;
import com.zzz.demo.dao.OrderDao;
import com.zzz.demo.entity.MyCart;
import com.zzz.demo.entity.OrderGoods;
import com.zzz.demo.entity.Orders;
import com.zzz.demo.entity.User;
import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.request.OrderGoodsBean;
import com.zzz.demo.request.OrderQueryBean;
import com.zzz.demo.service.OrderService;
import com.zzz.demo.util.OtherUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public ReBackMessage createOrder(String data) {
        JSONArray array =JSONArray.fromObject(data);
        List<OrderGoods> list = new ArrayList<>();
        List<MyCart> carts = new ArrayList<>();
                Orders orders = new Orders();
        for (int i = 0; i <array.size() ; i++) {
            Map map = (Map) array.get(i);
            if(i==0){
                orders.setCouponId((Integer) map.get("couponId"));
                orders.setUserId((Integer) map.get("userId"));
                orders.setPrice(BigDecimal.valueOf(Double.parseDouble((String) map.get("amount"))));
                orders.setStatus(OrderEnum.ORDERSTATUS_SUCCESS.getTypeCode());
                User u = new User();
                u.setId(orders.getUserId());
                u.setMoney(BigDecimal.valueOf(Double.parseDouble((String) map.get("userReMoney"))));
                orderDao.saveOrder(orders);
                orderDao.updateMail(u);
            }
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoodsId((Integer) map.get("goodsId"));
            orderGoods.setOrderId(orders.getId());
            orderGoods.setPopOne((String) map.get("popOne"));
            orderGoods.setPopTwo((String) map.get("popTwo"));
            orderGoods.setPrice(BigDecimal.valueOf(Double.parseDouble((String) map.get("price"))));
            orderGoods.setQuantity((Integer) map.get("quantity"));
            list.add(orderGoods);
            MyCart cart = new MyCart();
            cart.setId((Integer) map.get("id"));
            carts.add(cart);
        }
        String sql =OtherUtil.saveOrderGoodsList(list);
        orderDao.executeSql(sql);
        orderDao.delMyCart(carts);
        if(orders.getCouponId()>0){
            orderDao.updateCoupon(orders.getCouponId());
        }

        return new ReBackMessage(200,"成功");
    }

    @Override
    public ReBackMessage getOrders(OrderGoodsBean order) {
        List<Integer> pageList = OtherUtil.pageManager(order.getCurrentPage(),order.getPageSize());
        OrderQueryBean query = new OrderQueryBean(order.getUserId(),order.getStatus(),pageList.get(0),pageList.get(1));
        List result = orderDao.getOrders(query);
        return new ReBackMessage(200,"成功",result);
    }
}

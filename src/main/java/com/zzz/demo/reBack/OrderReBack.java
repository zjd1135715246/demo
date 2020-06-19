package com.zzz.demo.reBack;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderReBack {

    private Integer id;
    private Integer userId;
    private Integer status;
    private BigDecimal price;
    private String memo;
    private Timestamp addTime;
    private List<OrderGoodsReback> orderGoods;

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<OrderGoodsReback> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoodsReback> orderGoods) {
        this.orderGoods = orderGoods;
    }
}

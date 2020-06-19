package com.zzz.demo.entity;


import java.math.BigDecimal;
import java.util.List;

public class Orders {

  private Integer id;
  private Integer userId;
  private Integer status;
  private BigDecimal price;
  private String memo;
  private Integer couponId;
  private List<OrderGoods> orderGoods;

  public List<OrderGoods> getOrderGoods() {
    return orderGoods;
  }

  public void setOrderGoods(List<OrderGoods> orderGoods) {
    this.orderGoods = orderGoods;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Integer getCouponId() {
    return couponId;
  }

  public void setCouponId(Integer couponId) {
    this.couponId = couponId;
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

}

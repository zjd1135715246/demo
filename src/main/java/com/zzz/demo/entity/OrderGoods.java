package com.zzz.demo.entity;


import java.math.BigDecimal;

public class OrderGoods {

  private Integer id;
  private Integer orderId;
  private Integer goodsId;
  private Integer quantity;
  private BigDecimal price;
  private String popOne;
  private String popTwo;

  public String getPopTwo() {
    return popTwo;
  }

  public void setPopTwo(String popTwo) {
    this.popTwo = popTwo;
  }

  public String getPopOne() {
    return popOne;
  }

  public void setPopOne(String popOne) {
    this.popOne = popOne;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }


  public Integer getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Integer goodsId) {
    this.goodsId = goodsId;
  }


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}

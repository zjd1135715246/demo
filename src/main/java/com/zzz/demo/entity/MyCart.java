package com.zzz.demo.entity;


public class MyCart {

  private Integer id;
  private Integer userId;
  private Integer goodsId;
  private Integer quantity;
  private String popOne;
  private String popTwo;

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


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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

  public String getPopTwo() {
    return popTwo;
  }

  public void setPopTwo(String popTwo) {
    this.popTwo = popTwo;
  }
}

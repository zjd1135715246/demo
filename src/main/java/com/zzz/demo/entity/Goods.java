package com.zzz.demo.entity;


import java.math.BigDecimal;

public class Goods {

  private Integer id;
  private String name;
  private BigDecimal price;
  private Integer goodsTypeId;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getGoodsTypeId() {
    return goodsTypeId;
  }

  public void setGoodsTypeId(Integer goodsTypeId) {
    this.goodsTypeId = goodsTypeId;
  }

  public Goods() {
  }

  public Goods(Integer id, String name, BigDecimal price, Integer goodsTypeId) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.goodsTypeId = goodsTypeId;
  }

}

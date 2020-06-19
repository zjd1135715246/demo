package com.zzz.demo.entity;


import java.math.BigDecimal;

public class Coupon {

  private Integer id;
  private String title;
  private BigDecimal price;
  private Integer status;  //1:未使用
  private Integer userId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Coupon(Integer id, String title, BigDecimal price, Integer status, Integer userId) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.status = status;
    this.userId = userId;
  }

  public Coupon() {
  }

  public Coupon(String title, BigDecimal price, Integer status, Integer userId) {
    this.title = title;
    this.price = price;
    this.status = status;
    this.userId = userId;
  }
}

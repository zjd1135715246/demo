package com.zzz.demo.entity;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class User {

  private Integer id;
  private String account;
  private String nickname;
  private String password;
  private String headImg;
  private Timestamp addtime;
  private BigDecimal money;
  private Integer couponTotal;
  private List<com.zzz.demo.entity.Discuss> discusses;

  public Integer getCouponTotal() {
    return couponTotal;
  }

  public void setCouponTotal(Integer couponTotal) {
    this.couponTotal = couponTotal;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public java.sql.Timestamp getAddtime() {
    return addtime;
  }

  public void setAddtime(java.sql.Timestamp addtime) {
    this.addtime = addtime;
  }

  public List<Discuss> getDiscusses() {
    return discusses;
  }

  public void setDiscusses(List<Discuss> discusses) {
    this.discusses = discusses;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }

  public User(Integer id, String account, String nickname, String password, Timestamp addtime, List<Discuss> discusses, BigDecimal money,String headImg) {
    this.id = id;
    this.account = account;
    this.nickname = nickname;
    this.password = password;
    this.addtime = addtime;
    this.discusses = discusses;
    this.money=money;
    this.headImg=headImg;
  }

  public User() {
  }
}

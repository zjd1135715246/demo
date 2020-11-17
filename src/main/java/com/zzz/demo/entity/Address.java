package com.zzz.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data

public class Address {

  private Integer id;
  private Integer userId;
  private String name;
  private String mobile;
  private String addressName;
  private String address;
  private String area;
  private Integer defaultAddress;
  public static AtomicInteger atomicInteger = new AtomicInteger();

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


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }


  public String getAddressName() {
    return addressName;
  }

  public void setAddressName(String addressName) {
    this.addressName = addressName;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }


  public Integer getDefault() {
    return defaultAddress;
  }

  public void setDefault(Integer defaultAddress) {
    this.defaultAddress = defaultAddress;
  }

  public Address(Integer id, Integer userId, String name, String mobile, String addressName, String address, String area, Integer defaultAddress) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.mobile = mobile;
    this.addressName = addressName;
    this.address = address;
    this.area = area;
    this.defaultAddress = defaultAddress;
  }

  public Address(){}
}

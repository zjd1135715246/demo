package com.zzz.demo.entity;


import java.sql.Timestamp;

public class Discuss {

  private Integer id;
  private Integer uid;
  private String disinfo;
  private java.sql.Timestamp disdate;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }


  public String getDisinfo() {
    return disinfo;
  }

  public void setDisinfo(String disinfo) {
    this.disinfo = disinfo;
  }


  public java.sql.Timestamp getDisdate() {
    return disdate;
  }

  public void setDisdate(java.sql.Timestamp disdate) {
    this.disdate = disdate;
  }

  public Discuss(Integer id, Integer uid, String disinfo, Timestamp disdate) {
    this.id = id;
    this.uid = uid;
    this.disinfo = disinfo;
    this.disdate = disdate;
  }

  public Discuss() {
  }
}

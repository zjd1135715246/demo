package com.zzz.demo.entity;


import java.sql.Timestamp;

public class Chat {

  private Integer id;
  private Integer fromId;
  private Integer toId;
  private String message;
  private Integer messageType;
  private Integer isRead;
  private Timestamp addTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getFromId() {
    return fromId;
  }

  public void setFromId(Integer fromId) {
    this.fromId = fromId;
  }


  public Integer getToId() {
    return toId;
  }

  public void setToId(Integer toId) {
    this.toId = toId;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public Integer getMessageType() {
    return messageType;
  }

  public void setMessageType(Integer messageType) {
    this.messageType = messageType;
  }


  public Integer getIsRead() {
    return isRead;
  }

  public void setIsRead(Integer isRead) {
    this.isRead = isRead;
  }


  public Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(Timestamp addTime) {
    this.addTime = addTime;
  }

}

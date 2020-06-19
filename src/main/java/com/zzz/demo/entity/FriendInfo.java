package com.zzz.demo.entity;


import java.sql.Timestamp;

public class FriendInfo {

  private Integer id;
  private Integer ownId;
  private Integer friendId;
  private Timestamp addTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getOwnId() {
    return ownId;
  }

  public void setOwnId(Integer ownId) {
    this.ownId = ownId;
  }


  public Integer getFriendId() {
    return friendId;
  }

  public void setFriendId(Integer friendId) {
    this.friendId = friendId;
  }


  public Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(Timestamp addTime) {
    this.addTime = addTime;
  }

}

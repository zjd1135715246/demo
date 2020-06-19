package com.zzz.demo.reBack;

import com.zzz.demo.entity.Chat;

import java.util.List;

public class FriendMessage {

    private Integer FriendId;
    private String headImg;
    private String nickName;
    private List<Chat> chats;

    public Integer getFriendId() {
        return FriendId;
    }

    public void setFriendId(Integer friendId) {
        FriendId = friendId;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

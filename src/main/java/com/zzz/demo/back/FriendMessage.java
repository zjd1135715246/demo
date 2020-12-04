package com.zzz.demo.back;

import com.zzz.demo.entity.Chat;
import lombok.Data;

import java.util.List;

@Data
public class FriendMessage {

    private Integer FriendId;
    private String headImg;
    private String nickName;
    private List<Chat> chats;


}

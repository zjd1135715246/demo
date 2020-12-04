package com.zzz.demo.service;

import com.zzz.demo.entity.Address;
import com.zzz.demo.entity.FriendInfo;
import com.zzz.demo.entity.User;
import com.zzz.demo.back.ReBackMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List getUserDiscuss(Integer id);

    int registerUser(User user);


    List login(User user);

    ReBackMessage recharge(User user);

    ReBackMessage getAddress(User user);

    ReBackMessage addAddress(Address address, Integer type);

    ReBackMessage getNowUserCoupon(User user);

    void updateHeadImg(MultipartFile file, User user);

    ReBackMessage findFriend(Integer id, Integer type);

    ReBackMessage addFriend(FriendInfo friendInfo);

    ReBackMessage talkImg(String toUserId, String formUserId, MultipartFile file);

    void test();
}

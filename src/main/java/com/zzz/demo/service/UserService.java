package com.zzz.demo.service;

import com.zzz.demo.entity.Address;
import com.zzz.demo.entity.FriendInfo;
import com.zzz.demo.entity.User;
import com.zzz.demo.back.RebackMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List getUserDiscuss(Integer id);

    int registerUser(User user);


    List login(User user);

    RebackMessage recharge(User user);

    RebackMessage getAddress(User user);

    RebackMessage addAddress(Address address, Integer type);

    RebackMessage getNowUserCoupon(User user);

    void updateHeadImg(MultipartFile file, User user);

    RebackMessage findFriend(Integer id, Integer type);

    RebackMessage addFriend(FriendInfo friendInfo);

    RebackMessage talkImg(String toUserId,String formUserId, MultipartFile file);

    void test();
}

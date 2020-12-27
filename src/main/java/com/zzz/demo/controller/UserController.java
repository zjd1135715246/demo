package com.zzz.demo.controller;

import com.zzz.demo.entity.Address;
import com.zzz.demo.entity.FriendInfo;
import com.zzz.demo.entity.User;
import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
@Api(tags= "用户API")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("getUserDiscuss")
    public List<User> getUserDiscuss(Integer id){
        List list = new ArrayList();
        list = userService.getUserDiscuss(id);
        return list;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public ReBackMessage register(@ModelAttribute User user){
        int  code = userService.registerUser(user);
        if(code==0){
            return new ReBackMessage(0,"注册成功!",null,new Timestamp(System.currentTimeMillis()));
        }else if(code==-1){
            return new ReBackMessage(-1,"账户已存在",null,new Timestamp(System.currentTimeMillis()));
        }
        return new ReBackMessage(500,"网络异常",null,new Timestamp(System.currentTimeMillis()));
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public ReBackMessage login(User user){
        List list = userService.login(user);
        if(list==null || list.size()==0){
            return new ReBackMessage(-1,"账号密码错误!",null,new Timestamp(System.currentTimeMillis()));
        }
        if(list.size()>0){
            return new ReBackMessage(0,"登录成功!",list,new Timestamp(System.currentTimeMillis()));
        }
        return new ReBackMessage(500,"网络异常!",null,new Timestamp(System.currentTimeMillis()));
    }

    @GetMapping("getAddress")
    public ReBackMessage getAddress(User user){
        return userService.getAddress(user);
    }

    @GetMapping("recharge")
    public ReBackMessage recharge(User user){
        return userService.recharge(user);
    }

    @GetMapping("addAddress")
    public ReBackMessage addAddress(Address address, Integer type){
        return userService.addAddress(address,type);
    }

    @GetMapping("getNowUserCoupon")
    public ReBackMessage getNowUserCoupon(User user){
        return userService.getNowUserCoupon(user);
    }


    @PostMapping("testUser")
    public void testUser(List<User> users){
        System.out.println(users);
    }

    @GetMapping("findFriend")
    public ReBackMessage findFriend(Integer id, Integer type){
        return userService.findFriend(id,type);
    }

    @GetMapping("addFriend")
    public ReBackMessage addFriend(FriendInfo friendInfo){
        return userService.addFriend(friendInfo);
    }

    @GetMapping("/test")
    public String test(){
        userService.test();
        return "ok";
    }

}

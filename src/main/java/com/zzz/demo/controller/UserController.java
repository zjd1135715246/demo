package com.zzz.demo.controller;

import com.zzz.demo.entity.Address;
import com.zzz.demo.entity.FriendInfo;
import com.zzz.demo.entity.User;
import com.zzz.demo.back.RebackMessage;
import com.zzz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/getUserDiscuss")
    @ResponseBody()
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
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RebackMessage register(User user){
        int  code = userService.registerUser(user);
        if(code==0){
            return new RebackMessage(0,"注册成功!",null,new Timestamp(System.currentTimeMillis()));
        }else if(code==-1){
            return new RebackMessage(-1,"账户已存在",null,new Timestamp(System.currentTimeMillis()));
        }
        return new RebackMessage(500,"网络异常",null,new Timestamp(System.currentTimeMillis()));
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RebackMessage login(User user){
        List list = userService.login(user);
        if(list==null || list.size()==0){
            return new RebackMessage(-1,"账号密码错误!",null,new Timestamp(System.currentTimeMillis()));
        }
        if(list.size()>0){
            return new RebackMessage(0,"登录成功!",list,new Timestamp(System.currentTimeMillis()));
        }
        return new RebackMessage(500,"网络异常!",null,new Timestamp(System.currentTimeMillis()));
    }

    @RequestMapping(value = "/getAddress",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RebackMessage getAddress(User user){
        return userService.getAddress(user);
    }

    @RequestMapping(value = "/recharge",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RebackMessage recharge(User user){
        return userService.recharge(user);
    }

    @RequestMapping(value = "/addAddress",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RebackMessage addAddress(Address address,Integer type){
        return userService.addAddress(address,type);
    }

    @RequestMapping(value = "/getNowUserCoupon",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RebackMessage getNowUserCoupon(User user){
        return userService.getNowUserCoupon(user);
    }


    @RequestMapping(value = "/testUser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void testUser(List<User> users){
        System.out.println(users);
    }

    @RequestMapping(value = "/findFriend",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RebackMessage findFriend(Integer id,Integer type){
        return userService.findFriend(id,type);
    }

    @RequestMapping(value = "/addFriend",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public RebackMessage addFriend(FriendInfo friendInfo){
        return userService.addFriend(friendInfo);
    }

    @GetMapping("/test")
    public String test(){
        //userService.test();
        String x = "我不用";
        return x;
    }

}

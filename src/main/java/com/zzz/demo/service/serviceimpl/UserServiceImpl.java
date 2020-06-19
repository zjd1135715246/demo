package com.zzz.demo.service.serviceimpl;

import com.zzz.demo.config.WebSocketServer;
import com.zzz.demo.dao.UserDao;
import com.zzz.demo.entity.*;
import com.zzz.demo.reBack.RebackMessage;
import com.zzz.demo.service.UserService;
import com.zzz.demo.util.OtherUtil;
import com.zzz.demo.util.POJOSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List getUserDiscuss(Integer id) {
        return userDao.getUserDiscuss(id);
    }

    @Override
    public int registerUser(User user) {
        int code =0;
        List<User> list = new ArrayList<>();
        try {
            list  = userDao.findUserByAccount(user.getAccount());
        }catch (Exception e){
            return 500;
        }
        if(list.size()>0){
            return -1;
        }else {
            user.setNickname(user.getNickname()==null?"无名":user.getNickname());
            userDao.registerUser(user);
            if(user.getId()>0){
                userDao.saveUserMallInfo(user);
                List<Coupon> coupons = OtherUtil.getNewUserCoupon(user.getId());
                for (Coupon c:coupons
                     ) {
                    userDao.saveCoupon(c);
                }
            }
        }
        return 0;
    }

    @Override
    public List login(User user) {
        List<User> list =userDao.findUserByAccount(user.getAccount());
        if(list.size()>0){
            User daUser = list.get(0);
            if(user.getPassword().equals(daUser.getPassword())){
                return list;
            }
        }
        return null;
    }

    @Override
    public RebackMessage recharge(User user) {
        try {
            userDao.recharge(user);
            return new RebackMessage(0,"操作成功",null);
        }catch (Exception e){
            return new RebackMessage(500,"网络异常",null);
        }
    }

    @Override
    public RebackMessage getAddress(User user) {
        List list = new ArrayList();
        try {
            list = userDao.getAddress(user);
            return new RebackMessage(200,"查询成功",list);
        }catch (Exception e){
            return new RebackMessage(500,"网络异常");
        }
    }

    @Override
    public RebackMessage addAddress(Address address, Integer type) {
        try {
            if(address.getDefault().equals(1)){
                userDao.updateAddressDefault(address);
            }
            if(type==2){
                userDao.addAddress(address);
            }else if(type==1){
                //userDao.updateAddress(address);
                POJOSave.savePOJO(address,type);
            }
            return new RebackMessage(200,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new RebackMessage(500,"网络异常");
        }
    }

    @Override
    public RebackMessage getNowUserCoupon(User user) {
        List<Coupon> list = userDao.getNowUserCoupon(user);
        return new RebackMessage(200,"bingo",list);
    }

    @Override
    public void updateHeadImg(MultipartFile file, User user) {
        String fileName = file.getOriginalFilename();
        fileName = System.currentTimeMillis()+fileName;
        //String filePath = "G:\\test\\xx\\o2\\";
        String filePath = "/usr/dd/";
        File dest = new File(filePath + fileName);
        //String dbFileName = "http://127.0.0.1:2233/demo/upload/"+fileName;
        String dbFileName = "http://47.56.162.197:2233/demo/upload/"+fileName;
        user.setHeadImg(dbFileName);
        try {
            file.transferTo(dest);
            userDao.updateHeadImg(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RebackMessage findFriend(Integer id, Integer type) {
        List list = new ArrayList<>();
        if(type==1){
         list= userDao.findFriend(id);
        }else {
            list= userDao.findStranger(id);
        }
        return new RebackMessage(200,"成功",list);
    }

    @Override
    public RebackMessage addFriend(FriendInfo friendInfo) {
        userDao.addFriend(friendInfo);
        return new RebackMessage(200,"bingo");
    }

    @Override
    public RebackMessage talkImg(String toUserId, String formUserId, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        fileName = System.currentTimeMillis()+fileName;
        //String filePath = "G:\\test\\xx\\o2\\";
        String filePath = "/usr/dd/";
        File dest = new File(filePath + fileName);
        //String dbFileName = "2-"+"http://10.0.2.2:2233/demo/upload/"+fileName;
        String dbFileName = "2-"+"http://47.56.162.197:2233/demo/upload/"+fileName;
        try {
            file.transferTo(dest);
            WebSocketServer.sendInfo("{\"fromUserId\":\""+formUserId+"\",\"contentText\":\"2-http://47.56.162.197:2233/demo/upload/"+fileName+"\",\"toUserId\":\""+toUserId+"\"}",toUserId);
        }catch (Exception e){}
        return new RebackMessage(200,"http://47.56.162.197:2233/demo/upload/"+fileName);
    }

    @Override
    public void test() {
        List<T1> list = new ArrayList<>();
        for (int i = 0; i <100000 ; i++) {
            T1 t1 = new T1();
            t1.setName(String.valueOf(i));
            list.add(t1);
        }
        long start = System.currentTimeMillis();
        userDao.test(list);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }


}

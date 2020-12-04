package com.zzz.demo.service.serviceimpl;

import com.zzz.demo.config.WebSocketServer;
import com.zzz.demo.dao.UserDao;
import com.zzz.demo.entity.*;
import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.service.UserService;
import com.zzz.demo.util.OtherUtil;
import com.zzz.demo.util.POJOSave;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
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

    @Autowired
    private CuratorFramework zkClient;

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
    public ReBackMessage recharge(User user) {
        try {
            userDao.recharge(user);
            return new ReBackMessage(0,"操作成功",null);
        }catch (Exception e){
            return new ReBackMessage(500,"网络异常",null);
        }
    }

    @Override
    public ReBackMessage getAddress(User user) {
        List list = new ArrayList();
        try {
            list = userDao.getAddress(user);
            return new ReBackMessage(200,"查询成功",list);
        }catch (Exception e){
            return new ReBackMessage(500,"网络异常");
        }
    }

    @Override
    public ReBackMessage addAddress(Address address, Integer type) {
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
            return new ReBackMessage(200,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ReBackMessage(500,"网络异常");
        }
    }

    @Override
    public ReBackMessage getNowUserCoupon(User user) {
        List<Coupon> list = userDao.getNowUserCoupon(user);
        return new ReBackMessage(200,"bingo",list);
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
    public ReBackMessage findFriend(Integer id, Integer type) {
        List list = new ArrayList<>();
        if(type==1){
         list= userDao.findFriend(id);
        }else {
            list= userDao.findStranger(id);
        }
        return new ReBackMessage(200,"成功",list);
    }

    @Override
    public ReBackMessage addFriend(FriendInfo friendInfo) {
        userDao.addFriend(friendInfo);
        return new ReBackMessage(200,"bingo");
    }

    @Override
    public ReBackMessage talkImg(String toUserId, String formUserId, MultipartFile file) {
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
        return new ReBackMessage(200,"http://47.56.162.197:2233/demo/upload/"+fileName);
    }

    @Override
    public void test() {
        try {
            //创建节点(默认是永久节点),父节点不存在则报错
            //String s = zkClient.create().forPath("/node1/node3");

            //创建节点，父节点不存在则创建父节点 withMode:指定创建节点的类型
            //zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/node1/node4");

            //创建临时节点
            //zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/node1/epNode1");

            //创建节点并指定内容
            //zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/node1/node5","node5的内容是java".getBytes());
            //获取节点内容
            //byte[] node5 = zkClient.getData().forPath("/node1/node5");

            //检测节点是否创建成功,成功则返回节点的stat，否则为null
            //Stat stat = zkClient.checkExists().forPath("/node1/node5");

            //删除一个子节点
            //zkClient.delete().forPath("/node1/node2");

            //删除一个节点及其下所有子节点
            //zkClient.delete().deletingChildrenIfNeeded().forPath("/node2");

            //更新节点内容
            //zkClient.setData().forPath("/node1/node5","内容改变了".getBytes());

            //获取节点的所有子节点路径
            //List<String> list = zkClient.getChildren().forPath("/node1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.zzz.demo.dao;

import com.zzz.demo.entity.*;
import com.zzz.demo.back.FriendMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "account",property = "account"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "password",property = "password"),
            @Result(column = "addtime",property = "addtime"),
            @Result(column = "id",property = "discusses",
            many = @Many(select = "com.zzz.demo.dao.DiscussDao.getDiscussByUid"))
    })
    List<User> getUserDiscuss(Integer id);

    @Select("select u.*,um.money,c.couponTotal from user u left join userMallInfo  um on u.id=um.userId  left join (select count(userId) couponTotal,userId from coupon where status=1 and userId=(select id from user where account=#{param1}) ) c on u.id=c.userId where u.account=#{param1}")
    List<User> findUserByAccount(String account);

    void registerUser(User user);

    @Insert("insert into userMallInfo(userId,money) values(#{id},0)")
    void saveUserMallInfo(User user);

    @Update("update userMallInfo set money=#{money} where userId=#{id}")
    void recharge(User user);

    @Select("select * from address where userId = #{id}")
    List<Address> getAddress(User user);

    @Update("update address set defaultAddress=0 where userId=#{userId}")
    void updateAddressDefault(Address address);

    @Insert("insert into address(userId,name,mobile,addressName,address,area,defaultAddress) values(#{userId},#{name},#{mobile},#{addressName},#{address},#{area},#{default})")
    void addAddress(Address address);

    @Update("update address set name=#{name},mobile=#{mobile},addressName=#{addressName},address=#{address},area=#{area} where id=#{id}")
    void updateAddress(Address address);

    void savePOJOBySql(String sql);

    @Insert("insert into coupon(title,price,status,userId) values(#{title},#{price},#{status},#{userId})")
    void saveCoupon(Coupon c);

    @Select("select * from coupon where userId=#{id} and status=1 ")
    List<Coupon> getNowUserCoupon(User user);

    @Update("update user set headimg=#{headImg} where id=#{id}")
    void updateHeadImg(User user);

    List<FriendMessage> findFriend(Integer id);

    @Select("select id,headImg,nickName from user a where id not in(select friendId from friendinfo friendId where ownid=#{id}) and a.id<>#{id}")
    List<User> findStranger(Integer id);

    @Insert("insert into friendInfo(ownId,friendId) values(#{ownId},#{friendId}),(#{friendId},#{ownId})")
    void addFriend(FriendInfo friendInfo);

    void test(List<T1> list);
}

package com.zzz.demo.dao;

import com.zzz.demo.entity.Discuss;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface DiscussDao {

    @Select("select * from discuss where uid=#{uid}")
    List<Discuss> getDiscussByUid(Integer uid);
}

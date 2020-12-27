package com.zzz.demo.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zjd
 * @date 2020/12/25
 */
@Component
public class IdSnowflake {

    private static Long workId = 0L;
    private static Long dataCenterId = 1L;
    private static Snowflake snowflake = IdUtil.createSnowflake(workId,dataCenterId);

    @PostConstruct
    public void  init(){
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e){
            System.out.println("获取失败");
        }
    }

    public static synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public static synchronized long snowflakeId(Long workId,Long dataCenterId){
        return IdUtil.createSnowflake(workId,dataCenterId).nextId();
    }

    public static void main(String[] args) {
        System.out.println(IdSnowflake.snowflakeId());
    }
}

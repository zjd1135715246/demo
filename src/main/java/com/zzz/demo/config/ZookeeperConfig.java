package com.zzz.demo.config;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {

    private static final int BASE_SLEEP_TIME = 1000;
    private static final int MAX_RETRIES = 3;

    /**
     * 重试策略
     * BASE_SLEEP_TIME:重试之间等待的初始时间
     * MAX_RETRIES：最大重试次数
     */
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES);

    CuratorFramework zkClient = CuratorFrameworkFactory.builder()
            // the server to connect to (can be a server list)
            .connectString("81.70.199.67:2181")
            .retryPolicy(retryPolicy)
            .build();


    @Bean
    CuratorFramework getZkClient(){
        zkClient.start();
        return zkClient;
    }

}

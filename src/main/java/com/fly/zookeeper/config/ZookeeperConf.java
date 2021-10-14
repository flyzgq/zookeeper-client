package com.fly.zookeeper.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author fly
 * @version 1.0
 * @date 2021/10/14 22:29
 * @description:
 */
@Component
public class ZookeeperConf {

    @Value("${zookeeper.addr}")
    private String zkHost;

    @Value("${zookeeper.sessionTimeout}")
    private int sessionTimeout;
    @Bean
    public ZooKeeper getZk(){
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(zkHost, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zooKeeper;
    }
}

package com.fly.zookeeper.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fly
 * @version 1.0
 * @date 2021/10/14 22:40
 * @description:
 */
@Slf4j
@RestController
@Api(tags = "zookeeper相关接口")
public class zookeeperController {

    @Autowired
    private ZooKeeper zooKeeper;


    @ApiOperation(value = "获取数据")
    @GetMapping("/data")
    public String getData(){
        byte[] data = new byte[0];
        try {
            data = zooKeeper.getData("/tuling", true, null);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new String(data);
    }


    @ApiOperation(value = "zookeeper监听数据")
    @GetMapping("/watch")
    public String getWatchData(){
        byte[] data = null;
        Stat stat = new Stat();
        try {
                 data = zooKeeper.getData("/tuling", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    try {
                        zooKeeper.getData(watchedEvent.getPath(), this, null);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info(watchedEvent.getPath());
                }
            }, stat);
                 //log.info(stat.toString());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new String(data);
    }



}

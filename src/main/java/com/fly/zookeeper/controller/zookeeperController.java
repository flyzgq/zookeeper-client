package com.fly.zookeeper.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public String getData() throws InterruptedException, KeeperException {
        byte[] data = zooKeeper.getData("/tuling", true, null);
        return new String(data);
    }


    @ApiOperation(value = "zookeeper监听数据")
    @GetMapping("/watch")
    public String getWatchData() throws InterruptedException, KeeperException {
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/tuling", new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent watchedEvent) {
                zooKeeper.getData(watchedEvent.getPath(), this, null);
                log.info(watchedEvent.getPath());
            }
        }, stat);
        log.info(stat.toString());
        return new String(data);
    }

    @ApiOperation(value = "获取子节点")
    @GetMapping("/getChildren")
    public String getChildren() throws InterruptedException, KeeperException {
        List<String> children = zooKeeper.getChildren("/tuling", watchedEvent -> log.info(watchedEvent.getPath()));
        children.forEach(log::info);
        return "zookeeper children";
    }

    @ApiOperation("创建节点")
    @GetMapping("/createData")
    public void createData() throws KeeperException, InterruptedException {
        List<ACL> list = new ArrayList<>() ;
        int perms =ZooDefs.Perms.ADMIN | ZooDefs.Perms.READ;
        ACL acl1 = new ACL(perms, new Id("world", "anyone"));
        ACL acl2 = new ACL(perms, new Id("ip", "192.168.1.158"));
        ACL acl3 = new ACL(perms, new Id("ip", "127.0.0.1"));
        list.add(acl1);
        list.add(acl2);
        list.add(acl3);
        zooKeeper.create("/tuling/lu","hello".getBytes(), list, CreateMode.PERSISTENT);
    }
}

package com.zealot.mybatisplusdemo.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/9/30 14:45
 */

@Controller
@RequestMapping("kafka")
@Slf4j
@Api(value = "Kafka", tags = {"Kafka"})
public class KafkaTopicController {

    @Resource
    private AdminClient adminClient;

    @GetMapping("listTopics")
    @ResponseBody
    @ApiOperation("获取主题列表")
    public Set<String> listAllTopic() throws ExecutionException, InterruptedException {
        ListTopicsResult result = adminClient.listTopics();
        return result.names().get();
    }

    @GetMapping("createTopics")
    @ResponseBody
    @ApiOperation("创建主题")
    public String createTopics() {
        NewTopic newTopic = new NewTopic("topicName", 1, (short) 1);
        CreateTopicsResult result = adminClient.createTopics(Collections.singleton(newTopic));
        return JSON.toJSONString(result);
    }

    @GetMapping("deleteTopics")
    @ResponseBody
    @ApiOperation("删除主题")
    public String deleteTopics(List<String> list) {
        DeleteTopicsResult result = adminClient.deleteTopics(list);
        return JSON.toJSONString(result);
    }

    @GetMapping("describeTopics")
    @ResponseBody
    @ApiOperation("查询主题详情")
    public String describeTopics(List<String> list) {
        DescribeTopicsResult result = adminClient.describeTopics(list);
        return JSON.toJSONString(result);
    }

    @GetMapping("describeCluster")
    @ResponseBody
    @ApiOperation("查询集群信息")
    public String describeCluster() {
        DescribeClusterResult result = adminClient.describeCluster();
        return JSON.toJSONString(result);
    }

}

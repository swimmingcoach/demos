package com.zealot.mybatisplusdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @GetMapping("listAllTopic")
    @ResponseBody
    @ApiOperation("获取主题列表")
    public Set<String> listAllTopic() throws ExecutionException, InterruptedException {
        ListTopicsResult result = adminClient.listTopics();
        return result.names().get();
    }

}

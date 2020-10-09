package com.zealot.mybatisplusdemo.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.acl.AccessControlEntry;
import org.apache.kafka.common.acl.AclBinding;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.acl.AclPermissionType;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePattern;
import org.apache.kafka.common.resource.ResourceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("createTopics")
    @ResponseBody
    @ApiOperation("创建主题")
    public String createTopics(@ApiParam(value = "主题名称", required = true)
                               @RequestParam String name,
                               @ApiParam(value = "分区数", required = true)
                               @RequestParam int numPartitions,
                               @ApiParam(value = "副本数", required = true)
                               @RequestParam short replicationFactor) {
        NewTopic newTopic = new NewTopic(name, numPartitions, replicationFactor);
        CreateTopicsResult result = adminClient.createTopics(Collections.singleton(newTopic));
        return JSON.toJSONString(result);
    }

    @PostMapping("deleteTopics")
    @ResponseBody
    @ApiOperation("删除主题")
    public String deleteTopics(@ApiParam(value = "主题名称列表", required = true)
                               @RequestParam List<String> list) {
        DeleteTopicsResult result = adminClient.deleteTopics(list);
        return JSON.toJSONString(result);
    }

    @PostMapping("describeTopics")
    @ResponseBody
    @ApiOperation("查询主题详情")
    public String describeTopics(@ApiParam(value = "主题名称列表", required = true)
                                 @RequestParam List<String> list) {
        DescribeTopicsResult result = adminClient.describeTopics(list);
        return JSON.toJSONString(result);
    }

    @PostMapping("describeCluster")
    @ResponseBody
    @ApiOperation("查询集群信息")
    public String describeCluster() {
        DescribeClusterResult result = adminClient.describeCluster();
        return JSON.toJSONString(result);
    }

    @PostMapping("createAclsOptions")
    @ResponseBody
    @ApiOperation("配置ACL")
    public String createAclsOptions() {
        ResourcePattern pattern = new ResourcePattern(ResourceType.TOPIC, "test", PatternType.LITERAL);
        AccessControlEntry entry = new AccessControlEntry("admin", "*", AclOperation.WRITE, AclPermissionType.ALLOW);
        AclBinding aclBinding = new AclBinding(pattern, entry);
        CreateAclsResult result = adminClient.createAcls(Collections.singleton(aclBinding));
        return JSON.toJSONString(result);
    }

}

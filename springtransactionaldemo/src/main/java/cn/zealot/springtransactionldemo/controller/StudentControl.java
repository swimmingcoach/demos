package cn.zealot.springtransactionldemo.controller;

import cn.zealot.pojo.Student;
import cn.zealot.springtransactionldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/18 16:55
 */
@Controller
public class StudentControl {

    @Autowired
    private StudentService studentService;

    @Autowired
    private JedisCluster jedisCluster;

    @GetMapping("/test/getAll")
    @ResponseBody
    public List<Student> test() {
        return studentService.getAll();
    }

    @GetMapping("/test/insert/{name}/{area}/{age}")
    @ResponseBody
    public Student insert(@PathVariable String name,
                          @PathVariable String area,
                          @PathVariable Integer age) {
        Student record = new Student();
        record.setName(name);
        record.setArea(area);
        record.setAge(age);
        return studentService.insert(record);
    }

    @GetMapping("/test/redis/get")
    @ResponseBody
    public String getFromRedis() {
        String s = jedisCluster.get("test");
        return s;
    }

    @GetMapping("/test/redis/set/{name}/{area}/{age}")
    @ResponseBody
    public Boolean setFromRedis(@PathVariable String name,
                                @PathVariable String area,
                                @PathVariable Integer age) {
        Student record = new Student();
        record.setName(name);
        record.setArea(area);
        record.setAge(age);
        jedisCluster.set("test", record.toString());
        return true;
    }
}

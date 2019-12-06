package cn.zealot.controller;

import cn.zealot.Student;
import cn.zealot.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/18 16:55
 */
@Controller
@Slf4j
public class StudentControl {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedissonClient redissonClient;

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
    public Student getFromRedis() {
        RBucket<Student> keyObj = redissonClient.getBucket("test");
        if (!keyObj.isExists()) {
            return null;
        }
        Student s = keyObj.get();
        return s;
    }

    @GetMapping("/test/update1")
    @ResponseBody
    public void update1() throws InterruptedException {
        Student record = new Student();
        record.setUid(1);
        record.setName("name1");
        record.setArea("area1");
        record.setAge(1);
        studentService.update1(record);
    }

    @GetMapping("/test/update2")
    @ResponseBody
    public void update2() throws InterruptedException {
        Student record = new Student();
        record.setUid(2);
        record.setName("name2");
        record.setArea("area2");
        record.setAge(2);
        studentService.update2(record);
    }

    @GetMapping("/test/redis/set/{name}/{area}/{age}")
    @ResponseBody
    public Boolean setIntoRedis(@PathVariable String name,
                                @PathVariable String area,
                                @PathVariable Integer age) {
        Student record = new Student();
        record.setName(name);
        record.setArea(area);
        record.setAge(age);
        RBucket<Student> keyObj = redissonClient.getBucket("test");
        keyObj.set(record);
        return true;
    }

    @GetMapping("/test/redis/set/{key}/{value}")
    @ResponseBody
    public Boolean setIntoRedis1(@PathVariable String key,
                                @PathVariable String value) {
        RBucket<String> keyObj = redissonClient.getBucket(key);
        return keyObj.trySet(value);
    }

}

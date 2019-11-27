package cn.zealot.redissondemo.service;

import cn.zealot.pojo.Student;
import cn.zealot.redissondemo.aop.RLock;
import cn.zealot.redissondemo.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/18 16:53
 */
@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedissonClient redissonClient;

    public List<Student> getAll() {
        return studentMapper.getAll();
    }

    @Transactional
    public Student insert(Student record) {
        studentMapper.insert(record);
        return ins(record);
    }

    @Transactional
    @Async
    @RLock("studentUpdate")
    public Student update(Student record) throws InterruptedException {
        log.debug("IN");
        Thread.sleep(10000);
        studentMapper.update(record);
        log.debug("OUT");
        return record;
    }

    @Transactional
    public Student ins(Student record) {
        if (record.getUid() % 2 == 0) {
            throw new RuntimeException();
        }

        return record;
    }
}

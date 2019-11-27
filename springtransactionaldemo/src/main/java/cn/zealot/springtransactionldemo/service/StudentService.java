package cn.zealot.springtransactionldemo.service;

import cn.zealot.pojo.Student;
import cn.zealot.springtransactionldemo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAll() {
        return studentMapper.getAll();
    }

    @Transactional
    public Student insert(Student record) {
        studentMapper.insert(record);
        return ins(record);
    }

    @Transactional
    public Student ins(Student record) {
        if (record.getUid() % 2 == 0) {
            throw new RuntimeException();
        }

        return record;
    }
}

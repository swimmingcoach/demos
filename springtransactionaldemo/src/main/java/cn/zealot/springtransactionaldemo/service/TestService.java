package cn.zealot.springtransactionaldemo.service;

import cn.zealot.springtransactionaldemo.mapper.TestMapper;
import cn.zealot.springtransactionaldemo.pojo.TestModel;
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
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<TestModel> getAll() {
        return testMapper.getAll();
    }

    @Transactional
    public TestModel insert(TestModel record) {
        testMapper.insert(record);
        return ins(record);
    }

    @Transactional
    public TestModel ins(TestModel record) {
        if (record.getId() % 2 == 0) {
            throw new RuntimeException();
        }

        return record;
    }
}

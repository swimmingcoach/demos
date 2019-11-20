package cn.zealot.springtransactionaldemo.controller;

import cn.zealot.springtransactionaldemo.pojo.TestModel;
import cn.zealot.springtransactionaldemo.service.TestService;
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
public class TestControl {

    @Autowired
    private TestService testService;

    @GetMapping("/test/getAll")
    @ResponseBody
    public List<TestModel> test() {
        return testService.getAll();
    }

    @GetMapping("/test/insert/{name}/{desc}")
    @ResponseBody
    public TestModel insert(@PathVariable String name,
                            @PathVariable String desc) {
        TestModel record = new TestModel();
        record.setName(name);
        record.setDesc(desc);
        return testService.insert(record);
    }
}

package cn.zealot.springtransactionaldemo.mapper;

import cn.zealot.springtransactionaldemo.pojo.TestModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/18 16:20
 */
@Repository
@Mapper
public interface TestMapper {

    @Select("select * from tb_test")
    List<TestModel> getAll();

    @Select("select * from tb_test")
    TestModel getAll(Integer id);

    @Insert("insert into tb_test(`name`, `desc`) values (#{name}, #{desc})")
    @Options(keyProperty = "id" , useGeneratedKeys = true)
    void insert(TestModel record);

}

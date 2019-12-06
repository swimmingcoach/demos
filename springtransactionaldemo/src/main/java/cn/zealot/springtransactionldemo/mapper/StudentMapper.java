package cn.zealot.springtransactionldemo.mapper;

import cn.zealot.Student;
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
public interface StudentMapper {

    @Select("select * from tb_student")
    List<Student> getAll();

    @Insert("insert into tb_student(`name`, `area`, `age`) values (#{name}, #{area}, #{age})")
    @Options(keyProperty = "id" , useGeneratedKeys = true)
    void insert(Student record);

}

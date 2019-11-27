package cn.zealot.redissondemo.mapper;

import cn.zealot.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/18 16:20
 */
public interface StudentMapper {

    @Select("select * from tb_student")
    List<Student> getAll();

    @Insert("insert into tb_student(`name`, `area`, `age`) values (#{name}, #{area}, #{age})")
    @Options(keyProperty = "id" , useGeneratedKeys = true)
    void insert(Student record);

    @Update("update tb_student set `name` = #{name}, `area` = #{area}, `age` = #{age} where `uid` = #{uid}")
    Integer update(Student record);

}

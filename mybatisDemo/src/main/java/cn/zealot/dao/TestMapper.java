package cn.zealot.dao;

import cn.zealot.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/19 15:25
 */
public interface TestMapper {
    Student selectStudent(int uid);

    List<Student> selectAll();

    int updateStudentNameByUid(@Param("uid") int uid, @Param("name") String name);
}

package cn.zealot;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/12 16:00
 */
@Data
public class Student implements Serializable {
    private Integer uid;
    private String name;
    private String area;
    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createtime;

    public Student() {
    }

    public Student(Integer uid, String name, String area, Integer age) {
        this.uid = uid;
        this.name = name;
        this.area = area;
        this.age = age;
    }

    public static int compareByAge(Student s1, Student s2) {
        return s1.getAge().compareTo(s2.getAge());
    }

    public int compareByAge2(Student s1, Student s2) {
        return s1.getAge().compareTo(s2.getAge()) * (-1);
    }

    public int compareByAge3(Student s2) {
        return this.getAge().compareTo(s2.getAge()) * (-1);
    }
}

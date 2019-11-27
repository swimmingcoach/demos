import cn.zealot.pojo.Student;
import org.junit.Test;

import java.util.Optional;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/13 14:12
 */
public class OptionalDemoTest {

    @Test
    public void test1() {
        Student defStudent = new Student(0, "name", "area", 0);
        Student zhangsan = new Student(1, "уехЩ", null, 19);

        Optional<Student> op1 = Optional.of(zhangsan);
        String area = op1.map(Student::getArea).orElse(defStudent.getArea());
        System.out.println(area);
    }
}

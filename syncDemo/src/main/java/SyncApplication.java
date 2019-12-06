import cn.zealot.Student;
import org.openjdk.jol.info.ClassLayout;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/28 14:23
 */
public class SyncApplication {

    private static final Student STUDENT = new Student(1, "����", "����", 21);

    public static void main(String[] args) {
//        System.out.println(ClassLayout.parseInstance(STUDENT).toPrintable());
        Integer i = 1024, k = 1024;
        System.out.println(ClassLayout.parseInstance(i).toPrintable());
        System.out.println(ClassLayout.parseInstance(k).toPrintable());
        System.out.println(1024 == 1024);
//        test();
    }

    private static void test() {
        // JavaĬ�����������ı����Ķ���ͷ
        synchronized (STUDENT) {
            System.out.println(STUDENT.toString());
        }
    }
}

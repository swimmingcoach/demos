import cn.zealot.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/11 14:13
 */
public class LambdaDemoMain {
    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        list.add(new Student(1,"张三", "苏州", 23));
        list.add(new Student(2,"张四", "洛阳", 22));
        list.add(new Student(3,"张五", "姬昌", 25));
        list.add(new Student(4,"张六", "常州", 21));
        list.add(new Student(5,"张七", "佛山", 27));

        /**
         * 1、什么是Lambda表达式
         * Lambda 表达式是一种匿名函数，简单地说，它是没有声明的方法，也即没有访问修饰符、返回值声明和名字。
         * 它可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使 Java 语言的表达能力得到了提升。
         * 2、Lambda表达式的语法
         * 基本语法:  (parameters) -> expression
         *    或者：(parameters) ->{ statements;
         **/
//        List<Student> __suzhous = filterStudent(list, (Student student) -> {
//            String suzhou = "苏州";
//            return suzhou.equals(student.getArea());
//        });
//        __suzhous.forEach((Student student) -> {
//            System.out.println(student);
//        });

        /**
         * 函数式接口的实例可以通过 lambda 表达式、 方法引用、构造方法引用来创建。
         * 方法引用是 lambda 表达式的语法糖，任何用方法引用的地方都可由lambda表达式替换，但是并不是所有的lambda表达式都可以用方法引用来替换。
         * 举例
         * 这就是一个打印集合所有元素的例子，value -> System.out.println(value) 是一个Consumer函数式接口， 这个函数式接口可以通过方法引用来替换。
         **/
//        __suzhous.forEach(System.out::println);

        List<Student> _bigger21 = filterStudent(list, student -> student.getAge().compareTo(21) > 0);

        /*
         * 静态方法引用来替代lambda表达式
         **/
        _bigger21.sort(Student::compareByAge);
        _bigger21.forEach(student -> System.out.println(student.toString()));

        /*
         * 实例对象的方法引用来替代lambda表达式
         **/
        _bigger21.sort(new Student()::compareByAge2);
        _bigger21.forEach(student -> System.out.println(student.toString()));

        /*
         * 类方法引用来替代lambda表达式
         **/
        _bigger21.sort(Student::compareByAge3);
        _bigger21.forEach(student -> System.out.println(student.toString()));
    }

    /**
     * 3、什么是函数式接口
     * 再对上面进行举例说明之前，必须先来理解下函数式接口，因为Lambda是建立在函数式接口的基础上的。
     * <p>
     * 记住！
     * <p>
     * （1）只包含一个抽象方法的接口，称为函数式接口。
     * <p>
     * （2）你可以通过 Lambda 表达式来创建该接口的对象。
     * <p>
     * （3）我们可以在任意函数式接口上使用 @FunctionalInterface 注解，这样做可以检测它是否是一个函数式接口，同时 javadoc 也会包含一条声明，说明这个接口是一个函数式接口。
     * <p>
     * 在实际开发者有两个比较常见的函数式接口：Runnable接口，Comparator接口
     **/
    @FunctionalInterface
    public interface StudentPredicate {
        Boolean test(Student student);
    }

    public static List<Student> filterStudent(List<Student> students, StudentPredicate p) {
        List<Student> list = new ArrayList<>();
        for (Student student : students) {
            if (p.test(student)) {
                list.add(student);
            }
        }
        return list;
    }

}



import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/12 15:14
 */

/*
 * 方法引用： 若Lambda体中的内容有方法已经实现了，我们可以使用方法引用
 *
 * 三种语法格式：
 *          1、对象：：实例方法名
 *          2、类：：静态方法名
 *          3、类：：实例方法名
 *
 **/
public class 方法引用 {
    // 对象：：实例方法名
    @Test
    public void test1() {
        PrintStream ps = System.out;
        Consumer<String> con = x -> ps.println(x);

        Consumer<String> con1 = ps::println;
        Consumer<String> con2 = System.out::println;
        con2.accept("Hello world 11");
    }

    // 类：：静态方法名
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compare;
    }

    // 类：：实例方法名
    @Test
    public void test3() {
        BiPredicate<Integer, Integer> bp = (x, y) -> x.equals(y);

        // 注意:
        BiPredicate<Integer, Integer> bp1 = Integer::equals;
    }
}

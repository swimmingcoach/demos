import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/12 14:30
 */

/*
 * Consumer<T> : 消费型接口
 *  void accept(T t);
 * Supplier<T> : 供给型接口
 *  T get();
 * Function<T, R> : 函数型接口
 *  R apply(T t);
 * Predicate<T> : 断言型接口
 *  boolean test(T t);
 **/
public class FunctionalInterfacesMain {

    // Consumer<T> 消费型接口
    @Test
    public void test1() {
        happy(1000.0, m -> System.out.println("消费" + m));
    }

    private void happy(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    // 供给型接口 Suppier<T>
    // 产生一些整数，并放入集合中
    @Test
    public void test2() {
        List<Integer> list = getNumList(10, () -> new Random().nextInt(100));
        list.forEach(System.out::println);
    }

    private List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    //FUnction<T, R> 函数型接口
    // 需求：处理字符串
    @Test
    public void test3() {
        String newStr = strHandler("\t\t\t Hello world   ", str -> str.trim());
        System.out.println(newStr);
    }
    private String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    // Predicate<T> 断言型接口
    // 需求: 把满足条件的字符串添加到集合中
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "zealot", "java", "Lambda", "My");
        List<String> ret = filterStr(list, s -> s.length() > 4);
        ret.forEach(System.out::println);
    }

    private List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> ret = new ArrayList<>();

        for (String s : list) {
            if (predicate.test(s)) {
                ret.add(s);
            }
        }
        return ret;
    }
}

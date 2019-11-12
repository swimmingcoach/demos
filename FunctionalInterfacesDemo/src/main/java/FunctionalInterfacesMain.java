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
 * Consumer<T> : �����ͽӿ�
 *  void accept(T t);
 * Supplier<T> : �����ͽӿ�
 *  T get();
 * Function<T, R> : �����ͽӿ�
 *  R apply(T t);
 * Predicate<T> : �����ͽӿ�
 *  boolean test(T t);
 **/
public class FunctionalInterfacesMain {

    // Consumer<T> �����ͽӿ�
    @Test
    public void test1() {
        happy(1000.0, m -> System.out.println("����" + m));
    }

    private void happy(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    // �����ͽӿ� Suppier<T>
    // ����һЩ�����������뼯����
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

    //FUnction<T, R> �����ͽӿ�
    // ���󣺴����ַ���
    @Test
    public void test3() {
        String newStr = strHandler("\t\t\t Hello world   ", str -> str.trim());
        System.out.println(newStr);
    }
    private String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    // Predicate<T> �����ͽӿ�
    // ����: �������������ַ�����ӵ�������
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

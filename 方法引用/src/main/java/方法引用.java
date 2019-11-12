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
 * �������ã� ��Lambda���е������з����Ѿ�ʵ���ˣ����ǿ���ʹ�÷�������
 *
 * �����﷨��ʽ��
 *          1�����󣺣�ʵ��������
 *          2���ࣺ����̬������
 *          3���ࣺ��ʵ��������
 *
 **/
public class �������� {
    // ���󣺣�ʵ��������
    @Test
    public void test1() {
        PrintStream ps = System.out;
        Consumer<String> con = x -> ps.println(x);

        Consumer<String> con1 = ps::println;
        Consumer<String> con2 = System.out::println;
        con2.accept("Hello world 11");
    }

    // �ࣺ����̬������
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compare;
    }

    // �ࣺ��ʵ��������
    @Test
    public void test3() {
        BiPredicate<Integer, Integer> bp = (x, y) -> x.equals(y);

        // ע��:
        BiPredicate<Integer, Integer> bp1 = Integer::equals;
    }
}

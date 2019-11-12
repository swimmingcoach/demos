import org.junit.Test;

import java.util.function.Supplier;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/12 15:35
 */
public class 构造器引用 {
    @Test
    public void test1() {
        Supplier<String> sup = () -> new String();

        Supplier<String> sup1 = String::new;
        System.out.println(sup1.get());
    }
}

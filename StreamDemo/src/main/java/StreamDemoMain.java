import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/11 15:57
 */
public class StreamDemoMain {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 3, -2, 2, -13, 7, 3, 5, 8, -1);
        List<Integer> list = numbers.stream()
                .filter(i -> i > 0)
                .distinct()
                .sorted(Integer::compareTo)
                .map(integer -> {
                    int ii = integer * integer;
                    System.out.println(integer + " 的平方为: " + ii);
                    return ii;
                })
                .collect(Collectors.toList());
    }
}

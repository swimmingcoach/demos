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
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> list = numbers.parallelStream()
                .filter(i -> i > 3)
                .distinct()
                .map(integer -> integer * integer)
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }
}

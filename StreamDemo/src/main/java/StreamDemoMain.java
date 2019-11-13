import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/11 15:57
 */
public class StreamDemoMain {
    List<Student> students = Arrays.asList(new Student("张三", "上海", 21),
            new Student("李四", "郑州", 18),
            new Student("王五", "银川", 22),
            new Student("孙六", "南阳", 17),
            new Student("孙六", "南阳", 17));

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 3, -2, 2, -13, 7, 3, 5, 8, -1);
        List<Integer> list = numbers.stream()
                .filter(i -> i > 0)
                .distinct()
                .sorted(Integer::compare)
                .map(integer -> {
                    int ii = integer * integer;
                    System.out.println(integer + " 的平方为: " + ii);
                    return ii;
                })
                .collect(Collectors.toList());
    }

    // 创建一个stream
    @Test
    public void test1() {
        // 1、可以通过Collection系列集合提供的stream()或者parallelStream()
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        Stream<String> stringStream = list.stream();
        Stream<String> stringStream1 = list.parallelStream();

        // 2、通过Arrays中的静态方法stream()获取数组流
        Student[] students = new Student[10];
        Stream<Student> studentStream = Arrays.stream(students);

        System.out.println("--------------------------------");
        // 3、通过Stream类中的静态方法of()
        Stream<String> stringStream2 = Stream.of("a", "b", "c", "d");
        Stream<SaiTouEnum> saiTouEnumStream = Stream.of(SaiTouEnum.S1, SaiTouEnum.S2, SaiTouEnum.S3, SaiTouEnum.S4, SaiTouEnum.S5, SaiTouEnum.S6);
        saiTouEnumStream.forEach(System.out::println);
        Stream<SaiTouEnum> saiTouEnumStream1 = Arrays.stream(SaiTouEnum.values());
        saiTouEnumStream1.forEach(System.out::println);

        System.out.println("--------------------------------");
        // 4、创建无限流
        //迭代
        Stream<Integer> integerStream = Stream.iterate(10, x -> x + 2);
        integerStream.limit(10).forEach(System.out::println);

        //生成
        Stream<Integer> integerStream1 = Stream.generate(() -> new Random().nextInt(10));
        integerStream1.limit(10).forEach(System.out::println);

    }

    /*
     * 中间操作：不会执行任何操作，在终止操作中执行，即惰性求值
     * 筛选与切片
     *      filter
     *      limit
     *      skip
     *      distinct
     **/
    @Test
    public void test2() {
        Stream<Integer> integerStream1 = Stream.iterate(1, x -> ++x);
        Stream<Integer> integerStream2 = integerStream1
                .skip(10)
                .limit(2)
                .filter(x -> {
                    System.out.println("中间操作：" + x);
                    return x % 2 == 0;
                });

        // 终止操作：一次性执行全部内容，即“惰性求值”
        integerStream2.forEach(System.out::println);
    }

    /*
     * 映射
     *      map：接受Lambda，将元素转换成其他形式或者提取消息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *      flatMap：接收一个函数作为参数，将流中的每个值都转换成另一个流，然后把所有流连接成一个新流。
     **/
    @Test
    public void test3() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "asdwqed");
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("--------------------------------");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(this::filterCha);

        streamStream.forEach(sm -> sm.forEach(System.out::println));

        System.out.println("--------------------------------");
        list.stream().flatMap(this::filterCha).forEach(System.out::println);
    }

    private Stream<Character> filterCha(String string) {
        List<Character> list = new ArrayList<>();
        for (Character character : string.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    /*
     * 排序
     *      自然排序：comparable
     *      定制排序：comparator
     **/
    @Test
    public void test4() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "asdwqed");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        list.stream()
                .sorted((s1, s2) -> {
                    return s1.length() - s2.length();
                })
                .forEach(System.out::println);
    }

    /*
     * 终止操作
     *      allMatch
     *      anyMatch
     *      noneMatch
     *      findFirst
     *      findAny
     *      count
     *      min
     *      max
     **/
    @Test
    public void test5() {
        System.out.println("allMatch: " + students.stream().allMatch(student -> student.getAge() > 20));
        System.out.println("anyMatch: " + students.stream().anyMatch(student -> student.getAge() > 20));
        System.out.println("noneMatch: " + students.stream().noneMatch(student -> student.getAge() > 20));

        Optional<Student> stu = students.parallelStream()
                .filter(student -> student.getAge() > 20)
                .findAny();
        System.out.println(stu.get());
    }
}

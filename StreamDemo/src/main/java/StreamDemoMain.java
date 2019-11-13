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
    List<Student> students = Arrays.asList(new Student("����", "�Ϻ�", 21),
            new Student("����", "֣��", 18),
            new Student("����", "����", 22),
            new Student("����", "����", 17),
            new Student("����", "����", 17));

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 3, -2, 2, -13, 7, 3, 5, 8, -1);
        List<Integer> list = numbers.stream()
                .filter(i -> i > 0)
                .distinct()
                .sorted(Integer::compare)
                .map(integer -> {
                    int ii = integer * integer;
                    System.out.println(integer + " ��ƽ��Ϊ: " + ii);
                    return ii;
                })
                .collect(Collectors.toList());
    }

    // ����һ��stream
    @Test
    public void test1() {
        // 1������ͨ��Collectionϵ�м����ṩ��stream()����parallelStream()
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        Stream<String> stringStream = list.stream();
        Stream<String> stringStream1 = list.parallelStream();

        // 2��ͨ��Arrays�еľ�̬����stream()��ȡ������
        Student[] students = new Student[10];
        Stream<Student> studentStream = Arrays.stream(students);

        System.out.println("--------------------------------");
        // 3��ͨ��Stream���еľ�̬����of()
        Stream<String> stringStream2 = Stream.of("a", "b", "c", "d");
        Stream<SaiTouEnum> saiTouEnumStream = Stream.of(SaiTouEnum.S1, SaiTouEnum.S2, SaiTouEnum.S3, SaiTouEnum.S4, SaiTouEnum.S5, SaiTouEnum.S6);
        saiTouEnumStream.forEach(System.out::println);
        Stream<SaiTouEnum> saiTouEnumStream1 = Arrays.stream(SaiTouEnum.values());
        saiTouEnumStream1.forEach(System.out::println);

        System.out.println("--------------------------------");
        // 4������������
        //����
        Stream<Integer> integerStream = Stream.iterate(10, x -> x + 2);
        integerStream.limit(10).forEach(System.out::println);

        //����
        Stream<Integer> integerStream1 = Stream.generate(() -> new Random().nextInt(10));
        integerStream1.limit(10).forEach(System.out::println);

    }

    /*
     * �м����������ִ���κβ���������ֹ������ִ�У���������ֵ
     * ɸѡ����Ƭ
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
                    System.out.println("�м������" + x);
                    return x % 2 == 0;
                });

        // ��ֹ������һ����ִ��ȫ�����ݣ�����������ֵ��
        integerStream2.forEach(System.out::println);
    }

    /*
     * ӳ��
     *      map������Lambda����Ԫ��ת����������ʽ������ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ�ء�
     *      flatMap������һ��������Ϊ�����������е�ÿ��ֵ��ת������һ������Ȼ������������ӳ�һ��������
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
     * ����
     *      ��Ȼ����comparable
     *      ��������comparator
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
     * ��ֹ����
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

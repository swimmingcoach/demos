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
        list.add(new Student("����", "����", 23));
        list.add(new Student("����", "����", 22));
        list.add(new Student("����", "����", 25));
        list.add(new Student("����", "����", 21));
        list.add(new Student("����", "��ɽ", 27));

        /**
         * 1��ʲô��Lambda���ʽ
         * Lambda ���ʽ��һ�������������򵥵�˵������û�������ķ�����Ҳ��û�з������η�������ֵ���������֡�
         * ������д������ࡢ�����Ĵ��롣��Ϊһ�ָ����յĴ�����ʹ Java ���Եı�������õ���������
         * 2��Lambda���ʽ���﷨
         * �����﷨:  (parameters) -> expression
         *    ���ߣ�(parameters) ->{ statements;
         **/
//        List<Student> __suzhous = filterStudent(list, (Student student) -> {
//            String suzhou = "����";
//            return suzhou.equals(student.getArea());
//        });
//        __suzhous.forEach((Student student) -> {
//            System.out.println(student);
//        });

        /**
         * ����ʽ�ӿڵ�ʵ������ͨ�� lambda ���ʽ�� �������á����췽��������������
         * ���������� lambda ���ʽ���﷨�ǣ��κ��÷������õĵط�������lambda���ʽ�滻�����ǲ��������е�lambda���ʽ�������÷����������滻��
         * ����
         * �����һ����ӡ��������Ԫ�ص����ӣ�value -> System.out.println(value) ��һ��Consumer����ʽ�ӿڣ� �������ʽ�ӿڿ���ͨ�������������滻��
         **/
//        __suzhous.forEach(System.out::println);

        List<Student> _bigger21 = filterStudent(list, student -> student.getAge().compareTo(21) > 0);

        /*
         * ��̬�������������lambda���ʽ
         **/
        _bigger21.sort(Student::compareByAge);
        _bigger21.forEach(student -> System.out.println(student.toString()));

        /*
         * ʵ������ķ������������lambda���ʽ
         **/
        _bigger21.sort(new Student()::compareByAge2);
        _bigger21.forEach(student -> System.out.println(student.toString()));

        /*
         * �෽�����������lambda���ʽ
         **/
        _bigger21.sort(Student::compareByAge3);
        _bigger21.forEach(student -> System.out.println(student.toString()));
    }

    /**
     * 3��ʲô�Ǻ���ʽ�ӿ�
     * �ٶ�������о���˵��֮ǰ��������������º���ʽ�ӿڣ���ΪLambda�ǽ����ں���ʽ�ӿڵĻ����ϵġ�
     * <p>
     * ��ס��
     * <p>
     * ��1��ֻ����һ�����󷽷��Ľӿڣ���Ϊ����ʽ�ӿڡ�
     * <p>
     * ��2�������ͨ�� Lambda ���ʽ�������ýӿڵĶ���
     * <p>
     * ��3�����ǿ��������⺯��ʽ�ӿ���ʹ�� @FunctionalInterface ע�⣬���������Լ�����Ƿ���һ������ʽ�ӿڣ�ͬʱ javadoc Ҳ�����һ��������˵������ӿ���һ������ʽ�ӿڡ�
     * <p>
     * ��ʵ�ʿ������������Ƚϳ����ĺ���ʽ�ӿڣ�Runnable�ӿڣ�Comparator�ӿ�
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

class Student {
    private String name;
    private String area;
    private Integer age;

    public Student() {
    }

    public Student(String name, String area, Integer age) {
        this.name = name;
        this.area = area;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", age=" + age +
                '}';
    }

    public static int compareByAge(Student s1, Student s2) {
        return s1.getAge().compareTo(s2.getAge());
    }

    public int compareByAge2(Student s1, Student s2) {
        return s1.getAge().compareTo(s2.getAge()) * (-1);
    }

    public int compareByAge3(Student s2) {
        return this.getAge().compareTo(s2.getAge()) * (-1);
    }
}

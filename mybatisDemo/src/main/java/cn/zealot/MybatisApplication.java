package cn.zealot;

import cn.zealot.dao.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/19 15:21
 */
@Slf4j
public class MybatisApplication {

    private final static String ____ = "----------------------------��{}�����----------------------------";

    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            List<Student> students = mapper.selectAll();
            log.debug(students.toString());

            /**
             * mybatisĬ�Ͽ���һ������
             * Ĭ������£�MyBatisֻ�����˱��صĻỰ���棬��������һ���Ự�е����ݽ��л��档
             * ��Ҳ���Ǵ�ҳ�˵��MyBatisһ�����棬һ���������������SqlSession��
             *
             * MyBatisһ����������й����������ģ�
             * ִ��SQL���Ĺ����У�
             * �״�ִ����ʱ�����ݿ��ȡ���������ݻᱻ�洢��һ�θ��ٻ����У�
             * ���ִ���������ʱ�ͻ�Ӹ��ٻ����ж�ȡ�����
             * �������ٴβ�ѯ���ݿ⡣
             * MyBatis�ṩ��Ĭ���»���Java HashMap�Ļ���ʵ�֡�
             *
             * �ص���Ҫ���ף�
             * MyBatisִ��SQL���֮��
             * ��������ִ�н�������棬
             * �Ժ���ִ����������ʱ�򣬻�ֱ�Ӵӻ������ý�����������ٴ�ִ��SQL��
             * ����һ��ִ����������»�ɾ������������ͻᱻ�����
             * ���潫���������֤һ�¡�
             **/

            // ��1�������ͬ��session����������ͬ��ѯ
            log.debug(____, 1);
            List<Student> _students = mapper.selectAll();
            log.debug(_students.toString());
            // ���ۣ�MyBatisֻ����1�����ݿ��ѯ��

            // ��2�������ͬ��session�������β�ͬ�Ĳ�ѯ��
            log.debug(____, 2);
            Student stu = mapper.selectStudent(1);
            log.debug(stu.toString());
            // ���ۣ�MyBatis�����������ݿ��ѯ��

            // ��3���������ͬsession��������ͬ��ѯ��
            log.debug(____, 3);
            try (SqlSession session1 = sqlSessionFactory.openSession()) {
                TestMapper mapper1 = session1.getMapper(TestMapper.class);
                List<Student> students1 = mapper1.selectAll();
                log.debug(students1.toString());
            }
            // ���ۣ�MyBatis�����������ݿ��ѯ��

            // ��4�������ͬ��session����ѯ֮��������ݣ��ٴβ�ѯ��ͬ����䡣
            log.debug(____, 4);
            if (mapper.updateStudentNameByUid(1, "��һһ") > 0) {
                List<Student> __students = mapper.selectAll();
                log.debug(__students.toString());
            }
            // ���ۣ����²���֮�󻺴�ᱻ�����
        }
    }

    @Test
    public void test2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            Student student = mapper.selectStudent(1);
            log.debug(student.toString());

            List<Student> students = mapper.selectAll();
            log.debug(students.toString());
        }

        // ������������󣬲�ͬsession����ͬ��ѯ��ӻ����л�ȡ����
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            Student student = mapper.selectStudent(1);
            log.debug(student.toString());

            List<Student> students = mapper.selectAll();
            log.debug(students.toString());
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            if (mapper.updateStudentNameByUid(1, "��һ") > 0) {
                Student student = mapper.selectStudent(1);
                log.debug(student.toString());

                List<Student> students = mapper.selectAll();
                log.debug(students.toString());
            }
        }
    }
}

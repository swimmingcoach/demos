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

    private final static String ____ = "----------------------------第{}种情况----------------------------";

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
             * mybatis默认开启一级缓存
             * 默认情况下，MyBatis只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。
             * 这也就是大家常说的MyBatis一级缓存，一级缓存的作用域是SqlSession。
             *
             * MyBatis一级缓存的运行过程是这样的：
             * 执行SQL语句的过程中，
             * 首次执行它时从数据库获取的所有数据会被存储在一段高速缓存中，
             * 今后执行这条语句时就会从高速缓存中读取结果，
             * 而不是再次查询数据库。
             * MyBatis提供了默认下基于Java HashMap的缓存实现。
             *
             * 重点是要明白：
             * MyBatis执行SQL语句之后，
             * 这条语句的执行结果被缓存，
             * 以后再执行这条语句的时候，会直接从缓存中拿结果，而不是再次执行SQL。
             * 但是一旦执行新增或更新或删除操作，缓存就会被清除。
             * 下面将分情况来验证一下。
             **/

            // 第1种情况：同个session进行两次相同查询
            log.debug(____, 1);
            List<Student> _students = mapper.selectAll();
            log.debug(_students.toString());
            // 结论：MyBatis只进行1次数据库查询。

            // 第2种情况：同个session进行两次不同的查询。
            log.debug(____, 2);
            Student stu = mapper.selectStudent(1);
            log.debug(stu.toString());
            // 结论：MyBatis进行两次数据库查询。

            // 第3种情况：不同session，进行相同查询。
            log.debug(____, 3);
            try (SqlSession session1 = sqlSessionFactory.openSession()) {
                TestMapper mapper1 = session1.getMapper(TestMapper.class);
                List<Student> students1 = mapper1.selectAll();
                log.debug(students1.toString());
            }
            // 结论：MyBatis进行两次数据库查询。

            // 第4种情况：同个session，查询之后更新数据，再次查询相同的语句。
            log.debug(____, 4);
            if (mapper.updateStudentNameByUid(1, "赵一一") > 0) {
                List<Student> __students = mapper.selectAll();
                log.debug(__students.toString());
            }
            // 结论：更新操作之后缓存会被清除。
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

        // 启动二级缓存后，不同session的相同查询会从缓存中获取数据
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            Student student = mapper.selectStudent(1);
            log.debug(student.toString());

            List<Student> students = mapper.selectAll();
            log.debug(students.toString());
        }

        try (SqlSession session = sqlSessionFactory.openSession()) {
            TestMapper mapper = session.getMapper(TestMapper.class);

            if (mapper.updateStudentNameByUid(1, "赵一") > 0) {
                Student student = mapper.selectStudent(1);
                log.debug(student.toString());

                List<Student> students = mapper.selectAll();
                log.debug(students.toString());
            }
        }
    }
}

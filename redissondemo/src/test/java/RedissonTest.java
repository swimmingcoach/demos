import cn.zealot.redissondemo.RedissondemoApplication;
import cn.zealot.redissondemo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/27 15:46
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedissondemoApplication.class)
public class RedissonTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void test1() throws InterruptedException {
    }
}


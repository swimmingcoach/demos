package cn.zealot;

import org.junit.Test;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/21 10:16
 */
public class PythonDemoApplication {

    @Test
    public void test1() throws Exception {
        String pyFile = "C:\\test.py";

        try (BufferedReader br = PythonUtils.doPythonCommand(pyFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() throws ParseException {
        String str = "2019-11-21 16:46:32.111111";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date d = sdf.parse(str);
        System.out.println(d.getTime());
    }

    @Test
    public void test3() throws ParseException {
        //'timestart': '2019-11-22 15:23:19.820655'
        long ocrtime = 1574408219655L;
        Date d = new Date(ocrtime);
        Date d1 = strToDate("2019-11-22 15:23:19.820655");
        System.out.println(d);
        System.out.println(d1);
    }

    private Date strToDate(String str) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.fff");
            return sdf.parse(str);
        } catch (Exception e) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(str);
        }
    }
}

package cn.zealot;

import org.python.util.PythonInterpreter;

import java.io.IOException;
import java.io.OutputStream;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/10/17 9:39
 */
public class JythonDemo {

    public static void main(String[] args) throws Exception {

        PythonInterpreter interpreter = new PythonInterpreter();
        OutputStream os = new OutputStream() {
            private static final String PREFIX = "";
            private StringBuilder sb = new StringBuilder(PREFIX);

            @Override
            public void write(int b) throws IOException {
                if (b == 10 || b == 13) {
                    if (sb.length() > PREFIX.length()) {
                        System.out.println(sb.toString());
                        sb.delete(PREFIX.length(), sb.length());
                    }
                } else {
                    sb.append((char) b);
                }
            }
        };

        interpreter.setOut(os);
        interpreter.setErr(os);

        interpreter.execfile("jythondemo\\test.py");

        os.close();
    }
}
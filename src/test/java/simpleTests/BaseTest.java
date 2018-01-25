package simpleTests;

import org.apache.commons.io.FileUtils;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseTest {
    @Attachment(value = "log", type = "text/plain")
    public static String attachTextLog() {
        try {
            File file = new File("logs/allureLog.log");
            String log = FileUtils.readFileToString(file);
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
            return log;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

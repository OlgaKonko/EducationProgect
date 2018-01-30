package logger;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class LogListener implements ITestListener {

    public void onStart(ITestContext iTestContext) {
    }

    public void onTestSuccess(ITestResult iTestResult) {
        attachTextLog();
    }

    public void onTestFailure(ITestResult iTestResult) {
        onTestSuccess(iTestResult);
    }

    @Attachment(value = "log", type = "text/plain")
    private static String attachTextLog() {
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

    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
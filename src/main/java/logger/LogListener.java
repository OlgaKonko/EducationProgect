package logger;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static constants.Appenders.Default;

public class LogListener implements ITestListener {
    public Logger log;

    @Override
    public void onStart(ITestContext context) {
        try {
            Class testClass = context.getAllTestMethods()[0].getRealClass();
            LogAppender appender = (LogAppender) testClass.getAnnotation(LogAppender.class);
            this.log = Logger.getLogger(appender.value().getDefaultName());
        } catch (Exception e) {
            this.log = Logger.getLogger(Default.getDefaultName());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("start " + result.getName() + " test");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(iTestResult.getTestName() + " finish and success");
        attachTextLog();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(iTestResult.getTestName() + " finish and fail");
        attachTextLog();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getTestName() + " skiped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onFinish(ITestContext context) {
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
}
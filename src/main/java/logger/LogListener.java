package logger;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static constants.Appenders.Default;
import static constants.LoggerLayout.GeneralLayout;

public class LogListener implements ITestListener, IClassListener, IInvokedMethodListener {
    public Logger log;

    @Override
    public void onStart(ITestContext context) {
            this.log = Logger.getLogger(Default.getDefaultName());
    }

    @Override
    public void onBeforeClass(ITestClass testClass) {
        try {
            Class invokedClass = testClass.getTestMethods()[0].getRealClass();
            LogAppender appender = (LogAppender) invokedClass.getAnnotation(LogAppender.class);
            this.log = Logger.getLogger(appender.value().getDefaultName());
        } catch (Exception e) {
            this.log = Logger.getLogger(Default.getDefaultName());
        }
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }


    @Override
    public void onTestStart(ITestResult result) {

        RollingFileAppender allureAppender = null;
        try {
            allureAppender = new RollingFileAppender(GeneralLayout.getLayout(), getFileName(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        allureAppender.setName(getAppenderName(result));
        ThreadLoggingFilter threadLogFilter = new ThreadLoggingFilter(Thread.currentThread().getName());
        allureAppender.addFilter(threadLogFilter);
        Logger.getRootLogger().addAppender(allureAppender);
        log.info("start " + result.getName() + " test");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("!!!s");
        log.info(iTestResult.getName() + " finish and success");
        ((RollingFileAppender) Logger.getRootLogger().getAppender(getAppenderName(iTestResult))).close();
        Logger.getRootLogger().removeAppender(getAppenderName(iTestResult));

        attachTextLog(getFileName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("!!!f");
        log.info(iTestResult.getName() + " finish and fail");
        ((RollingFileAppender) Logger.getRootLogger().getAppender(getAppenderName(iTestResult))).close();
        Logger.getRootLogger().removeAppender(getAppenderName(iTestResult));

        attachTextLog(getFileName(iTestResult));

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("!!!sk ");
        log.info(result.getName() + " skipped");
        ((RollingFileAppender) Logger.getRootLogger().getAppender(getAppenderName(result))).close();
        Logger.getRootLogger().removeAppender(getAppenderName(result));

        attachTextLog(getFileName(result));

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("!!!fs");
        log.info(result.getName() + " failed but within success percentage");
        ((RollingFileAppender) Logger.getRootLogger().getAppender(getAppenderName(result))).close();
        Logger.getRootLogger().removeAppender(getAppenderName(result));

        attachTextLog(getFileName(result));

    }

    @Override
    public void onAfterClass(ITestClass testClass) {

    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            FileUtils.deleteDirectory(new File("target/logs/allure/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "log", type = "text/plain")
    private String attachTextLog(String filename) {
        System.out.println("!!!attach");
        try {
            File file = new File(filename);
            String logAttachment = FileUtils.readFileToString(file);
            file.delete();
            return logAttachment;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getFileName(ITestResult iTestResult) {
        return "target/logs/allure/" + iTestResult.getName() + "Thread" + Thread.currentThread().getId() + ".log";
    }

    private String getAppenderName(ITestResult iTestResult) {
        return "allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId();
    }
}
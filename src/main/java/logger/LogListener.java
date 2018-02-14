package logger;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.testng.*;

import java.io.File;
import java.io.IOException;

import static constants.Appenders.Default;
import static constants.LoggerLayout.GeneralLayout;

public class LogListener implements ITestListener, IClassListener, IInvokedMethodListener {
    public Logger log;

    @Override
    public void onStart(ITestContext context) {
        //  this.log = Logger.getRootLogger();
        System.out.println("!!! 0" + Thread.currentThread().getName());
        try {
            Class invokedClass = context.getAllTestMethods()[0].getRealClass();
            LogAppender appender = (LogAppender) invokedClass.getAnnotation(LogAppender.class);
            this.log = Logger.getLogger(appender.value().getDefaultName());
        } catch (Exception e) {
            this.log = Logger.getLogger(Default.getDefaultName());
        }
    }

    @Override
    public void onBeforeClass(ITestClass testClass) {
        System.out.println("!!! 1 " + Thread.currentThread().getName() + " " + testClass.getName());
        try {
            Class invokedClass = testClass.getTestMethods()[0].getRealClass();
            LogAppender appender = (LogAppender) invokedClass.getAnnotation(LogAppender.class);
            this.log = Logger.getLogger(appender.value().getDefaultName());
        } catch (Exception e) {
            this.log = Logger.getLogger(Default.getDefaultName());
        }
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("!!! 2 " + Thread.currentThread().getName());
    }


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("!!! 3 " + Thread.currentThread().getId());
        RollingFileAppender allureAppender = null;
        try {
            allureAppender = new RollingFileAppender(GeneralLayout.getLayout(), getFileName(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        allureAppender.setName("allureAppender" + result.getName() + "Thread" + Thread.currentThread().getId());
        ThreadLoggingFilter threadLogFilter = new ThreadLoggingFilter(Thread.currentThread().getName());
        allureAppender.addFilter(threadLogFilter);
        Logger.getRootLogger().addAppender(allureAppender);
        System.out.println("!!! a3a add appender " + allureAppender.getName() + " to logger " + log.getName());
        log.info("start " + result.getName() + " test");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("!!! 4 " + Thread.currentThread().getId());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("!!! 5s " + Thread.currentThread().getId());
        log.info(iTestResult.getName() + " finish and success");
        String appenderName = "allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId();
        RollingFileAppender appender = (RollingFileAppender) Logger.getRootLogger().getAppender(appenderName);
        System.out.println("!!! a5a try to delete appender " + appender.getName() + " from logger " + log.getName());
        appender.close();
        Logger.getRootLogger().removeAppender("allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId());

        attachTextLog(getFileName(iTestResult));
        //  log.removeAppender("allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("!!! 5f" + Thread.currentThread().getId());
        String appenderName = "allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId();
        RollingFileAppender appender = (RollingFileAppender) Logger.getRootLogger().getAppender(appenderName);
        System.out.println("!!! a5a try to delete appender " + appender.getName() + " from logger " + log.getName());
        appender.finalize();
        System.out.println("!!! a5b appender " + appender.getName() + " from logger " + log.getName() + " is closed ");
        Logger.getRootLogger().removeAppender("allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId());

        attachTextLog(getFileName(iTestResult));
        //  log.removeAppender("allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId());
        System.out.println("!!! 5fend" + Thread.currentThread().getId());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("!!! 5sk" + Thread.currentThread().getId());
        // log.info(result.getName() + " skipped");
        //log.getAppender("allureAppender" + result.getName() + "Thread" + Thread.currentThread().getId()).close();
        // attachTextLog(getFileName(result));
        //  log.removeAppender("allureAppender" + result.getName() + "Thread" + Thread.currentThread().getId());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("!!! 5fs" + Thread.currentThread().getId());
        //  log.info(result.getName() + " failed but within success percentage");
        //  log.getAppender("allureAppender" + result.getName() + "Thread" + Thread.currentThread().getId()).close();
        //  attachTextLog(getFileName(result));
        // log.removeAppender("allureAppender" + result.getName() + "Thread" + Thread.currentThread().getId());

    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        System.out.println("!!! 6" + Thread.currentThread().getId());

    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("!!! 7" + Thread.currentThread().getName());
        try {
            FileUtils.deleteDirectory(new File("target/logs/allure/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String attachTextLog(String filename) {
        System.out.println("!!!! " + filename);
        try {
            File file = new File(filename);
            String logAttachment = FileUtils.readFileToString(file);
            //PrintWriter writer = new PrintWriter(file);
            file.delete();
            //writer.print("");
            //writer.close();
            return logAttachment;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getFileName(ITestResult iTestResult) {
        return "target/logs/allure/" + iTestResult.getName() + "Thread" + Thread.currentThread().getId() + ".log";
    }
}
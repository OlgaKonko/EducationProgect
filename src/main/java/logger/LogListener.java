package logger;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.testng.*;

import java.io.File;
import java.io.IOException;

import static constants.Appenders.Default;

public class LogListener implements ITestListener, IClassListener, IInvokedMethodListener {
    public Logger log;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("!!! 0");
    }

    @Override
    public void onBeforeClass(ITestClass testClass) {
        System.out.println("!!! 1");
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
        /*if (method.isTestMethod()) {
            String filename = "logs/allure/" + testResult.getName() + "Thread" + Thread.currentThread().getId() + ".log";
            RollingFileAppender allureAppender = new RollingFileAppender();
            allureAppender.setName("allureAppender"+ testResult.getName() + "Thread" + Thread.currentThread().getId());
            allureAppender.setLayout(new PatternLayout("%d{ABSOLUTE} %5p %C{1}:%M:%L:%t - %m%n"));
            allureAppender.setFile(filename);
            allureAppender.setAppend(false);
            //ThreadLoggingFilter threadLogFilter = new ThreadLoggingFilter(Thread.currentThread().getName());
           // allureAppender.addFilter(threadLogFilter);
            allureAppender.setMaxFileSize("100MB");
            allureAppender.setMaxBackupIndex(10);
            allureAppender.activateOptions();
            log.addAppender(allureAppender);
            log.info("start " + testResult.getName() + " test");

        }*/
    }


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("!!! 3");
        String filename = "logs/allure/" + result.getName() + "Thread" + Thread.currentThread().getId() + ".log";
        RollingFileAppender allureAppender = new RollingFileAppender();
        allureAppender.setName("allureAppender" + result.getName() + "Thread" + Thread.currentThread().getId());
        allureAppender.setLayout(new PatternLayout("%d{ABSOLUTE} %5p %C{1}:%M:%L:%t - %m%n"));
        allureAppender.setFile(filename);
        allureAppender.setAppend(false);
        ThreadLoggingFilter threadLogFilter = new ThreadLoggingFilter(Thread.currentThread().getName());
        allureAppender.addFilter(threadLogFilter);
        allureAppender.setMaxFileSize("100MB");
        allureAppender.setMaxBackupIndex(10);
        allureAppender.activateOptions();

        log.addAppender(allureAppender);
        log.info("start " + result.getName() + " test");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            //String filename = ((RollingFileAppender) log.getAppender("allureAppender")).getFile();
            //  String filename = "logs/allure/"+testResult.getName()+"Thread"+Thread.currentThread().getId()+".log";
            //  System.out.println("!!! 4 "+filename);
            // attachTextLog(filename);

            // log.removeAppender("allureAppender"+ testResult.getName() + "Thread" + Thread.currentThread().getId());
        }
        // log.removeAllAppenders();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("!!! 5s");
        log.info(iTestResult.getName() + " finish and success");
        String filename = "logs/allure/" + iTestResult.getName() + "Thread" + Thread.currentThread().getId() + ".log";
        attachTextLog(filename);
        log.removeAppender("allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId());
        //  attachTextLog();
        // log.removeAppender("allureAppender");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("!!! 5f");
        log.info(iTestResult.getName() + " finish and fail");
        String filename = "logs/allure/" + iTestResult.getName() + "Thread" + Thread.currentThread().getId() + ".log";

        attachTextLog(filename);
        log.removeAppender("allureAppender" + iTestResult.getName() + "Thread" + Thread.currentThread().getId());
        // attachTextLog();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getTestName() + " skiped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        System.out.println("!!! 6");

    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("!!! 7");
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String attachTextLog(String filename) {
        System.out.println("!!!! " + filename);
        try {
            File file = new File(filename);
            String logAttachment = FileUtils.readFileToString(file);
            //PrintWriter writer = new PrintWriter(file);
            //file.delete();
            //writer.print("");
            //writer.close();
            return logAttachment;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
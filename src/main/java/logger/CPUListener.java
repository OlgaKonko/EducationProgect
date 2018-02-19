package logger;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CPUListener implements IInvokedMethodListener {
    private Map<Long, ThreadTime> threadTimes = new HashMap<>();

    @Override
    public synchronized void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            threadTimes.put(Thread.currentThread().getId(), new ThreadTime());
        }
    }

    @Override
    public synchronized void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            ThreadTime threadTime = threadTimes.get(Thread.currentThread().getId());
            long currentTime = System.nanoTime();
            long currentCPU = threadTime.threadMXBean.getCurrentThreadUserTime();
            DecimalFormat df = new DecimalFormat("#.000");
            String cpuLoadPercent = df.format((currentCPU - threadTime.baseCPU) * 100.0 / (currentTime - threadTime.startTime)) + "%";
            writeCPULoad(cpuLoadPercent);

        }
    }

    @Step("CPU load is {cpuLoadPercent}")
    private void writeCPULoad(String cpuLoadPercent) {
        Logger.getRootLogger().info("load: " +
                cpuLoadPercent + "%");
    }
}

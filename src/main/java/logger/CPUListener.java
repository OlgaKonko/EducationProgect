package logger;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;

public class CPUListener implements IInvokedMethodListener {
    ThreadMXBean threadMXBean;
    long start;
    long base_cpu;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            threadMXBean =
                    ManagementFactory.getThreadMXBean();
            if (!threadMXBean.isCurrentThreadCpuTimeSupported()) {
                System.out.println("CPU Usage monitoring is not avaliable!");
            } else {
                threadMXBean.setThreadCpuTimeEnabled(true);
            }
            start = System.nanoTime();
            base_cpu = threadMXBean.getCurrentThreadUserTime();
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            long time = System.nanoTime();
            long cpu = threadMXBean.getCurrentThreadUserTime();

            DecimalFormat df = new DecimalFormat("#.000");
            String cpuLoadPercent = df.format((cpu - base_cpu) * 100.0 / (time - start)) + "%";
            writeCPULoad(cpuLoadPercent);

        }
    }

    @Step("CPU load is {cpuLoadPercent}")
    private void writeCPULoad(String cpuLoadPercent) {
        Logger.getRootLogger().info("load: " +
                cpuLoadPercent + "%");
    }
}

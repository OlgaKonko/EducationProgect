package logger;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class ThreadTime {
    ThreadMXBean threadMXBean;
    long startTime;
    long baseCPU;

    public ThreadTime() {

        threadMXBean = ManagementFactory.getThreadMXBean();
        if (threadMXBean.isCurrentThreadCpuTimeSupported()) {
            threadMXBean.setThreadCpuTimeEnabled(true);
        }
        startTime = System.nanoTime();
        baseCPU = threadMXBean.getCurrentThreadUserTime();
    }
}

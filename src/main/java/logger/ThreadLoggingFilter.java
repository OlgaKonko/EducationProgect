package logger;

import org.apache.log4j.spi.*;


public class ThreadLoggingFilter extends Filter {
    String threadName;

    public ThreadLoggingFilter(String _threadName) {
        this.threadName = _threadName;
    }

    @Override
    public int decide(final LoggingEvent event) {
        if (event.getThreadName() != null && event.getThreadName().equals(this.threadName)) {
            return ACCEPT;
        }
        return DENY;
    }
}
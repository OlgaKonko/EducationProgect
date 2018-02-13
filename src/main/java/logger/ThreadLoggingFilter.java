package logger;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;


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
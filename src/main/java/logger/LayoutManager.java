package logger;

import constants.LoggerLayout;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

import java.util.Enumeration;

public class LayoutManager {

    public static void changeLayout(Logger log, LoggerLayout layout) {

        changeLogLayout(log, layout);
        changeLogLayout(Logger.getRootLogger(), layout);

    }

    public static void changeLogLayout(Logger log, LoggerLayout layout) {
        Enumeration appenders = log.getAllAppenders();
        while (appenders.hasMoreElements()) {
            ((Appender) appenders.nextElement()).setLayout(layout.getLayout());
        }
    }
}

package logger;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;

import static constants.Appenders.changeAppender;

public class RequestPrintStream {
    Logger log;
    PrintStream myPrintStream;
    String logMessage;

    public RequestPrintStream(Logger logger) throws IOException {
        super();
        //log = logger;
        log = Logger.getLogger(changeAppender(logger.getName()));

    }

    public PrintStream getPrintStream() {
        if (myPrintStream == null) {
            OutputStream output = new OutputStream() {
                private StringBuilder myStringBuilder = new StringBuilder();

                @Override
                public void write(int b) throws IOException {
                    this.myStringBuilder.append((char) b);
                }

                @Override
                public void flush() {
                    writeLogMessage(this.myStringBuilder);
                    myStringBuilder = new StringBuilder();
                }
            };

            myPrintStream = new PrintStream(output, true);
        }

        return myPrintStream;
    }


    private void writeLogMessage(StringBuilder myStringBuilder) {

     /*   Enumeration appenders = log.getAllAppenders();
        while (appenders.hasMoreElements()) {
            ((Appender) appenders.nextElement()).setLayout(new PatternLayout("%m%n"));}*/
        logMessage = myStringBuilder.toString().replaceAll("\n$|\r\n$", " ");
        if (!logMessage.isEmpty() && !logMessage.endsWith(">") && !logMessage.endsWith("}")) {
            logMessage = logMessage.substring(0, logMessage.length() - 1);
        }
        if (!logMessage.isEmpty() && !logMessage.contains("none")) {

            log.info(logMessage);
        }
      /*  appenders = log.getAllAppenders();
        while (appenders.hasMoreElements()) {
            ((Appender) appenders.nextElement()).setLayout(new PatternLayout("%d{ABSOLUTE} %5p %C{1}:%M:%L:%t - %m%n"));}*/
    }
}
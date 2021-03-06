package logger;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static constants.Appenders.changeAppender;

public class ResponsePrintStream {
    Logger log;
    PrintStream myPrintStream;
    String logMessage;

    public ResponsePrintStream(Logger logger) {
        super();
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
        logMessage = myStringBuilder.toString().replaceAll("\n$|\r\n$", "");
        if (!logMessage.isEmpty()) {
            log.info("Response body: \n" + logMessage);
        }
    }
}
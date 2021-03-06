package logger;

import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.io.PrintStream;


public class LoggerCather {
    public static void catchLog(Logger log, RequestSpecification defaultRequest) {
        PrintStream requestCapture = new RequestPrintStream(log).getPrintStream();
        PrintStream responseCapture = new ResponsePrintStream(log).getPrintStream();
        defaultRequest.filters(
                new RequestLoggingFilter(LogDetail.PATH, requestCapture),
                new RequestLoggingFilter(LogDetail.METHOD, requestCapture),
                new RequestLoggingFilter(LogDetail.BODY, requestCapture),
                new ResponseLoggingFilter(LogDetail.BODY, responseCapture));
    }
}

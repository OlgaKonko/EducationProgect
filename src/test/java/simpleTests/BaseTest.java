package simpleTests;

import constants.Appenders;
import logger.LogListener;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners(LogListener.class)
public class BaseTest {
    private static final Logger log = Logger.getLogger(Appenders.Default.getDefaultName());
    /*@BeforeMethod
    public void testStartLog(Method method) {
        Test test = method.getAnnotation(Test.class);
        log.info("start test that " + test.description());
    }*/
}

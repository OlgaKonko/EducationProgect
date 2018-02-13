package simpleTests;

import business.TestBO;
import constants.Appenders;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Fail")
public class FallTest extends BaseTest {
    // private static final Logger log = Logger.getLogger(Default.getDefaultName());
    private TestBO testBO;

    @BeforeMethod
    public void setData() {
        this.testBO = new TestBO();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Operations with non-exist function")
    @Test(testName = "call non exist function", description = "Call non-exist function")
//,  threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
    public void nonExistFunctionFailTest() {
        testBO.callNonExistentFunction();
    }
}

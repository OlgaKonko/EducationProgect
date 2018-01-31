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

import static constants.Appenders.Default;

@Feature("Fail")
public class FallTest extends BaseTest {
    // private static final Logger log = Logger.getLogger(Default.getDefaultName());
    private TestBO testBO;

    public Logger logger() {
        return Logger.getLogger(Appenders.Default.getDefaultName());
    }
    @BeforeMethod
    public void setData() {
        this.testBO = new TestBO();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Operations with non-exist function")
    @Test(testName = "call non exist function", description = "Call non-exist function")
    public void nonExistFunctionFailTest() {
        testBO.callNonExistentFunction();
    }
}

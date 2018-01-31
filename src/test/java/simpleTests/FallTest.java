package simpleTests;

import business.TestBO;
import constants.Appenders;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Features("Fail")
@Title("Test with non-exist function")
@Description("Test api errors")
public class FallTest extends BaseTest {
    private static final Logger log = Logger.getLogger(Appenders.Default.getDefaultName());
    private TestBO testBO;

    @BeforeMethod
    public void setData() {
        this.testBO = new TestBO();
    }

    @Severity(SeverityLevel.NORMAL)
    @Stories("Operations with non-exist function")
    @Title("Call non-exist function")
    @Test(description = "Call non-exist function")
    public void nonExistFunctionFailTest() {
        // log.info("start non exist function fail test");
        testBO.callNonExistentFunction();
    }
}

package simpleTests;

import business.TestBO;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Fail")
public class FallTest extends BaseTest {
    private TestBO testBO;

    @BeforeMethod
    public void setData() {
        this.testBO = new TestBO();

    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Operations with non-exist function")
    @Test(groups = {"fail"}, testName = "call non exist function", description = "Call non-exist function")
    public void nonExistFunctionFailTest() {
        testBO.callNonExistentFunction();
    }
}

package business;

import assertions.AssertStatusCode;
import client.TestClient;
import com.jayway.restassured.response.Response;
import exeptions.BaseException;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static constants.Appenders.Default;
import static logger.LoggerCatcher.catchLog;

public class TestBO {

    private static final Logger log = Logger.getLogger(Default.getDefaultName());
    private TestClient testClient;

    public TestBO() {
        log.debug("create store BO and set client");
        this.testClient = new TestClient();
        catchLog(log, testClient.defaultRequest);
    }

    @Step("call non-existent function")
    public boolean callNonExistentFunction() {
        try {
            log.info("start calling non-existent function");
            Response fallResponse = testClient.nonExistentFunction();
            AssertStatusCode.assertStatusCodeIsOk(fallResponse);
            log.info("non-existent function has been gotten");
            return fallResponse.body().toString().isEmpty();
        } catch (Throwable t) {
            log.error("non-existent function can't be got");
            throw new BaseException("get non-existent function", t);
        }

    }
}

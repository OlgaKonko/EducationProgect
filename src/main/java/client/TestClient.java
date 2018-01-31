package client;

import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;

import static com.jayway.restassured.RestAssured.given;
import static constants.Appenders.Store;
import static constants.EndpointConstants.BASE_URL;
import static constants.EndpointConstants.TEST_URL;
import static logger.LoggerCather.catchLog;

public class TestClient extends HttpClient {
    private static final Logger log = Logger.getLogger(Store.getDefaultName());

    public TestClient() {
        super(TEST_URL);
        log.info("connecting to " + BASE_URL + TEST_URL);
        catchLog(log, defaultRequest);
    }

    public Response nonExistentFunction() {
        log.info("send request non-existent function");
        return given(defaultRequest)
                .get();
    }
}

package client;

import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;

import static com.jayway.restassured.RestAssured.given;
import static constants.Appenders.Store;
import static constants.EndpointConstants.*;
import static logger.LoggerCather.catchLog;

public class StoreClient extends HttpClient {
    private static final Logger log = Logger.getLogger(Store.getDefaultName());

    public StoreClient() {
        super(STORE_URL);
        log.info("connecting to " + BASE_URL + USER_URL);
        catchLog(log, defaultRequest);
    }

    public Response getInventory() {
        log.info("send request to get inventory");
        return given(defaultRequest).
                get(STORE_INVENTORY_URL);

    }

}

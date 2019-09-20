package client;

import com.jayway.restassured.response.Response;
import model.order.Order;
import org.apache.log4j.Logger;

import static com.jayway.restassured.RestAssured.given;
import static constants.Appenders.Store;
import static constants.EndpointConstants.*;
import static logger.LoggerCatcher.catchLog;

public class OrderClient extends HttpClient {
    private static final Logger log = Logger.getLogger(Store.getDefaultName());

    public OrderClient() {
        super(ORDER_URL);
        log.info("connecting to " + BASE_URL + ORDER_URL);
        catchLog(log, defaultRequest);
    }

    public Response createOrder(Order order) {
        log.info("send request to create order");
        return given(defaultRequest).
                body(order).
                post();
    }

    public Response getOrder(long orderId) {
        log.info("send request to get order");
        return given(defaultRequest).
                get(ID, orderId);

    }

    public Response deleteOrder(long orderId) {
        log.info("send request to delete order");
        return given(defaultRequest).
                delete(ID, orderId);
    }

}

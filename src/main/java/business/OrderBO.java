package business;

import client.OrderClient;
import com.jayway.restassured.response.Response;
import constants.ResponseCode;
import exeptions.StoreException;
import io.qameta.allure.Step;
import model.order.Order;
import org.apache.log4j.Logger;

import static assertions.AssertDetails.assertOrderData;
import static assertions.AssertStatusCode.assertStatusCodeIsOk;
import static constants.Appenders.Store;

public class OrderBO {
    private static final Logger log = Logger.getLogger(Store.getDefaultName());
    private OrderClient orderClient;

    public OrderBO() {
        log.debug("create order BO and set client");
        this.orderClient = new OrderClient();
    }

    @Step("Create order")
    public Order createOrder(Order order) {
        try {
            log.info("start creating order, order id : " + order.getId());
            if (isOrderExist(order.getId())) {
                deleteOrder(order);
            }

            Response createOrderResponse = orderClient.createOrder(order);
            assertStatusCodeIsOk(createOrderResponse);
            Order createdOrder = getOrder(order.getId());
            assertOrderData(order, createdOrder);
            log.info("order has been created, order id : " + order.getId());
            return createdOrder;
        } catch (Throwable t) {
            log.error("order can't be created, order id : " + order.getId());
            throw new StoreException("add order", order, t);
        }
    }

    @Step("Get order")
    public Order getOrder(long orderId) {
        try {
            log.info("start getting order, order id : " + orderId);
            Response getOrderResponse = orderClient.getOrder(orderId);
            assertStatusCodeIsOk(getOrderResponse);
            log.info("order with id : " + orderId + " has been gotten");
            return getOrderResponse.as(Order.class);
        } catch (Throwable t) {
            log.error("order can't be got , order id : " + orderId);
            throw new StoreException("get order", orderId, t);
        }

    }

    @Step("Check if order exist")
    public boolean isOrderExist(long orderId) {
        try {
            log.info("start checking order, order id : " + orderId);
            Response getOrderResponse = orderClient.getOrder(orderId);
            if (getOrderResponse.statusCode() == ResponseCode.SUCCESS_CODE.getCode())
                log.info("order with id : " + orderId + " is exist");
            else
                log.info("order with id :" + orderId + " is not exist");
            return (getOrderResponse.statusCode() == ResponseCode.SUCCESS_CODE.getCode());
        } catch (Throwable t) {
            log.error("order can't be checked, order id : " + orderId);
            throw new StoreException("check order", orderId, t);
        }

    }

    @Step("Delete order")
    public void deleteOrder(Order order) {
        try {
            log.info("start deleting order image, order id : " + order.getId());
            Response deleteOrderResponse = orderClient.deleteOrder(order.getId());
            assertStatusCodeIsOk(deleteOrderResponse);
            assert !isOrderExist(order.getId()) : "Error - order has not been deleted";
            log.info("order has been deleted, order id : " + order.getId());
        } catch (Throwable t) {
            log.error("order can't be deleted, order id : " + order.getId());
            throw new StoreException("delete order", order, t);
        }

    }
}

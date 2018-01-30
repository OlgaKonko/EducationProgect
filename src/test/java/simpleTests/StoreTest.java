package simpleTests;

import business.OrderBO;
import business.StoreBO;
import data.OrderGenerator;
import model.order.Order;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static constants.Appenders.Store;

@Features("Store")
@Title("Test with store")
@Description("Test store api operations")
public class StoreTest extends BaseTest {
    private static final Logger log = Logger.getLogger(Store.getDefaultName());

    @Severity(SeverityLevel.BLOCKER)
    @Stories("Operations with orders")
    @Title("Order create and delete")
    @Test(description = "Create order and delete it")
    public void createOrder() {
        log.info("start create order test");
        Order order = OrderGenerator.testOrder();
        OrderBO orderBO = new OrderBO();
        orderBO.createOrder(order);
        orderBO.deleteOrder(order);

    }

    @Stories("Operations with store")
    @Title("Get inventory")
    @Test(description = "Get store inventory")
    public void checkInventory() {
        log.info("start check inventory test");
        StoreBO storeBO = new StoreBO();
        storeBO.getInventory();
    }
}

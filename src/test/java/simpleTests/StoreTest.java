package simpleTests;

import business.OrderBO;
import business.StoreBO;
import data.OrderGenerator;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import logger.LogAppender;
import model.order.Order;
import org.testng.annotations.Test;

import static constants.Appenders.Store;

@Feature("Store")
@LogAppender(Store)
public class StoreTest extends BaseTest {

    @Severity(SeverityLevel.BLOCKER)
    @Story("Operations with orders")
    @Test(groups = {"smoke"}, testName = "create and delete order", description = "Create order and delete it")
    public void createOrder() {
        Order order = OrderGenerator.testOrder();
        OrderBO orderBO = new OrderBO();
        orderBO.createOrder(order);
        orderBO.deleteOrder(order);

    }

    @Story("Operations with store")
    @Test(testName = "check inventory", description = "Get store inventory")
    public void checkInventory() {
        StoreBO storeBO = new StoreBO();
        storeBO.getInventory();
    }
}

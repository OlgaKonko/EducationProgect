package business;

import assertions.AssertStatusCode;
import client.StoreClient;
import com.jayway.restassured.response.Response;
import exeptions.StoreException;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import java.util.Map;

import static constants.Appenders.Store;

public class StoreBO {
    private static final Logger log = Logger.getLogger(Store.getDefaultName());
    private StoreClient storeClient;

    public StoreBO() {
        log.debug("create store BO and set client");
        this.storeClient = new StoreClient();
    }

    @Step("Get inventory")
    public Map<String, Integer> getInventory() {
        try {
            log.info("start getting inventory");
            Response inventoryResponse = storeClient.getInventory();
            AssertStatusCode.assertStatusCodeIsOk(inventoryResponse);
            log.info("inventory has been gotten");
            return inventoryResponse.jsonPath().getMap("");
        } catch (Throwable t) {
            log.error("inventory can't be got");
            throw new StoreException("get inventory", t);
        }

    }
}

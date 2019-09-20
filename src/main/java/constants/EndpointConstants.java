package constants;

import java.io.InputStream;
import java.util.Properties;

public class EndpointConstants {
    public static final String BASE_URL = getURL();
    // public static final String BASE_URL = "http://petstore.swagger.io/v2";
    public static final String PET_URL = "/pet";
    public static final String UPLOAD_PET_IMAGE_URL = "/uploadImage";
    public static final String PET_FIND_BY_STATUS_URL = "/findByStatus";
    public static final String ORDER_URL = "/store/order";
    public static final String STORE_URL = "/store";
    public static final String STORE_INVENTORY_URL = "/inventory";
    public static final String USER_URL = "/user";
    public static final String CREATE_USERS_BY_ARRAY_URL = "/createWithArray";
    public static final String CREATE_USERS_BY_LIST_URL = "/createWithList";
    public static final String USER_LOGIN_URL = "/login";
    public static final String USER_LOGOUT_URL = "/logout";
    public static final String ID = "/{id}";

    public static final String TEST_URL = "/test";

    private static String getUrlName() {
        return System.getProperty("buld.env", "petstore");
    }

    private static String getURL() {
        Properties prop = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            try (InputStream resourceStream = classLoader.getResourceAsStream("testEnvironment.properties")) {
                prop.load(resourceStream);
                return prop.getProperty(getUrlName());

            } catch (Exception e) {
            }
        }
        return "";
    }
}

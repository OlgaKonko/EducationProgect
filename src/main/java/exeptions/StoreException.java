package exeptions;

import model.order.Order;

public class StoreException extends BaseException {

    public StoreException(String message, Order order, Throwable cause) {
        super(message, "order id : " + order.getId(), cause);
    }

    public StoreException(String message, long orderId, Throwable cause) {
        super(message, "order id : " + orderId, cause);
    }

    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }
}

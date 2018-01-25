package model.bulder;

import constants.OrderStatus;
import model.order.Order;

public class OrderBuilder {
    private Order order;

    public OrderBuilder() {
        order = new Order();
    }

    public OrderBuilder id(long id) {
        order.setId(id);
        return this;
    }

    public OrderBuilder petId(long petId) {
        order.setPetId(petId);
        return this;
    }

    public OrderBuilder quantity(long quantity) {
        order.setQuantity(quantity);
        return this;

    }

    public OrderBuilder shipDate(String shipDate) {
        order.setShipDate(shipDate);
        return this;
    }

    public OrderBuilder status(OrderStatus status) {
        order.setStatus(status);
        return this;
    }

    public OrderBuilder complete(boolean complete) {
        order.setComplete(complete);
        return this;
    }

    public Order build() {
        return order;

    }
}

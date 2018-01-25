package data;

import constants.OrderStatus;
import model.bulder.OrderBuilder;
import model.order.Order;

import static data.RandomGenerator.*;


public class OrderGenerator {
    public static Order testOrder() {
        return new OrderBuilder().
                id(randomNumber()).
                petId(randomNumber()).
                quantity(randomNumber()).
                shipDate(randomDate()).
                status(OrderStatus.Placed).
                complete(randomBoolean()).
                build();
    }

}

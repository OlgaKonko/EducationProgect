package model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import constants.OrderStatus;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Order {

    @JsonProperty("id")
    private long id;
    @JsonProperty("petId")
    private long petId;
    @JsonProperty("quantity")
    private long quantity;
    @JsonProperty("shipDate")
    private String shipDate;
    @JsonProperty("status")
    private OrderStatus status;
    @JsonProperty("complete")
    private boolean complete;

    public Order() {
    }

    public Order(long id, long petId, long quantity, String shipDate, OrderStatus status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public Order(long id, long petId, long quantity, String shipDate, OrderStatus status) {
        this(id, petId, quantity, shipDate, status, false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            Order order = (Order) obj;
            return (new EqualsBuilder()).
                    append(this.id, order.id).
                    append(this.petId, order.petId).
                    append(this.quantity, order.quantity).
                    append(this.shipDate, order.shipDate).
                    append(this.status, order.status).
                    append(this.complete, order.complete).
                    isEquals();
        } else {
            return false;
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean getComplete() {
        return complete;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

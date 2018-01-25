package constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    Placed("Placed"),
    Approved("approved"),
    Delivered("delivered");

    @JsonValue
    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getStatusName() {
        return name;
    }
}

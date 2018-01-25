package constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetStatus {
    Available("available"),
    Pending("pending"),
    Sold("sold");

    private String name;

    PetStatus(String name) {
        this.name = name;
    }

    @JsonValue
    public String getStatusName() {
        return name;
    }
}

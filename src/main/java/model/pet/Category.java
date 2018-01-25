package model.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Category {

    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;

    public Category() {
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            Category category = (Category) obj;
            return (new EqualsBuilder()).
                    append(this.id, category.id).
                    append(this.name, category.name).
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package model.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Tag {
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;

    public Tag() {
    }

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            Tag tag = (Tag) obj;
            return (new EqualsBuilder()).
                    append(this.id, tag.id).
                    append(this.name, tag.name).
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

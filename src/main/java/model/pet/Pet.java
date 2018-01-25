package model.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import constants.PetStatus;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.List;

public class Pet {
    @JsonProperty("id")
    private long id;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<Tag> tags;
    @JsonProperty("status")
    private PetStatus status;

    public Pet() {
    }

    public Pet(int id, Category category, String name, List<String> photoUrls, List<Tag> tags, PetStatus status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            Pet pet = (Pet) obj;
            return (new EqualsBuilder()).
                    append(this.id, pet.id).
                    append(this.category, pet.category).
                    append(this.name, pet.name).
                    append(this.photoUrls, pet.photoUrls).
                    append(this.tags, pet.tags).
                    append(this.status, pet.status).
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }
}

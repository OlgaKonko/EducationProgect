package model.bulder;

import constants.PetStatus;
import model.pet.Category;
import model.pet.Pet;
import model.pet.Tag;

import java.util.List;

public class PetBuilder {
    private Pet pet;

    public PetBuilder() {
        pet = new Pet();
    }

    public PetBuilder id(long id) {
        pet.setId(id);
        return this;
    }

    public PetBuilder category(Category category) {
        pet.setCategory(category);
        return this;
    }

    public PetBuilder name(String name) {
        pet.setName(name);
        return this;

    }

    public PetBuilder photoUrls(List<String> photoUrls) {
        pet.setPhotoUrls(photoUrls);
        return this;
    }

    public PetBuilder tags(List<Tag> tags) {
        pet.setTags(tags);
        return this;
    }

    public PetBuilder status(PetStatus status) {
        pet.setStatus(status);
        return this;
    }

    public Pet build() {
        return pet;

    }
}

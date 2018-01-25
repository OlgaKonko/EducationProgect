package data;

import constants.PetStatus;
import model.bulder.PetBuilder;
import model.pet.Category;
import model.pet.Pet;
import model.pet.Tag;

import java.util.ArrayList;
import java.util.Arrays;

import static constants.DefaultTags.Small;
import static constants.DefaultTags.White;
import static data.RandomGenerator.randomNumber;
import static data.RandomGenerator.randomString;

public class PetGenerator {
    public static Pet testPet() {
        return new PetBuilder().
                id(randomNumber()).
                category(new Category(randomNumber(), randomString())).
                name(randomString()).
                photoUrls(new ArrayList<String>(Arrays.asList(randomString(), randomString()))).
                tags(new ArrayList<Tag>(Arrays.asList(Small.getTag(), White.getTag()))).
                status(PetStatus.Available).
                build();
    }
}

package simpleTests;

import business.PetBO;
import constants.Appenders;
import constants.DefaultTags;
import constants.PetStatus;
import data.PetGenerator;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import logger.LogAppender;
import model.pet.Pet;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static constants.Appenders.Pet;

@Feature("Pet")
@Test
@LogAppender(Pet)
public class PetTests extends BaseTest {
    private Pet pet;
    private PetBO petBO;

    public Logger logger() {
        return Logger.getLogger(Appenders.Pet.getDefaultName());
    }

    @BeforeMethod
    public void setData() {
        this.pet = PetGenerator.testPet();
        this.petBO = new PetBO();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Story("Operations with user pet")
    @Test(testName = "create and delete pet", description = "Create pet and delete it")
    public void simplePet() {
        petBO.createPet(pet);
        petBO.deletePet(pet);
    }

    @Story("Operations with user pet")
    @Test(testName = "update pet", description = "Create pet and update its name and tags")
    public void updatePet() {
        petBO.createPet(pet);
        pet.setName("Blacky");
        pet.getTags().add(DefaultTags.Black.getTag());
        petBO.updatePet(pet);
        petBO.deletePet(pet);
    }

    @Story("Operations with user pet")
    @Test(testName = "update pet status", description = "Create pet and update his status")
    public void updatePetStatus() {
        petBO.createPet(pet);
        petBO.updatePetStatus(pet, PetStatus.Sold);
        petBO.deletePet(pet);
    }

    @Story("Operations with user pet")
    @Test(testName = "upload pet photo", description = "Create pet and upload image to it")
    public void uploadPetPhoto() {
        petBO.createPet(pet);
        petBO.uploadPetImage("cat.jpg", pet.getId());
        petBO.deletePet(pet);
    }

    @Story("Operations with existing pets data")
    @Test(testName = "get available pets", description = "Get available pets")
    public void getPets() {
        petBO.getPetsByStatus();
    }
}

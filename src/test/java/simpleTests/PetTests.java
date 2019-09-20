package simpleTests;

import business.PetBO;
import data.PetGenerator;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import logger.LogAppender;
import model.pet.Pet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static constants.Appenders.Pet;

@Feature("Pet")
@LogAppender(Pet)
public class PetTests extends BaseTest {
    private Map<Long, PetBO> petBOs = new HashMap<>();

    @BeforeMethod(alwaysRun = true)
    public void setData() {
        // this.pet = PetGenerator.testPet();
        //logger().info("create pet with id"+pet.getId());
        this.petBOs.put(Thread.currentThread().getId(), new PetBO());
    }

    @Severity(SeverityLevel.BLOCKER)
    @Story("Operations with user pet")
    @Test(groups = {"smoke"}, testName = "create and delete pet", description = "Create pet and delete it")
    //  @Test(testName = "create and delete pet", description = "Create pet and delete it", invocationCount = 2, threadPoolSize = 2)
    public void simplePet() {
        Pet pet = PetGenerator.testPet();
        petBOs.get(Thread.currentThread().getId()).createPet(pet);
        petBOs.get(Thread.currentThread().getId()).deletePet(pet);
    }
/*
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
    }*/
}

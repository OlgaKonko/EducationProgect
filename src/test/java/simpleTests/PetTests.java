package simpleTests;

import business.PetBO;
import constants.Appenders;
import constants.DefaultTags;
import constants.PetStatus;
import data.PetGenerator;
import model.pet.Pet;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Features("Pet")
@Title("Test with pets")
@Description("Test pet api operations")
public class PetTests extends BaseTest {
    private static final Logger log = Logger.getLogger(Appenders.Pet.getDefaultName());
    private Pet pet;
    private PetBO petBO;

    @BeforeTest
    public void setData() {
        this.pet = PetGenerator.testPet();
        this.petBO = new PetBO();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Stories("Operations with user pet")
    @Title("Pet create and delete")
    @Test(description = "Create pet and delete it")
    public void simplePet() {
        log.info("start simple pet test");
        petBO.createPet(pet);
        petBO.deletePet(pet);
        attachTextLog();
    }

    @Title("Pet update")
    @Stories("Operations with user pet")
    @Test(description = "Create pet and update his name and tags")
    public void updatePet() {
        log.info("start update pet test");
        petBO.createPet(pet);
        pet.setName("Blacky");
        pet.getTags().add(DefaultTags.Black.getTag());
        petBO.updatePet(pet);
        petBO.deletePet(pet);
        attachTextLog();
    }

    @Title("Pet status update")
    @Stories("Operations with user pet")
    @Test(description = "Create pet and update his status")
    public void updatePetStatus() {
        log.info("start update pet status test");
        petBO.createPet(pet);
        petBO.updatePetStatus(pet, PetStatus.Sold);
        petBO.deletePet(pet);
        attachTextLog();
    }

    @Title("Pet's photo upload")
    @Stories("Operations with user pet")
    @Test(description = "Create pet and upload image to it")
    public void uploadPetPhoto() {
        log.info("start upload pet photo test");
        petBO.createPet(pet);
        petBO.uploadPetImage("cat.jpg", pet.getId());
        petBO.deletePet(pet);
        attachTextLog();
    }

    @Title("Get pets")
    @Stories("Operations with existing pets data")
    @Test(description = "Get available pets")
    public void getPets() {
        log.info("start get pets test");
        petBO.getPetsByStatus();
        attachTextLog();
    }
}

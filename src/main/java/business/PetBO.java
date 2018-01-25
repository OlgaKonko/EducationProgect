package business;

import client.PetClient;
import com.jayway.restassured.response.Response;
import constants.PetStatus;
import constants.ResponseCode;
import exeptions.PetException;
import model.pet.ApiResponse;
import model.pet.Pet;
import org.apache.log4j.Logger;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static assertions.AssertDetails.assertPetData;
import static assertions.AssertStatusCode.assertStatusCodeIsOk;
import static constants.Appenders.Pet;

public class PetBO {
    private static final Logger log = Logger.getLogger(Pet.getDefaultName());
    private PetClient petClient;

    public PetBO() {
        log.debug("create pet BO and set client");
        this.petClient = new PetClient();
    }

    @Step("Create pet")
    public Pet createPet(Pet pet) {
        try {
            log.info("start creating pet, pet id : " + pet.getId());
            if (isPetExist(pet.getId())) {
                updatePet(pet);
            } else {
                Response createPetResponse = petClient.createPet(pet);
                assertStatusCodeIsOk(createPetResponse);
            }

            Pet createdPet = getPet(pet.getId());
            assertPetData(pet, createdPet);
            log.info("pet has been created, pet id : " + pet.getId());
            return createdPet;
        } catch (Throwable t) {
            log.error("pet can't be created, pet id : " + pet.getId());
            throw new PetException("add pet", pet, t);
        }

    }

    @Step("Get pet")
    public Pet getPet(long petId) {
        try {
            log.info("start getting pet, pet id : " + petId);
            Response getPetResponse = petClient.getPet(petId);
            assertStatusCodeIsOk(getPetResponse);
            log.info("pet with id : " + petId + " has been gotten");
            return getPetResponse.as(Pet.class);
        } catch (Throwable t) {
            log.error("pet can't be got , pet id : " + petId);
            throw new PetException("get pet", petId, t);
        }

    }

    @Step("Check if pet exist")
    public boolean isPetExist(long petId) {
        try {
            log.info("start checking pet, pet id : " + petId);
            Response getPetResponse = petClient.getPet(petId);

            if (getPetResponse.statusCode() == ResponseCode.SUCCESS_CODE.getCode())
                log.info("pet with id : " + petId + " is exist");
            else
                log.info("pet with id :" + petId + " is not exist");
            return (getPetResponse.statusCode() == ResponseCode.SUCCESS_CODE.getCode());
        } catch (Throwable t) {
            log.error("pet can't be checked, pet id : " + petId);
            throw new PetException("check pet", petId, t);
        }

    }

    @Step("Update pet")
    public Pet updatePet(Pet pet) {
        try {
            log.info("start updating pet, pet id : " + pet.getId());
            Response updatedPetResponse = petClient.updatePetData(pet);
            assertStatusCodeIsOk(updatedPetResponse);
            Pet updatedPet = updatedPetResponse.as(Pet.class);
            assertPetData(pet, updatedPet);
            log.info("pet with id : " + pet.getId() + " has been updated");
            return updatedPet;
        } catch (Throwable t) {
            log.error("pet can't be updated, pet id : " + pet.getId());
            throw new PetException("update pet", pet, t);
        }

    }

    @Step("Update pet status")
    public Pet updatePetStatus(Pet pet, PetStatus status, String petName) {
        try {
            log.info("start updating pet status, pet id : " + pet.getId() + " new status: " + status);
            Response updatedPetResponse = petClient.updatePetStatus(pet.getId(), status, petName);
            assertStatusCodeIsOk(updatedPetResponse);
            Pet updatedPet = getPet(pet.getId());
            assert updatedPet.getStatus().equals(status) && updatedPet.getName().equals(petName) : "Error - pet not updated";
            log.info("pet status has been updated, pet id : " + pet.getId());
            return updatedPet;
        } catch (Throwable t) {
            log.error("pet status can't be updated, pet id : " + pet.getId());
            throw new PetException("update pet status or name", pet, t);
        }

    }

    public Pet updatePetStatus(Pet pet, PetStatus status) {
        return updatePetStatus(pet, status, pet.getName());
    }

    @Step("Upload pet image")
    public ApiResponse uploadPetImage(String filename, long petId, String metadata) {
        try {
            log.info("start uploading pet image, pet id : " + petId + " image file name: " + filename);
            ApiResponse response = petClient.uploadPetImage(new File(filename), petId, metadata);
            assertStatusCodeIsOk(response);
            log.info("pet image has been uploaded, pet id : " + petId);
            return response;
        } catch (Throwable t) {
            log.error("pet image can't be uploaded, pet id : " + petId + " image file name: " + filename);
            throw new PetException("update pet image", filename, petId, t);
        }

    }

    public ApiResponse uploadPetImage(String filename, long petId) {
        return uploadPetImage(filename, petId, "photo");
    }

    @Step("Delete pet")
    public void deletePet(Pet pet) {
        try {
            log.info("start deleting pet image, pet id : " + pet.getId());
            Response deletePetResponse = petClient.deletePet(pet.getId());
            assertStatusCodeIsOk(deletePetResponse);
            assert !isPetExist(pet.getId()) : "Error - pet has not been deleted";
            log.info("pet has been deleted, pet id : " + pet.getId());
        } catch (Throwable t) {
            log.error("pet can't be deleted, pet id : " + pet.getId());
            throw new PetException("delete pet", pet, t);
        }

    }

    @Step("Get pets")
    public List<Pet> getPetsByStatus(PetStatus neededStatus) {
        try {
            log.info("start getting " + neededStatus + " pets");
            Response getPetsResponse = petClient.getPetsByStatus(neededStatus);
            log.info(neededStatus + " pets have been gotten");
            return Arrays.asList(getPetsResponse.as(Pet[].class));
        } catch (Throwable t) {
            log.error(neededStatus + "pet can't be got");
            throw new PetException("get pet by status", neededStatus, t);
        }
    }

    public List<Pet> getPetsByStatus() {
        return getPetsByStatus(PetStatus.Available);
    }
}

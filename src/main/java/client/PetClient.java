package client;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import constants.PetStatus;
import model.pet.ApiResponse;
import model.pet.Pet;
import org.apache.log4j.Logger;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;
import static constants.Appenders.Pet;
import static constants.EndpointConstants.*;
import static logger.LoggerCather.catchLog;

public class PetClient extends HttpClient {
    private static final Logger log = Logger.getLogger(Pet.getDefaultName());

    public PetClient() {
        super(PET_URL);
        log.info("connecting to " + BASE_URL + USER_URL);
        catchLog(log, defaultRequest);
    }

    public Response createPet(Pet pet) {
        log.info("send request to create pet");
        return given(defaultRequest)
                .body(pet)
                .post();
    }

    public Response updatePetData(Pet updatedPet) {
        log.info("send request to update pet");
        return given(defaultRequest)
                .body(updatedPet)
                .put();
    }

    public Response getPetsByStatus(PetStatus neededStatus) {
        log.info("send request to get pet by status");
        return given(defaultRequest)
                .parameters("status", neededStatus.getStatusName())
                .get(PET_FIND_BY_STATUS_URL);
    }

    public Response getPet(long petId) {
        log.info("send request to get pet");
        return given(defaultRequest)
                .get(ID, petId);
    }

    public Response updatePetStatus(long petId, PetStatus status, String petName) {
        log.info("send request to update pet by status");
        return given(defaultRequest)
                .parameters("name", petName, "status", status.getStatusName())
                .contentType(ContentType.URLENC)
                .post(ID, petId);
    }

    public Response deletePet(long petId) {
        log.info("send request to delete pet");
        return given(defaultRequest)
                .delete(ID, petId);
    }

    public ApiResponse uploadPetImage(File file, long petId, String metadata) {
        log.info("send request to upload pet image");
        return given(defaultRequest)
                .multiPart("file", file)
                .formParam("additionalMetadata", metadata)
                .contentType("multipart/form-data")
                .pathParam("id", petId)
                .post(ID + UPLOAD_PET_IMAGE_URL)
                .getBody().as(ApiResponse.class);
    }

}

package exeptions;

import constants.PetStatus;
import model.pet.Pet;

public class PetException extends BaseException {

    public PetException(String message, Pet pet, Throwable cause) {
        super(message, "pet id : " + pet.getId() + ", pet name : " + pet.getName(), cause);
    }

    public PetException(String message, long petId, Throwable cause) {
        super(message, "pet id : " + petId, cause);
    }

    public PetException(String message, PetStatus status, Throwable cause) {
        super(message, "status : " + status.getStatusName(), cause);
    }

    public PetException(String message, String filename, long petId, Throwable cause) {
        super(message, "pet id : " + petId + ", file name : " + filename, cause);
    }
}

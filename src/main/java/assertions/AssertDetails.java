package assertions;

import model.order.Order;
import model.pet.Pet;
import model.user.User;

public class AssertDetails {
    public static void assertUserData(User expectedUser, User actualUser) {
        assert expectedUser.equals(actualUser) : "Error - actual user not equals expected";
    }

    public static void assertPetData(Pet expectedPet, Pet actualPet) {
        assert expectedPet.equals(actualPet) : "Error - actual pet not equals expected";
    }

    public static void assertOrderData(Order expectedOrder, Order actualOrder) {
        assert expectedOrder.equals(actualOrder) : "Error - actual order not equals expected";
    }

}

package data;

import model.bulder.UserBuilder;
import model.user.User;

import static data.RandomGenerator.randomNumber;
import static data.RandomGenerator.randomString;

public class UserGenerator {
    public static User testUser() {
        return new UserBuilder().
                id(randomNumber()).
                username(randomString()).
                firstName(randomString()).
                lastName(randomString()).
                email(randomString()).
                password(randomString()).
                phone(randomString()).
                userStatus(randomNumber()).
                build();
    }

}

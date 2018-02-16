package simpleTests;

import business.UserBO;
import constants.Appenders;
import data.UserGenerator;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import logger.LogAppender;
import model.user.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static data.RandomGenerator.randomString;

@Feature("User")
@LogAppender(Appenders.User)
public class UserTests extends BaseTest {
    private Map<Long, UserBO> userBOs = new HashMap<>();
    // private User user;
    //  private UserBO userBO;

    @BeforeMethod
    public void createNewUser() {
        User user = UserGenerator.testUser();
        this.userBOs.put(Thread.currentThread().getId(), new UserBO(user));
        // this.userBO = new UserBO(user);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Story("Operations with user")
    @Test(groups = {"smoke"}, testName = "create and delete user", description = "Create user and delete him")
    public void simpleUser() {
        userBOs.get(Thread.currentThread().getId()).createUser();
        userBOs.get(Thread.currentThread().getId()).deleteUser();

    }

    @Severity(SeverityLevel.BLOCKER)
    @Story("Operations with user")
    @Test(testName = "login and logout", description = "Login and logout to created user")
    public void login() {
        userBOs.get(Thread.currentThread().getId()).createUser();
        userBOs.get(Thread.currentThread().getId()).logIn();
        userBOs.get(Thread.currentThread().getId()).logOut();
        userBOs.get(Thread.currentThread().getId()).deleteUser();
    }

    @Story("Operations with user")
    @Test(testName = "update user", description = "Update user name and phone")
    public void updateUser() {
        userBOs.get(Thread.currentThread().getId()).createUser();
        userBOs.get(Thread.currentThread().getId()).logIn();
        User newUser = userBOs.get(Thread.currentThread().getId()).getUser();
        newUser.setPhone(randomString());
        newUser.setLastName(randomString());
        userBOs.get(Thread.currentThread().getId()).updateUser(newUser);
        userBOs.get(Thread.currentThread().getId()).logOut();
        userBOs.get(Thread.currentThread().getId()).deleteUser();
    }

}

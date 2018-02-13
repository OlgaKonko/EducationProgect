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

import static data.RandomGenerator.randomString;

@Feature("User")
@LogAppender(Appenders.User)
public class UserTests extends BaseTest {
    private User user;
    private UserBO userBO;

    @BeforeMethod
    public void createNewUser() {
        this.user = UserGenerator.testUser();
        this.userBO = new UserBO(user);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Story("Operations with user")
    @Test(testName = "create and delete user", description = "Create user and delete him")
    public void simpleUser() {
        userBO.createUser();
        userBO.deleteUser();

    }

    @Severity(SeverityLevel.BLOCKER)
    @Story("Operations with user")
    @Test(testName = "login and logout", description = "Login and logout to created user")
    public void login() {
        userBO.createUser();
        userBO.logIn();
        userBO.logOut();
        userBO.deleteUser();
    }

    @Story("Operations with user")
    @Test(testName = "update user", description = "Update user name and phone")
    public void updateUser() {
        userBO.createUser();
        userBO.logIn();
        user.setPhone(randomString());
        user.setLastName(randomString());
        userBO.updateUser(user);
        userBO.logOut();
        userBO.deleteUser();
    }

}

package simpleTests;

import business.UserBO;
import constants.Appenders;
import data.UserGenerator;
import model.user.User;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static data.RandomGenerator.randomString;

@Features("User")
@Title("Test with user")
@Description("Test user api operations")
public class UserTests extends BaseTest {
    private static final Logger log = Logger.getLogger(Appenders.User.getDefaultName());
    private User user;
    private UserBO userBO;

    @BeforeMethod
    public void createNewUser() {
        this.user = UserGenerator.testUser();
        this.userBO = new UserBO(user);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Stories("Operations with user")
    @Title("User create and delete")
    @Test(description = "Create user and delete him")
    public void simpleUser() {
        log.info("start simple user test");
        userBO.createUser();
        userBO.logIn();
        userBO.deleteUser();
        attachTextLog();

    }

    @Severity(SeverityLevel.BLOCKER)
    @Stories("Operations with user")
    @Title("Login and logout")
    @Test(description = "Login and logout to created user")
    public void login() {
        log.info("start login and logout test");
        userBO.createUser();
        userBO.logIn();
        userBO.logOut();
        userBO.logIn();
        userBO.deleteUser();
        attachTextLog();
    }

    @Stories("Operations with user")
    @Title("Update user")
    @Test(description = "Update user name and phone")
    public void updateUser() {
        log.info("start update user test");
        userBO.createUser();
        userBO.logIn();
        user.setPhone(randomString());
        user.setLastName(randomString());
        userBO.updateUser(user);
        userBO.logOut();
        userBO.logIn();
        userBO.deleteUser();
        attachTextLog();
    }

}

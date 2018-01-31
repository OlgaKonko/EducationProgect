package simpleTests;

import business.UsersBO;
import constants.Appenders;
import data.UserGenerator;
import model.user.User;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.List;

@Features("User")
@Title("Test with multiply users")
@Description("Test user api operations")
public class UsersTest extends BaseTest {
    private static final Logger log = Logger.getLogger(Appenders.User.getDefaultName());
    private List<User> users;

    @BeforeMethod
    public void createUsers() {
        users = new ArrayList<>();
        users.add(UserGenerator.testUser());
        users.add(UserGenerator.testUser());
        users.add(UserGenerator.testUser());
    }

    @Stories("Operations with users")
    @Title("Users from array")
    @Test(description = "Create and delete users from array")
    public void createNewUsersWithArray() {
        //  log.info("start create user with array test");
        UsersBO usersBO = new UsersBO(users);
        usersBO.createUsersWithArray();
        usersBO.deleteUsers();
    }

    @Stories("Operations with users")
    @Title("Users from list")
    @Test(description = "Create and delete users from list")
    public void createNewUsersWithList() {
        //  log.info("start create user with list test");
        UsersBO usersBO = new UsersBO(users);
        usersBO.createUsersWithList();
        usersBO.deleteUsers();
    }
}

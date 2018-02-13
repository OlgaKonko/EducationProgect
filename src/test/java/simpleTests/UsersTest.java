package simpleTests;

import business.UsersBO;
import data.UserGenerator;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import logger.LogAppender;
import model.user.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static constants.Appenders.User;

@LogAppender(User)
@Feature("User")
public class UsersTest extends BaseTest {
    private List<User> users;

    @BeforeMethod
    public void createUsers() {
        users = new ArrayList<>();
        users.add(UserGenerator.testUser());
        users.add(UserGenerator.testUser());
        users.add(UserGenerator.testUser());
    }

    @Story("Operations with users")
    @Test(testName = "create user with array", description = "Create and delete users from array")
    public void createNewUsersWithArray() {
        UsersBO usersBO = new UsersBO(users);
        usersBO.createUsersWithArray();
        usersBO.deleteUsers();
    }

    @Story("Operations with users")
    @Test(testName = "create user with list", description = "Create and delete users from list")
    public void createNewUsersWithList() {
        UsersBO usersBO = new UsersBO(users);
        usersBO.createUsersWithList();
        usersBO.deleteUsers();
    }
}

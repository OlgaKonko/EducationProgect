package business;

import client.UserClient;
import com.jayway.restassured.response.Response;
import exeptions.UserException;
import model.user.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static assertions.AssertDetails.assertUserData;
import static assertions.AssertStatusCode.assertStatusCodeIsOk;
import static constants.Appenders.User;

public class UsersBO {
    private static final Logger log = Logger.getLogger(User.getDefaultName());
    private UserClient userClient;
    private UserBO userBO;
    private List<User> users;

    public UsersBO(List<User> users) {
        log.debug("set user client");
        this.userClient = new UserClient();
        log.debug("create user BO");
        this.userBO = new UserBO();
        log.debug("set " + users.size() + " users");
        this.users = users;
    }

    public List<User> createUsersWithArray() {
        try {
            log.info("start creating " + users.size() + " users");
            users.forEach(user -> {
                userBO.setUser(user);
                if (userBO.isUserExist()) {
                    userBO.deleteUser();
                }
            });
            Response createUsersResponse = userClient.createUsersWithArray(users);
            assertStatusCodeIsOk(createUsersResponse);
            final List<User> createdUsers = new ArrayList<>();
            users.forEach(user -> {
                userBO.setUser(user);
                createdUsers.add(userBO.getUser());
                assertUserData(user, createdUsers.get(createdUsers.size() - 1));
            });
            log.info(users.size() + " users was created");
            return createdUsers;
        } catch (Throwable t) {
            log.error("can't create" + users.size() + " users");
            throw new UserException("add users from array", users, t);
        }

    }

    public List<User> createUsersWithList() {
        try {
            users.forEach(user -> {
                userBO.setUser(user);
                if (userBO.isUserExist()) {
                    userBO.deleteUser();
                }
            });
            Response createUsersResponse = userClient.createUsersWithList(users);
            assertStatusCodeIsOk(createUsersResponse);
            final List<User> createdUsers = new ArrayList<>();
            users.forEach(user -> {
                userBO.setUser(user);
                createdUsers.add(userBO.getUser());
                assertUserData(user, createdUsers.get(createdUsers.size() - 1));
            });
            return createdUsers;
        } catch (Throwable t) {
            throw new UserException("add users from list", users, t);
        }

    }

    public void deleteUsers() {
        try {
            log.info("start deleting " + users.size() + " users");
            users.forEach(user -> {
                userBO.setUser(user);
                userBO.deleteUser();
            });
            log.info(users.size() + " users was be deleted");
        } catch (Throwable t) {
            log.error("can't delete " + users.size() + " users");
            throw new UserException("delete users", users, t);
        }
    }
}

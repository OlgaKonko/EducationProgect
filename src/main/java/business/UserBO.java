package business;


import client.UserClient;
import com.jayway.restassured.response.Response;
import constants.ResponseCode;
import exeptions.UserException;
import io.qameta.allure.Step;
import model.user.User;
import org.apache.log4j.Logger;

import static assertions.AssertDetails.assertUserData;
import static assertions.AssertStatusCode.assertStatusCodeIsOk;
import static constants.Appenders.User;

public class UserBO {
    private static final Logger log = Logger.getLogger(User.getDefaultName());
    private UserClient userClient;
    private User user;

    public UserBO() {
        log.debug("create user BO and set client");
        this.userClient = new UserClient();
    }

    public UserBO(User user) {
        log.debug("create user BO and set client");
        this.userClient = new UserClient();
        log.debug("set user, username : " + user.getUsername());
        this.user = user;
    }

    @Step("Create user")
    public User createUser() {
        try {
            log.info("start creating user, username : " + user.getUsername());
            if (isUserExist()) {
                updateUser(user);
            } else {
                Response createUserResponse = userClient.createUser(user);
                assertStatusCodeIsOk(createUserResponse);
            }

            User createdUser = getUser();
            assertUserData(user, createdUser);
            log.info("user has been created, username : " + user.getUsername());
            return createdUser;
        } catch (Throwable t) {
            log.error("user can't be created, username : " + user.getUsername());
            throw new UserException("add user", user, t);
        }


    }

    @Step("Get user")
    public User getUser() {
        try {
            log.info("start getting user, username : " + user.getUsername());
            Response getUserResponse = userClient.getUser(user.getUsername());
            assertStatusCodeIsOk(getUserResponse);
            log.info("user " + user.getUsername() + " has been gotten");
            return getUserResponse.as(User.class);
        } catch (Throwable t) {
            log.error("user can't be got, username : " + user.getUsername());
            throw new UserException("get user", user, t);
        }

    }

    public void setUser(User user) {
        this.user = user;
    }

    @Step("Check if user exist")
    public boolean isUserExist() {
        try {
            log.info("start check user exist, username : " + user.getUsername());
            Response getUserResponse = userClient.getUser(user.getUsername());
            if (getUserResponse.statusCode() == ResponseCode.SUCCESS_CODE.getCode())
                log.info("user " + user.getUsername() + " is exist");
            else
                log.info("user " + user.getUsername() + " is not exist");
            return (getUserResponse.statusCode() == ResponseCode.SUCCESS_CODE.getCode());
        } catch (Throwable t) {
            log.error("user can't be checked username : " + user.getUsername());
            throw new UserException("check user", user, t);
        }
    }

    @Step("Login")
    public void logIn() {
        try {
            log.info("start login in user, username : " + user.getUsername() + " password : " + user.getPassword());
            Response response = userClient.logIn(user.getUsername(), user.getPassword());
            assertStatusCodeIsOk(response);
            log.info("user has been logged in, username : " + user.getUsername());
        } catch (Throwable t) {
            log.error("user can't be login in, username : " + user.getUsername() + " password : " + user.getPassword());
            throw new UserException("log in", user.getId(), user.getUsername(), user.getPassword(), t);
        }

    }

    @Step("Logout")
    public void logOut() {
        try {
            log.info("start user logout, username : " + user.getUsername());
            Response response = userClient.logOut();
            assertStatusCodeIsOk(response);
            log.info("user has been logout, username : " + user.getUsername());
        } catch (Throwable t) {
            log.error("user can't logout");
            throw new UserException("log out", user, t);
        }

    }

    @Step("Update user")
    public User updateUser(User changedUser) {
        try {
            log.info("start update user, username : " + user.getUsername());
            user = changedUser;
            Response updateUserResponse = userClient.updateUser(user);
            assertStatusCodeIsOk(updateUserResponse);
            User updatedUser = getUser();
            assertUserData(user, updatedUser);
            log.info("user has been updated, username : " + user.getUsername());
            return updatedUser;
        } catch (Throwable t) {
            log.error("user can't be updated, username : " + user.getUsername());
            throw new UserException("update user", user, t);
        }

    }

    @Step("Delete user")
    public void deleteUser() {
        try {
            log.info("start delete user, username : " + user.getUsername());
            logIn();
            Response deleteUserResponse = userClient.deleteUser(user.getUsername());
            assertStatusCodeIsOk(deleteUserResponse);
            assert !isUserExist() : "Error - user has not been deleted";
            log.info("user has been deleted, username : " + user.getUsername());
        } catch (Throwable t) {
            log.error("user can't be deleted, username : " + user.getUsername());
            throw new UserException("delete user", user, t);
        }

    }

}

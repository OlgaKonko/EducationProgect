package client;

import com.jayway.restassured.response.Response;
import model.user.User;
import org.apache.log4j.Logger;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static constants.Appenders.User;
import static constants.EndpointConstants.*;
import static logger.LoggerCatcher.catchLog;

public class UserClient extends HttpClient {
    private static final Logger log = Logger.getLogger(User.getDefaultName());

    public UserClient() {
        super(USER_URL);
        log.info("connecting to " + BASE_URL + USER_URL);
        catchLog(log, defaultRequest);
    }

    public Response createUser(User user) {
        log.info("send request to create user");
        return given(defaultRequest)
                .body(user).post();
    }

    public Response createUsersWithArray(List<User> users) {
        log.info("send request to create user from array");
        return given(defaultRequest).
                body(users).
                post(CREATE_USERS_BY_ARRAY_URL);
    }

    public Response createUsersWithList(List<User> users) {
        log.info("send request to create user from list");
        return given(defaultRequest).
                body(users).
                post(CREATE_USERS_BY_LIST_URL);
    }

    public Response logIn(String username, String password) {
        log.info("send request to log in");
        return given(defaultRequest).
                parameters("username", username, "password", password).
                get(USER_LOGIN_URL);
    }

    public Response logOut() {
        log.info("send request to log out");
        return given(defaultRequest).
                get(USER_LOGOUT_URL);
    }

    public Response getUser(String username) {
        log.info("send request to get user");
        return given(defaultRequest).
                get(ID, username);
    }

    public Response updateUser(User updatedUser) {
        log.info("send request to update user");
        return given(defaultRequest).
                body(updatedUser).
                put(ID, updatedUser.getUsername());
    }

    public Response deleteUser(String username) {
        log.info("send request to delete user");
        return given(defaultRequest).
                delete(ID, username);
    }

}

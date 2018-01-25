package exeptions;

import model.user.User;

import java.util.List;

public class UserException extends BaseException {

    public UserException(String message, User user, Throwable cause) {
        super(message, "user id : " + user.getId() + ", user name : " + user.getUsername(), cause);
        //super(message, user.getUsername(), cause);
    }

    public UserException(String message, long userId, String username, String password, Throwable cause) {
        super(message, "user id : " + userId + ", user name : " + username + ", password : " + password, cause);
        //super(message, username, cause);
    }

    public UserException(String message, List<User> users, Throwable cause) {
        super(message, "users quantity : " + users.size(), cause);
        //super(message, username, cause);
    }


}

package model.bulder;

import model.user.User;

public class UserBuilder {
    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder id(long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder username(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder firstName(String firstName) {
        user.setFirstName(firstName);
        return this;

    }

    public UserBuilder lastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    public UserBuilder email(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder phone(String phone) {
        user.setPhone(phone);
        return this;
    }

    public UserBuilder userStatus(long userStatus) {
        user.setUserStatus(userStatus);
        return this;
    }

    public User build() {
        return user;

    }
}

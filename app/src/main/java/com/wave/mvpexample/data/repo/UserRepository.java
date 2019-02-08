package com.wave.mvpexample.data.repo;

import com.wave.mvpexample.data.model.User;

public class UserRepository implements LoginRepository {

    private User user;

    @Override
    public User getUser() {

        if (user == null) {
            User user = new User("Dinesh", "Kumar");
            user.setId(0);
            return user;
        } else {
            return user;
        }

    }

    @Override
    public void saveUser(User user) {

        if (user == null) {
            user = getUser();
        }

        this.user = user;

    }
}

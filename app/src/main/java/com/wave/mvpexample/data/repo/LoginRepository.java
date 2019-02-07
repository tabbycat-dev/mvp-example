package com.wave.mvpexample.data.repo;

import com.wave.mvpexample.data.model.User;

public interface LoginRepository {

    User getUser();

    void saveUser(User user);
}

package com.wave.mvpexample.model.repository;

import com.wave.mvpexample.utils.User;

public interface LoginRepository {

    User getUser();

    void saveUser(User user);

    void createUserFireBase(User user);
    void loginUser(User user);
    User getFireBaseUser();
    boolean isLogin();
    void signoutUser();

}

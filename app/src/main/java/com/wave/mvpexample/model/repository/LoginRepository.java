package com.wave.mvpexample.model.repository;

import com.wave.mvpexample.utils.User;

public interface LoginRepository {

    String loginUser(String email, String password);
    User getCurrentUser();


    void saveUser(User user);
    void createUserFireBase(User user);
    User getFireBaseUser();
    boolean isLogin();
    void signoutUser();

}

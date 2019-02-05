package com.wave.mvpexample.login;

public interface LoginRepository {

    User getUser();

    void saveUser(User user);
}

package com.wave.mvpexample.data.model;

import com.wave.mvpexample.data.repo.LoginRepository;
import com.wave.mvpexample.login.LoginActivityMVP;

public class LoginModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String name, String lastName) {

        //repository.saveUser(new User(name, lastName));
        repository.createUserFireBase(new User(name, lastName));
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }

    @Override
    public User getFireBaseUser() {
        return repository.getFireBaseUser();
    }

    @Override
    public void loginUser(String name, String lastname) {

        repository.loginUser(new User(name, lastname));
    }

    public boolean checkLogin(){
        return repository.isLogin();
    }


    @Override
    public void signoutUser() {
        repository.signoutUser();
    }

}

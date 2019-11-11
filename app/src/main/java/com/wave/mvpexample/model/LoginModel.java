package com.wave.mvpexample.model;

import com.wave.mvpexample.utils.User;
import com.wave.mvpexample.model.repository.LoginRepository;
import com.wave.mvpexample.login.LoginActivityMVP;

public class LoginModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }


    @Override
    public String loginUser(String email, String password) {

        return repository.loginUser(email, password);
    }

    @Override
    public User getCurrentUser() {
        return repository.getCurrentUser();
    }

    @Override
    public void createUser(String name, String lastName) {

        //repository.saveUser(new User(name, lastName));
        repository.createUserFireBase(new User(name));
    }

    @Override
    public User getFireBaseUser() {
        return repository.getFireBaseUser();
    }


    public boolean checkLogin(){
        return repository.isLogin();
    }


    @Override
    public void signoutUser() {
        repository.signoutUser();
    }

}

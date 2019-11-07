package com.wave.mvpexample.login;

import com.wave.mvpexample.utils.User;

/*
* contains all use case of the app
* */
public interface LoginActivityMVP {

    interface View{

        String getEmail();

        String getPassword();

        void showInputError();

        void setEmail(String email);

        void setPassword(String password);

        void showUserSavedMessage();

        void showUserNotAvailable();

        void showUserSignout();

    }
/*
* Use case of sign up / sign in / sign out
* */
    interface Presenter {

        void setView(View view);

        void loginButtonClicked();

        void getCurrentUser();

        void signoutButtonClicked();

    }

    interface Model {

        void createUser(String name, String lastName);

        User getUser();

        User getFireBaseUser();

        void loginUser(String name, String lastname);

        boolean checkLogin();

        void signoutUser();



    }
}

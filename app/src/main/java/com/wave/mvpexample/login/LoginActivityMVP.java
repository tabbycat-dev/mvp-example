package com.wave.mvpexample.login;

import com.wave.mvpexample.utils.User;

/*
* contains all use case of the app
* */
public interface LoginActivityMVP {

    interface View{

        String getEmail();

        String getPassword();

        void showInputEmailError();

        void showInputPasswordError();

        void showUserLoginSuccess();

        void showUserLoginFailure();

        void startProgressDialog();

        void endProgressDialog();

        void showUserLoginError(String error);

        void goToStudentActivity();



        void setEmail(String email);

        void setPassword(String password);

        void showUserSignout();

    }
/*
* Use case of sign up / sign in / sign out
* */
    interface Presenter {

        void setView(View view);

        void loginButtonClicked();


    }

    interface Model {

        String loginUser(String name, String lastname);

        User getCurrentUser();





        void createUser(String name, String lastName);


        User getFireBaseUser();


        boolean checkLogin();

        void signoutUser();



    }
}

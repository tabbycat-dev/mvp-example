package com.wave.mvpexample.login;

import android.support.annotation.Nullable;
import android.util.Log;

import com.wave.mvpexample.data.model.User;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {

        this.view = view;

    }

    @Override
    public void loginButtonClicked() {

        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {

                //model.createUser(view.getFirstName(), view.getLastName());
                //model.createUser(view.getFirstName(), view.getLastName());
                //view.showUserSavedMessage();
                model.loginUser(view.getFirstName(), view.getLastName());
                //view.showUserSavedMessage();
                boolean isLogin = model.checkLogin();

                if (isLogin) {
                    if (view != null) {
                        view.showUserSavedMessage();
                        Log.i("USER", "loginButtonClicked true");
                    }
                }else{
                    if (view !=null){
                        view.showUserNotAvailable();
                        Log.i("USER", "loginButtonClicked false");
                        //view.setFirstName(user.getFirstName());
                        //view.setLastName(user.getLastName());

                    }
                }

            }
        }
    }

    @Override
    public void getCurrentUser() {

        User user = model.getUser();

        if (user != null) {
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }

    @Override
    public void signoutButtonClicked() {
        model.signoutUser();
        if (view !=null){
            view.showUserSignout();
        }
    }

}

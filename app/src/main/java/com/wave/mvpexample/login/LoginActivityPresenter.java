package com.wave.mvpexample.login;

import androidx.annotation.Nullable;

import android.os.AsyncTask;
import android.util.Log;

import com.wave.mvpexample.utils.User;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {
    private final String DONE = "DONE";

    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    private final String SUCCESS = "success";
    private final String FAILURE = "failure";
    private final String ERROR_1 = "signInWithEmail:invalid password";
    private final String ERROR_2 = "signInWithEmail:No account with this email";

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
            //boolean valid = true;
            if (view.getEmail().trim().equals("")|| !android.util.Patterns.EMAIL_ADDRESS.matcher(view.getEmail().trim()).matches()) {
                view.showInputEmailError();
            }
            else if (view.getPassword().trim().equals("")) {
                view.showInputPasswordError();
            } else {
                view.startProgressDialog();
                String email = view.getEmail();
                String password = view.getPassword();
                String result = model.loginUser(email, password);
                Log.i("PRESENTER", "0-result: "+result);
                if (result != null) {
                    view.endProgressDialog();
                    if (result.equals(SUCCESS)) {
                        view.showUserLoginSuccess();
                        Log.i("PRESENTER", "1-loginButtonClicked true");
                        view.goToStudentActivity();
                    } else if (result.equals(FAILURE)) {
                        view.showUserLoginFailure();
                        Log.i("PRESENTER", "2-loginButtonClicked false");
                    } else {
                        //view.showUserLoginError("LOGIN UNKNOWN");
                        view.showUserLoginFailure();
                        Log.i("PRESENTER", "3-loginButtonClicked UNKNOWN");
                    }
                }
            }
        }
    }
}

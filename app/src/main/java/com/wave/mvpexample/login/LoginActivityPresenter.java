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

            if (view.getEmail().trim().equals("") || view.getPassword().trim().equals("")) {
                view.showInputEmailError();

            } else {
                view.startProgressDialog();
                String email = view.getEmail();
                String password = view.getPassword();

                MyTask myTask = new MyTask();
                myTask.execute(email, password);


            }
        }
    }

    public class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String result = model.loginUser(params[0], params[1]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            view.endProgressDialog();
            if (result != null) {
                if (result.equals(SUCCESS)){
                    if (view != null) {
                        view.showUserLoginSuccess();
                        Log.i("LOGIN", "loginButtonClicked true");
                        view.goToStudentActivity();
                }

                }
            } else if(result.equals(FAILURE)) {
                if (view != null) {
                    view.showUserLoginFailure();
                    Log.i("LOGIN", "loginButtonClicked false");

                }
            }else {
                if (view != null) {
                    view.showUserLoginError(result);
                    Log.i("LOGIN", "loginButtonClicked false");

                }
            }
        }
    }


}

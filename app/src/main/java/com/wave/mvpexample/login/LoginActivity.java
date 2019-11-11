package com.wave.mvpexample.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.wave.mvpexample.R;
import com.wave.mvpexample.root.App;
import com.wave.mvpexample.studentActivity.CheckClassActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    @Inject
    LoginActivityMVP.Presenter presenter;

    @BindView(R.id.et_email)
    EditText email_et;

    @BindView(R.id.et_password)
    EditText password_et;

    FirebaseAuth mAuth ;
    private final String TAG = "LOGIN";

    @BindView(R.id.spinnerRole)
    Spinner spinnerRole;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    private Button login, signout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signoutButton) void signOutClicked(){
       // presenter.signoutButtonClicked();
    }

    @OnClick(R.id.btnLogin) void loginClicked(){
        presenter.loginButtonClicked();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        //presenter.getCurrentUser();
    }

    @Override
    public String getEmail() {
        return email_et.getText().toString();
    }

    @Override
    public String getPassword() {
        return password_et.getText().toString();
    }

    @Override
    public void showInputEmailError() {
        email_et.setError("Please enter valid email!");
    }

    @Override
    public void showInputPasswordError() {
        password_et.setError("Please enter valid password!");
    }

    @Override
    public void showUserLoginSuccess() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserLoginFailure() {
        Toast.makeText(this, "Login failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgressDialog() {
        btnLogin.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE); // To show the ProgressBar
    }
    @Override
    public void endProgressDialog() {
        btnLogin.setEnabled(true);
        progressBar.setVisibility(View.INVISIBLE); // To hide the ProgressBar
    }

    @Override
    public void showUserLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToStudentActivity() {
        Intent i = new Intent (getApplicationContext(), CheckClassActivity.class);
        startActivity(i);
    }

    @Override
    public void setEmail(String email) {
        this.email_et.setText(email);
    }

    @Override
    public void setPassword(String password) {
        this.password_et.setText(password);
    }


    @Override
    public void showUserSignout() {
        Toast.makeText(this, "User signed out", Toast.LENGTH_SHORT).show();

    }



}

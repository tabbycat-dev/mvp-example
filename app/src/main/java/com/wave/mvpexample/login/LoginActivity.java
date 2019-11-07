package com.wave.mvpexample.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.wave.mvpexample.R;
import com.wave.mvpexample.root.App;

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

    private Button login, signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signoutButton) void signOutClicked(){
        presenter.signoutButtonClicked();
    }

    @OnClick(R.id.btnLogin) void loginClicked(){
        presenter.loginButtonClicked();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
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
    public void showInputError() {
        Toast.makeText(this, "First Name or last name cannot be empty", Toast.LENGTH_SHORT).show();
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
    public void showUserSavedMessage() {
        Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "User is not available", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showUserSignout() {
        Toast.makeText(this, "User signed out", Toast.LENGTH_SHORT).show();

    }
}

package com.wave.mvpexample.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wave.mvpexample.R;
import com.wave.mvpexample.root.App;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    @Inject
    LoginActivityMVP.Presenter presenter;


    private EditText firstName;
    private EditText lastName;
    private Button login, signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);

        firstName = (EditText) findViewById(R.id.loginActivity_firstName_editText);
        lastName = (EditText) findViewById(R.id.loginActivity_lastName_editText);
        login = (Button) findViewById(R.id.loginActivity_login_button);
        signout = (Button) findViewById(R.id.loginActivity_btn_signout);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginButtonClicked();
            }
        });
        signout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                presenter.signoutButtonClicked();

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return firstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastName.getText().toString();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "First Name or last name cannot be empty", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
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

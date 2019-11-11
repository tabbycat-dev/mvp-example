package com.wave.mvpexample.model.repository;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.wave.mvpexample.utils.User;

public class UserRepository implements LoginRepository {

    private User user = null;
    private final FirebaseAuth mAuth =FirebaseAuth.getInstance();
    private final String SUCCESS = "success";
    private final String FAILURE = "failure";
    private final String ERROR_1 = "signInWithEmail:invalid password";
    private final String ERROR_2 = "signInWithEmail:No account with this email";
    private String result = "";

    @Override
    public User getCurrentUser() {
        FirebaseUser userFirebase = mAuth.getCurrentUser();
        if (userFirebase != null) {
            String email = userFirebase.getEmail();
            String name = userFirebase.getDisplayName();
            user = new User(email);
            user.setName(name);
        }
        return user;
    }

    @Override
    public String loginUser(String email, String password ) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("LOGIN", "login Success " + task.getResult().getUser().getEmail());
                            user = new User(email);
                            result = SUCCESS;
                        } else {
                            Log.i("LOGIN", "login Fail " + task.getException().getMessage());
                            result = FAILURE;
                        }
                    }
                })
                //login error handling
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            Log.i("LOGIN", "signInWithEmail:invalid password");
                            result = ERROR_1;
                        } else if (e instanceof FirebaseAuthInvalidUserException) {
                            Log.i("LOGIN", "signInWithEmail:No account with this email");
                            result = ERROR_2;
                        } else {
                            Log.i("LOGIN", e.getLocalizedMessage());
                            result = e.getLocalizedMessage();

                        }
                    }
                })
        ;
        return result;
    }

    @Override
    public void saveUser(User user) {

        if (user == null) {
            user = getCurrentUser();
        }

        this.user = user;

    }
    @Override
    public void createUserFireBase(User user) {
        //if (user == null) {
        //    user = getUser();
        //}
        String email = user.getEmail();
        String password = user.getPassword();
        Log.i("USER","CLICKED FIREBASE" );
        //User u ;
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Log.i("USER","Fail "+task.getException().getMessage() );
                            //mOnRegistrationListener.onFailure(task.getException().getMessage());
                        }else{
                            Log.i("USER","Success "+task.getResult().getUser().getEmail() );
                            //mOnRegistrationListener.onSuccess(task.getResult().getUser());
                        }
                    }
                });
        this.user = user;
    }
    @Override
    public User getFireBaseUser() {
        if(mAuth.getCurrentUser() !=null){
            String email = mAuth.getCurrentUser().getEmail();
            Log.i("USER", "getFBUser " + email);
            return new User(email);
        }else{
            Log.i("USER", "getFBUser  NULL");
            return null;
        }
    }

    @Override
    public boolean isLogin() {
        if(mAuth.getCurrentUser() !=null){
            String email = mAuth.getCurrentUser().getEmail();
            Log.i("USER", "isLogin " + email);
            return true;
        }else{
            Log.i("USER", "isLogin " + false);
            return false;
        }
    }

    @Override
    public void signoutUser() {
        mAuth.signOut();
        Log.i("USER", "signout ");


    }


}

package com.wave.mvpexample.model.repository;

import androidx.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.wave.mvpexample.utils.User;

public class UserRepository implements LoginRepository {

    private User user;
    private final FirebaseAuth mAuth =FirebaseAuth.getInstance();


    @Override
    public User getUser() {
        if (user == null) {
            User user = new User("tan@gmail.com", "tan123");
            user.setId(0);
            return user;
        } else {
            return user;
        }
    }
    @Override
    public void saveUser(User user) {

        if (user == null) {
            user = getUser();
        }

        this.user = user;

    }

    @Override
    public void createUserFireBase(User user) {
        //if (user == null) {
        //    user = getUser();
        //}
        String email = user.getFirstName();
        String password = user.getLastName();
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
    public void loginUser(User user) {
        String email = user.getFirstName();
        String password = user.getLastName();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("USER", "login Success " + task.getResult().getUser().getEmail());
                        } else {
                            Log.i("USER", "login Fail " + task.getException().getMessage());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthUserCollisionException) {
                            Log.i("USER", "login Fail " + "This email address is already in use.");
                        }
                        else {
                            Log.i("USER", "login Fail " + e.getLocalizedMessage());
                        }
                    }
                })
        ;
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

package com.wave.mvpexample.data.repo;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wave.mvpexample.data.model.User;

public class UserRepository implements LoginRepository {

    private User user;
    private final FirebaseAuth mAuth =FirebaseAuth.getInstance();


    @Override
    public User getUser() {
        if (user == null) {
            User user = new User("Dinesh", "Kumar");
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
        if (user == null) {
            user = getUser();
        }
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
                });
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

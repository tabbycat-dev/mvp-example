package com.wave.mvpexample.login;

import com.wave.mvpexample.utils.User;
import com.wave.mvpexample.login.LoginActivityMVP;
import com.wave.mvpexample.login.LoginActivityPresenter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class LoginActivityPresenterTest {
    LoginActivityPresenter presenter;
    User user;

    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockView;

    @Before
    public void setup(){
        mockLoginModel = mock(LoginActivityMVP.Model.class);
        mockView =mock(LoginActivityMVP.View.class);
        presenter = new LoginActivityPresenter(mockLoginModel);
        presenter.setView(mockView);
        user = new User("tan@gmail.com", "tan123");
    }

    @Test
    public void createErrorMsgIfFieldsAreEmpty(){

        when(mockView.getEmail()).thenReturn("");               /* email is empty*/
        //when(mockView.getPassword()).thenReturn("");

        presenter.loginButtonClicked();

        //verify model interactions
        verifyZeroInteractions(mockLoginModel);

        //verify view interactions
        verify(mockView, times(1)).getEmail();//once
        verify(mockView, never()).getPassword();
        //verify(mockView, times(1)).getPassword();//once

        //verify(mockView, never()).getPassword();
        verify(mockView, times(1)).showInputEmailError();//onece
        //verify(mockView, times(1)).showInputPasswordError();//onece




    }
    @Test
    public void createErrorMsgIfFieldsAreEmpty2(){
        when(mockView.getEmail()).thenReturn("Tan");  /* email is valid*/
        when(mockView.getPassword()).thenReturn("");   /* password is empty*/

        //when(mockView.getPassword()).thenReturn("");

        presenter.loginButtonClicked();


    }
}
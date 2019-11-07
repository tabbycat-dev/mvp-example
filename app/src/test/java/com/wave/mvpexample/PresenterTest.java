package com.wave.mvpexample;

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

public class PresenterTest {
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
    public void noInteractionWithView(){
        //when(mockLoginModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();
        verifyZeroInteractions(mockView);
    }
    @Test
    public void loadTheUserFromTheRepoWhenValidUserIsPresent(){
        when(mockLoginModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();
        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, times(1)).setFirstName("tan@gmail.com");
        verify(mockView, times(1)).setLastName("tan123");
        verify(mockView, never()).showUserNotAvailable();
    }
    @Test
    public void showErrorMsgWhenUserIsNull(){
        when(mockLoginModel.getUser()).thenReturn(null);
        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, never()).setFirstName("tan@gmail.com");
        verify(mockView, never()).setLastName("tan123");
        verify(mockView, times(1)).showUserNotAvailable();
    }
    @Test
    public void createErrorMsgIfFieldsAreEmpty(){
        when(mockView.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();

        //verify model interactions
        verifyZeroInteractions(mockLoginModel);

        //verify view interactions
        verify(mockView, times(1)).getFirstName();//once
        verify(mockView, never()).getLastName();
        verify(mockView, times(1)).showInputError();//onece

        //now tell mockView to return a value for first name and an empty last name
        when(mockView.getFirstName()).thenReturn("Tan");
        when(mockView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        //verify model interactions
        verifyZeroInteractions(mockLoginModel);

        verify(mockView, times(2)).getFirstName();//twice
        verify(mockView, times(1)).getLastName();//once
        verify(mockView, times(2)).showInputError();//twice
    }

}


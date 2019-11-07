package com.wave.mvpexample.login;

import com.wave.mvpexample.model.LoginModel;
import com.wave.mvpexample.model.repository.LoginRepository;
import com.wave.mvpexample.model.repository.UserRepository;

import dagger.Module;
import dagger.Provides;


@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model){
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository){
        return new LoginModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository()
    {
        return new UserRepository();
    }
}

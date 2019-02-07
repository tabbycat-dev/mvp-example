package com.wave.mvpexample.root;



import com.wave.mvpexample.login.LoginActivity;
import com.wave.mvpexample.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, LoginModule.class})
public interface AppComponent {

    void inject(LoginActivity target);

}

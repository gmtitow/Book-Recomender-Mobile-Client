package c.tgm.booksapplication.authentication;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Stack;

import c.tgm.booksapplication.AbstractPresenter;
import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.DataStore;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.Screen;

@InjectViewState
public class AuthenticationPresenter extends NavigatorPresenter<AuthenticationView> {
    
    AuthenticationModel mModel;
    
    public AuthenticationPresenter() {
        mModel = new AuthenticationModel();
        
        replaceScreen(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.AUTHORIZATION_SCREEN));
    }
    
    public void goToRegisterLoginScreen(String login) {
        mModel.setRegisterLogin(login);
        navigateTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.REGISTRATION_LOGIN_SCREEN, mModel.getRegisterLogin()));
    }
    
    public void goToRegisterPasswordScreen(String activationCode) {
        mModel.setActivationCode(activationCode);
        navigateTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.REGISTRATION_PASSWORD_SCREEN, mModel.getRegisterLogin(),mModel.getActivationCode()));
    }
    
    public void goToRegisterConfirmCodeScreen(String login) {
        mModel.setRegisterLogin(login);
        navigateTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.REGISTRATION_CONFIRM_CODE_SCREEN, mModel.getRegisterLogin()));
    }
    
    public void onRegisterScreen() {
        //backTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.AUTHORIZATION_SCREEN));
        replaceScreen(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.REGISTRATION_CONFIRM_CODE_SCREEN, mModel.getRegisterLogin()));
    }
    
    public void backToRegisterLoginScreen() {
        boolean flag = true;
        int i = mScreens.size() - 1;
        int count = 0;
        while (i >= 0 && !mScreens.get(i).getScreenKey().equals(Screens.AuthenticationScreens.REGISTRATION_LOGIN_SCREEN)) {
            i--;
            count++;
        }
        
        if (i == -1)
            flag = false;
        
        if (flag) {
            //while(count>0){
                /*exit();
                count--;*/
            backTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.REGISTRATION_LOGIN_SCREEN, BookApplication.INSTANCE.getDataStore().getLogin()));
            //}
        } else {
            replaceScreen(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.REGISTRATION_LOGIN_SCREEN, BookApplication.INSTANCE.getDataStore().getLogin()));
        }
    }
    
    public void goToChangePassword(String login) {
        mModel.setRegisterLogin(login);
        navigateTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.RESET_PASSWORD_LOGIN_SCREEN, mModel.getRegisterLogin()));
    }
    
    public void backToChangePasswordCodeScreen(String login) {
        mModel.setRegisterLogin(login);
        navigateTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.RESET_PASSWORD_CODE_SCREEN, mModel.getRegisterLogin()));
    }
    
    public void goToChangePasswordPassword(String resetCode) {
        navigateTo(new Screens.AuthenticationScreens(
                Screens.AuthenticationScreens.RESET_PASSWORD_PASSWORD_SCREEN,
                new String[]{mModel.getRegisterLogin(),resetCode}));
    }
}

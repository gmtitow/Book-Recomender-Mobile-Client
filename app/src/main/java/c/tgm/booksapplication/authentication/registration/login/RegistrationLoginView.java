package c.tgm.booksapplication.authentication.registration.login;

import com.arellomobile.mvp.MvpView;

public interface RegistrationLoginView extends MvpView {
    void changeRegisterButtonEnabled(boolean enabled);
    
    void setError(String error);
    
    void setLoginError(String error);
    
    void clearErrors();
    
    void showProgress();
    
    void hideProgress();
}

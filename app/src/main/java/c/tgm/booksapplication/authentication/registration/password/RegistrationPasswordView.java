package c.tgm.booksapplication.authentication.registration.password;

import com.arellomobile.mvp.MvpView;

public interface RegistrationPasswordView extends MvpView {
    void changeRegisterButtonEnabled(boolean enabled);
    
    void setError(String error);
    
    void setPasswordError(String error);
    
    void clearErrors();
    
    void showProgress();
    
    void hideProgress();
}

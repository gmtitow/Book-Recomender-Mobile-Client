package c.tgm.booksapplication.authentication.authorization.change_password.password;

import com.arellomobile.mvp.MvpView;

public interface ChangePasswordPasswordView extends MvpView {
    void changeRegisterButtonEnabled(boolean enabled);
    
    void setError(String error);
    
    void setPasswordError(String error);
    
    void clearErrors();
    
    void showProgress();
    
    void hideProgress();
}

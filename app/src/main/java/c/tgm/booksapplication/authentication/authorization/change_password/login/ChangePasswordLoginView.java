package c.tgm.booksapplication.authentication.authorization.change_password.login;

import com.arellomobile.mvp.MvpView;

public interface ChangePasswordLoginView extends MvpView {
    void changeRegisterButtonEnabled(boolean enabled);
    
    void setError(String error);
    
    void setLoginError(String error);
    
    void clearErrors();
    
    void showProgress();
    
    void hideProgress();
}

package c.tgm.booksapplication.authentication.authorization;

import com.arellomobile.mvp.MvpView;

public interface AuthorizationView extends MvpView {
    void changeEnterButtonEnabled(boolean enabled);
    
    void setError(String error);
    
    void setLoginError(String error);
    
    void setPasswordError(String error);
    
    void clearErrors();
    
    void showProgress();
    
    void hideProgress();
}

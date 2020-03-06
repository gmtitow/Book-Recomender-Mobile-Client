package c.tgm.booksapplication.authentication.registration.confirm;

import com.arellomobile.mvp.MvpView;

public interface RegistrationConfirmCodeView extends MvpView {
    void changeSendConfirmButtonEnabled(boolean enabled);
    
    void setError(String error);
    
    void clearErrors();
    
    void showProgress();
    
    void hideProgress();
    
    void setConfirmTimeVisible();
    
    void setTime(int seconds);
    
    void setReplyCodeVisible();
}

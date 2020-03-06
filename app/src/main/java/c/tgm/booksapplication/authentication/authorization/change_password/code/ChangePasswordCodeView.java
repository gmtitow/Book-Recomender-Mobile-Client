package c.tgm.booksapplication.authentication.authorization.change_password.code;

import com.arellomobile.mvp.MvpView;

public interface ChangePasswordCodeView extends MvpView {
    void changeSendCodeButtonEnabled(boolean enabled);
    
    void setError(String error);
    
    void clearErrors();
    
    void showProgress();
    
    void hideProgress();
    
    void setReplyCodeVisible();
    
    void setConfirmTimeVisible();
    
    void setTime(int i);
}

package c.tgm.booksapplication.authentication.authorization.change_password.login;

import c.tgm.booksapplication.any.CustomValidationUtils;

public class ChangePasswordLoginModel {
    private String mLogin = "";
    private String mCurrentLogin = "";
    
    private String mSentLogin = "";
    
    public String getLogin() {
        return mLogin;
    }
    
    public void setLogin(String login) {
        mLogin = login;
    }
    
    public String getCurrentLogin() {
        return mCurrentLogin;
    }
    
    public void setCurrentLogin(String currentLogin) {
        mCurrentLogin = currentLogin;
    }
    
    public String getSentLogin() {
        return mSentLogin;
    }
    
    public void setSentLogin(String sentLogin) {
        mSentLogin = sentLogin;
    }
    
    public boolean inputIsCorrect(){
        return CustomValidationUtils.checkLogin(getLogin());
    }
}

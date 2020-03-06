package c.tgm.booksapplication.authentication.authorization.change_password.password;

import c.tgm.booksapplication.any.CustomValidationUtils;
import c.tgm.booksapplication.models.data.AuthData;

public class ChangePasswordPasswordModel {
    private String mLogin = "";
    private String mPassword = "";
    private String mPasswordSent = "";
    private String mConfirm = "";
    
    private String mResetCode = "";
    
    private AuthData mCurrentAuthData = null;
    private boolean mAuthDataWait = false;
    
    public String getPassword() {
        return mPassword;
    }
    
    public void setPassword(String password) {
        mPassword = password;
    }
    
    public boolean inputIsCorrect(){
        if(!getPassword().equals(getConfirm()))
            return false;
        return CustomValidationUtils.checkPassword(getPassword());
    }
    
    public AuthData getCurrentAuthData() {
        return mCurrentAuthData;
    }
    
    public void setCurrentAuthData(AuthData currentAuthData) {
        mCurrentAuthData = currentAuthData;
    }
    
    public boolean isAuthDataWait() {
        return mAuthDataWait;
    }
    
    public void setAuthDataWait(boolean authDataWait) {
        mAuthDataWait = authDataWait;
    }
    
    public String getLogin() {
        return mLogin;
    }
    
    public void setLogin(String login) {
        mLogin = login;
    }
    
    public String getConfirm() {
        return mConfirm;
    }
    
    public void setConfirm(String confirm) {
        mConfirm = confirm;
    }
    
    public String getPasswordSent() {
        return mPasswordSent;
    }
    
    public void setPasswordSent(String passwordSent) {
        mPasswordSent = passwordSent;
    }
    
    public String getResetCode() {
        return mResetCode;
    }
    
    public void setResetCode(String resetCode) {
        mResetCode = resetCode;
    }
}

package c.tgm.booksapplication.authentication.registration.password;


import c.tgm.booksapplication.any.CustomValidationUtils;
import c.tgm.booksapplication.models.data.AuthData;

public class RegistrationPasswordModel {
    private String mCurrentLogin = "";
    private String mLogin = "";
    private String mPassword = "";
    private String mConfirm = "";
    private String mActivationCode = "";
    
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
    
    public String getCurrentLogin() {
        return mCurrentLogin;
    }
    
    public void setCurrentLogin(String currentLogin) {
        mCurrentLogin = currentLogin;
    }
    
    public String getActivationCode() {
        return mActivationCode;
    }
    
    public void setActivationCode(String activationCode) {
        mActivationCode = activationCode;
    }
}

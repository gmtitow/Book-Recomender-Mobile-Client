package c.tgm.booksapplication.authentication.registration.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import c.tgm.booksapplication.any.CustomValidationUtils;

public class RegistrationLoginModel {
    private String mLogin = null;
    private String mCurrentLogin = "";
    
    public String getLogin() {
        return mLogin;
    }
    
    public void setLogin(String login) {
        mLogin = login;
    }
    
    public boolean inputIsCorrect(){
        return CustomValidationUtils.checkLogin(getCurrentLogin());
    }
    
    public String getCurrentLogin() {
        return mCurrentLogin;
    }
    
    public void setCurrentLogin(String currentLogin) {
        mCurrentLogin = currentLogin;
    }
}

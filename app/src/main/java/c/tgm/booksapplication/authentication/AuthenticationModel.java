package c.tgm.booksapplication.authentication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationModel {
    private String mRegisterLogin = "";
    private String mRegisterPassword = "";
    private String mActivationCode = "";
    
    public String getRegisterLogin() {
        return mRegisterLogin;
    }
    
    public void setRegisterLogin(String registerLogin) {
        mRegisterLogin = registerLogin;
    }
    
    public String getRegisterPassword() {
        return mRegisterPassword;
    }
    
    public void setRegisterPassword(String registerPassword) {
        mRegisterPassword = registerPassword;
    }
    
    public String getActivationCode() {
        return mActivationCode;
    }
    
    public void setActivationCode(String activationCode) {
        mActivationCode = activationCode;
    }
}

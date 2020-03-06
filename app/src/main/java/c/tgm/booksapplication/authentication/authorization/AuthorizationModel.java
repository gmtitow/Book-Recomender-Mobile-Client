package c.tgm.booksapplication.authentication.authorization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import c.tgm.booksapplication.any.CustomValidationUtils;
import c.tgm.booksapplication.models.data.AuthData;

public class AuthorizationModel {
    private String mLogin = "";
    private String mPassword = "";
    
    private AuthData mCurrentAuthData = null;
    private boolean mAuthDataWait = false;
    
    public String getLogin() {
        return mLogin;
    }
    
    public void setLogin(String login) {
        mLogin = login;
    }
    
    public String getPassword() {
        return mPassword;
    }
    
    public void setPassword(String password) {
        mPassword = password;
    }
    
    public boolean inputIsCorrect(){
        if(!CustomValidationUtils.checkPassword(getPassword()))
            return false;
    
        return CustomValidationUtils.checkLogin(getLogin());
    }
    
    public boolean passwordIsCorrect(){
        if(getPassword().length()<4)
            return false;
        
        return true;
    }
    
    public boolean loginIsCorrect(){
        String phonePattern = "^[0-9]{3,25}$";
        Pattern patternPhone = Pattern.compile(phonePattern);
        Matcher matcher = patternPhone.matcher(getLogin());
    
        if(!matcher.find()){
            String emailPattern = "@";
            Pattern patternEmail = Pattern.compile(emailPattern);
            matcher = patternEmail.matcher(getLogin());
        
            if(!matcher.find())
                return false;
        }
    
        return true;
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
}

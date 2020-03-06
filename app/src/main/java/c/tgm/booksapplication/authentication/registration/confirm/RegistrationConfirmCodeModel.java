package c.tgm.booksapplication.authentication.registration.confirm;

public class RegistrationConfirmCodeModel {
    private String mConfirmCode = "";
    private String mLogin = "";
    
    public String getConfirmCode() {
        return mConfirmCode;
    }
    
    public void setConfirmCode(String confirmCode) {
        mConfirmCode = confirmCode;
    }
    
    public boolean inputIsCorrect(){
        return getConfirmCode().length()>0;
    }
    
    public String getLogin() {
        return mLogin;
    }
    
    public void setLogin(String login) {
        mLogin = login;
    }
}

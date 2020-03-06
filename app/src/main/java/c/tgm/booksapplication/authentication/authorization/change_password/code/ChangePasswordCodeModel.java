package c.tgm.booksapplication.authentication.authorization.change_password.code;

public class ChangePasswordCodeModel {
    private String mLogin = "";
    private String mResetCode = "";
    private String mResetCodeSent = "";
    
    public String getLogin() {
        return mLogin;
    }
    
    public void setLogin(String login) {
        mLogin = login;
    }
    
    public String getResetCode() {
        return mResetCode;
    }
    
    public void setResetCode(String resetCode) {
        mResetCode = resetCode;
    }
    
    public String getResetCodeSent() {
        return mResetCodeSent;
    }
    
    public void setResetCodeSent(String resetCodeSent) {
        mResetCodeSent = resetCodeSent;
    }
    
    public boolean inputIsCorrect(){
        return mResetCode.length()>0;
    }
}

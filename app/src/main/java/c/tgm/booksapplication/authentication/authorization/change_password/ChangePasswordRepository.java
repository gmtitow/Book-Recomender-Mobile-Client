package c.tgm.booksapplication.authentication.authorization.change_password;

public interface ChangePasswordRepository {
    void getResetPasswordCode(String login);
    
    void checkResetPasswordCode(String login, String resetCode);
    
    void changePassword(String login, String resetCode, String password);
}

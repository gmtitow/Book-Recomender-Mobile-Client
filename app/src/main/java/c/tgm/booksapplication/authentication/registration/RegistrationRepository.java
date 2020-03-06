package c.tgm.booksapplication.authentication.registration;

public interface RegistrationRepository {
    void register(String login, String password, String activationCode);
    
    void checkActivationCode(String activationCode, String login);
    
    void replyActivationCode(String login);
    
    void checkLogin(String login);
}

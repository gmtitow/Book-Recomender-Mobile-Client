package c.tgm.booksapplication.authentication.authorization;

public interface AuthorizationRepository {
    void login(String login, String password);
}

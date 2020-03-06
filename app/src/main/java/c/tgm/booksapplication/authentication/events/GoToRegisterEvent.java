package c.tgm.booksapplication.authentication.events;

public class GoToRegisterEvent {
    private String login;
    
    public String getLogin() {
        return login;
    }
    
    public GoToRegisterEvent(String login) {
        this.login = login;
    }
}

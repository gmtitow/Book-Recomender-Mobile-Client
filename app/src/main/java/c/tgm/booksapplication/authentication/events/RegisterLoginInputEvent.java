package c.tgm.booksapplication.authentication.events;

public class RegisterLoginInputEvent {
    private String login;
    
    public RegisterLoginInputEvent(String login) {
        this.login = login;
    }
    
    public String getLogin() {
        return login;
    }
}

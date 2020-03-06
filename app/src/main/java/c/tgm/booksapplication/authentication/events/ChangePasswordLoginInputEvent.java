package c.tgm.booksapplication.authentication.events;

public class ChangePasswordLoginInputEvent {
    private String login;
    
    public ChangePasswordLoginInputEvent(String login) {
        this.login = login;
    }
    
    public String getLogin() {
        return login;
    }
}

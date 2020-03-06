package c.tgm.booksapplication.authentication.authorization.events;

public class LoginErrorEvent {
    String error;
    
    public String getError() {
        return error;
    }
    
    public LoginErrorEvent(String error) {
        this.error = error;
    }
}

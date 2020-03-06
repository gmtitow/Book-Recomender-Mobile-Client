package c.tgm.booksapplication.authentication.registration.confirm.events;

public class RegisterActivationErrorEvent {
    String error;
    
    public RegisterActivationErrorEvent(String error) {
        this.error = error;
    }
    
    public String getError() {
        return error;
    }
}

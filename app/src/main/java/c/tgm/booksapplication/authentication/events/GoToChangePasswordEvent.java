package c.tgm.booksapplication.authentication.events;

public class GoToChangePasswordEvent {
    private String activationCode;
    
    public GoToChangePasswordEvent(String activationCode) {
        this.activationCode = activationCode;
    }
    
    public String getActivationCode() {
        return activationCode;
    }
}

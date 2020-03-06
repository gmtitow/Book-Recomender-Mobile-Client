package c.tgm.booksapplication.authentication.registration.confirm.events;

public class RegisterGetActivationCodeErrorEvent {
    String error;
    Integer timeLeft;
    
    public RegisterGetActivationCodeErrorEvent(String error, Integer timeLeft) {
        this.error = error;
        this.timeLeft = timeLeft;
    }
    
    public RegisterGetActivationCodeErrorEvent(String error) {
        this.error = error;
        timeLeft = null;
    }
    
    public String getError() {
        return error;
    }
    
    public Integer getTimeLeft() {
        return timeLeft;
    }
    
    public void setTimeLeft(Integer timeLeft) {
        this.timeLeft = timeLeft;
    }
}

package c.tgm.booksapplication.authentication.authorization.change_password.code.events;

public class GetResetPasswordCodeFailureEvent {
    private Integer timeLeft;
    private String error;
    
    public GetResetPasswordCodeFailureEvent(String error) {
        this.error = error;
    }
    
    public GetResetPasswordCodeFailureEvent(Integer timeLeft, String error) {
        this.timeLeft = timeLeft;
        this.error = error;
    }
    
    public Integer getTimeLeft() {
        return timeLeft;
    }
    
    public String getError() {
        return error;
    }
    
    public void setTimeLeft(Integer timeLeft) {
        this.timeLeft = timeLeft;
    }
}

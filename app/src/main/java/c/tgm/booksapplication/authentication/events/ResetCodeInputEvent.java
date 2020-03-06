package c.tgm.booksapplication.authentication.events;

public class ResetCodeInputEvent {
    private String resetCode;
    
    public String getResetCode() {
        return resetCode;
    }
    
    public ResetCodeInputEvent(String resetCode) {
        this.resetCode = resetCode;
    }
}

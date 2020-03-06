package c.tgm.booksapplication.authentication.registration.password.events;

public class RegisterErrorEvent {
    private String errorDescription;
    private Integer error;
    
    public RegisterErrorEvent(String errorDescription, Integer error) {
        this.errorDescription = errorDescription;
        this.error = error;
    }
    
    public String getErrorDescription() {
        return errorDescription;
    }
    
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
    
    public Integer getError() {
        return error;
    }
    
    public void setError(Integer error) {
        this.error = error;
    }
}

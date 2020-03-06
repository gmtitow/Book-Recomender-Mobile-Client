package c.tgm.booksapplication.events_for_bus;

public class ErrorEvent {
    private String error;
    
    public ErrorEvent(String error) {
        this.error = error;
    }
    
    public String getError() {
        return error;
    }
}

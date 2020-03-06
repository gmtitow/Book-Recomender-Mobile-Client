package c.tgm.booksapplication.events_for_bus;

public class ShowMessageEvent {
    private String message;
    
    public String getMessage() {
        return message;
    }
    
    public ShowMessageEvent(String message) {
        this.message = message;
    }
}

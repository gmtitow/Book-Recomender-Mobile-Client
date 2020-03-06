package c.tgm.booksapplication.events_for_bus;

public class SearchTypeChoosenEvent {
    private int requestCode;
    
    public SearchTypeChoosenEvent(int requestCode) {
        this.requestCode = requestCode;
    }
    
    public int getRequestCode() {
        return requestCode;
    }
}

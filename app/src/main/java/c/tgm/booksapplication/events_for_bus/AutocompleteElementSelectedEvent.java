package c.tgm.booksapplication.events_for_bus;

public class AutocompleteElementSelectedEvent {
    int type;
    long id;
    
    public int getType() {
        return type;
    }
    
    public long getId() {
        return id;
    }
    
    public AutocompleteElementSelectedEvent(int type, long id) {
        this.type = type;
        this.id = id;
    }
}

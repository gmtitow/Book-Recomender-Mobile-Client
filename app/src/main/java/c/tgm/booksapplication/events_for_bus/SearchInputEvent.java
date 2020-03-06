package c.tgm.booksapplication.events_for_bus;

import java.util.HashMap;

public class SearchInputEvent {
    private String text;
    
    private HashMap<String, Double> center;
    
    private HashMap<String, Double> diagonal;
    
    public String getText() {
        return text;
    }
    
    public HashMap<String, Double> getCenter() {
        return center;
    }
    
    public HashMap<String, Double> getDiagonal() {
        return diagonal;
    }
    
    public SearchInputEvent(String text, HashMap<String, Double> center, HashMap<String, Double> diagonal) {
        this.text = text;
        this.center = center;
        this.diagonal = diagonal;
    }
}

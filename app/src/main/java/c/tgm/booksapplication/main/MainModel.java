package c.tgm.booksapplication.main;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
    
    private List<String> items;
    
    public MainModel() {
        items = new ArrayList<>();
    }
    
    public List<String> getItems() {
        return items;
    }
    
    public void pushItem(String item) {
        items.add(item);
    }
}

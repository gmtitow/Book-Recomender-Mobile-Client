package c.tgm.booksapplication.events_for_bus;

public class ChildCategorySelectedEvent {
    private long childCategoryId;
    
    public ChildCategorySelectedEvent(long childCategoryId) {
        this.childCategoryId = childCategoryId;
    }
    
    public long getChildCategoryId() {
        return childCategoryId;
    }
}

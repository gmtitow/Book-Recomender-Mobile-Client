package c.tgm.booksapplication.review;

public class ReviewAddModel {
    private boolean isEdit = false;
    private int reviewId = -1;
    
    private int bookId;
    
    public boolean isEdit() {
        return isEdit;
    }
    
    public void setEdit(boolean edit) {
        isEdit = edit;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public int getReviewId() {
        return reviewId;
    }
    
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}

package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewWithBook {
    @SerializedName("review")
    @Expose
    private Review review;
    @SerializedName("book_info")
    @Expose
    private BookShort bookInfo;
    
    public Review getReview() {
        return review;
    }
    
    public void setReview(Review review) {
        this.review = review;
    }
    
    public BookShort getBookInfo() {
        return bookInfo;
    }
    
    public void setBookInfo(BookShort bookInfo) {
        this.bookInfo = bookInfo;
    }
}

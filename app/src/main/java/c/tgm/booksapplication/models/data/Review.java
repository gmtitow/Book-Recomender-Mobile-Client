package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {
    @SerializedName("review_id")
    @Expose
    private Integer reviewId;
    @SerializedName("book_id")
    @Expose
    private Integer bookId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("review_text")
    @Expose
    private String reviewText;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("review_date")
    @Expose
    private String reviewDate;
    
    public Integer getReviewId() {
        return reviewId;
    }
    
    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
    
    public Integer getBookId() {
        return bookId;
    }
    
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getReviewText() {
        return reviewText;
    }
    
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public String getReviewDate() {
        return reviewDate;
    }
    
    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}

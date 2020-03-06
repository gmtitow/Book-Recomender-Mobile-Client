package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import c.tgm.booksapplication.models.response.OnlyPaginationResponse;

public class BookInfo extends Book implements Serializable {
    
    @SerializedName("reviews")
    @Expose
    private OnlyPaginationResponse<ArrayList<Review>> reviews;
    
    @SerializedName("review_exists")
    @Expose
    private Boolean reviewExists;
    
    public OnlyPaginationResponse<ArrayList<Review>> getReviews() {
        return reviews;
    }
    
    public void setReviews(OnlyPaginationResponse<ArrayList<Review>> reviews) {
        this.reviews = reviews;
    }
    
    public Boolean getReviewExists() {
        return reviewExists;
    }
    
    public void setReviewExists(Boolean reviewExists) {
        this.reviewExists = reviewExists;
    }
}

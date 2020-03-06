package c.tgm.booksapplication.repositories;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;

public interface ReviewPresenterRepo {
    
    void onAddReview(Review review);
    
    void onEditReview(Review review);
    
    void onDeleteReview(int reviewId);
    
    void onGetReviewList(ArrayList<ReviewWithBook> reviews, boolean rewrite);
    
    void onError(String error);
}

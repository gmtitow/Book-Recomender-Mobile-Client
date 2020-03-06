package c.tgm.booksapplication.review;

import c.tgm.booksapplication.models.data.Review;

public interface ReviewListener {
    void goToChangeReview(Review review);
    
    void deleteReview(int reviewId);
}

package c.tgm.booksapplication.events_for_bus;

import c.tgm.booksapplication.models.data.Review;

public class ReviewChanged {
    private Review review;
    
    public ReviewChanged(Review review) {
        this.review = review;
    }
    
    public Review getReview() {
        return review;
    }
}

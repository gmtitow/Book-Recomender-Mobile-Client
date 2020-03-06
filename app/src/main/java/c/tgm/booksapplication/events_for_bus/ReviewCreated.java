package c.tgm.booksapplication.events_for_bus;

import c.tgm.booksapplication.models.data.Review;

public class ReviewCreated {
    private Review review;
    
    public ReviewCreated(Review review) {
        this.review = review;
    }
    
    public Review getReview() {
        return review;
    }
}

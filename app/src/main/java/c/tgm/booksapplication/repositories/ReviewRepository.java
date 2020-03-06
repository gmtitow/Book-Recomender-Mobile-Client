package c.tgm.booksapplication.repositories;

public interface ReviewRepository {
    void addReview(int rating, String reviewText, int bookId);
    
    void editReview(int reviewId, int rating, String reviewText);
    
    void deleteReview(int reviewId);
    
    void getUsersReviews(int page, int page_size, boolean rewrite);
    
    void cancelRequest();
    
    void retry();
}

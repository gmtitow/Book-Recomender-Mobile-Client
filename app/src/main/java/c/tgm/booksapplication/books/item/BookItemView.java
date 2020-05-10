package c.tgm.booksapplication.books.item;

import com.arellomobile.mvp.MvpView;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Review;

public interface BookItemView extends MvpView {
    void addReview(Review review);
    
    void showCreateReviewBtn();
    
    void deleteReviewFromList(int reviewId);

    void bookWasAdded();

    void showError(String error);
}

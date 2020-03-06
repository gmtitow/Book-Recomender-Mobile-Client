package c.tgm.booksapplication.review.list;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;

public interface ReviewListView extends MvpView {
    
    void updateList(ArrayList<ReviewWithBook> reviews);
    
    void deleteReviewFromList(int reviewId);
}

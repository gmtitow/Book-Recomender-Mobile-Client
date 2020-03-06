package c.tgm.booksapplication.review;

import com.arellomobile.mvp.MvpView;

import c.tgm.booksapplication.models.data.Review;

public interface ReviewAddView extends MvpView {
    void setReview(Review review);
    
    void setEnabled(boolean enable);
}

package c.tgm.booksapplication.review;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import c.tgm.booksapplication.AbstractPresenter;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.events_for_bus.ReviewChanged;
import c.tgm.booksapplication.events_for_bus.ReviewCreated;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;
import c.tgm.booksapplication.repositories.ReviewPresenterRepo;
import c.tgm.booksapplication.repositories.ReviewRepository;
import c.tgm.booksapplication.repositories.ReviewRepositoryImpl;

public class ReviewAddPresenter extends NavigatorPresenter<ReviewAddView> implements ReviewPresenterRepo {
    
    protected ReviewRepository mReviewRepository;
    protected ReviewAddModel mModel;
    
    
    public ReviewAddPresenter() {
        mReviewRepository = new ReviewRepositoryImpl(this);
        mModel = new ReviewAddModel();
    }
    
    public boolean isEdit(){
        return mModel.isEdit();
    }
    @Override
    public void onAddReview(Review review) {
        EventBus.getDefault().postSticky(new ReviewCreated(review));
        exit();
    }
    
    @Override
    public void onEditReview(Review review) {
        EventBus.getDefault().postSticky(new ReviewChanged(review));
        exit();
    }
    
    @Override
    public void onGetReviewList(ArrayList<ReviewWithBook> reviews, boolean rewrite) {
    }
    
    @Override
    public void onDeleteReview(int reviewId) {
    }
    
    public void saveBook(int bookId) {
        mModel.setBookId(bookId);
    }
    
    public void saveReview(int rating, String reviewText){
        if(mModel.isEdit()) {
            mReviewRepository.editReview(mModel.getReviewId(),rating,reviewText);
        } else {
            mReviewRepository.addReview(rating,reviewText,mModel.getBookId());
        }
    }
    
    @Override
    public void onError(String error) {
        mReviewRepository.retry();
    }
    
    public void setChangeReview(Review review) {
        if (getView()!=null)
            getView().setReview(review);
        
        mModel.setEdit(true);
        mModel.setReviewId(review.getReviewId());
    }
}

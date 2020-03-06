package c.tgm.booksapplication.review;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.AbstractPresenter;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.FragmentReviewAddBinding;
import c.tgm.booksapplication.models.data.Review;

public class ReviewAddFragment  extends AbstractFragment implements ReviewAddView {
    
    public static final String REVIEW = "ReviewAddFragment.review";
    public static final String BOOK_ID = "ReviewAddFragment.book_id";
    
    @InjectPresenter(type = PresenterType.LOCAL)
    ReviewAddPresenter mPresenter;
    
    FragmentReviewAddBinding mBinding;
    
    @Override
    public String getTitle() {
        if (getPresenter().isEdit()){
            return "Редактирование отзыва";
        }
        return "Создание отзыва";
    }
    
    @Override
    public ReviewAddPresenter getPresenter() {
        return mPresenter;
    }
    
    @Override
    public boolean withEventBus() {
        return false;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_add, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    
        getPresenter().saveBook(getArguments().getInt(BOOK_ID));
        
        setupViews();
    }
    
    @Override
    public void onStart() {
        super.onStart();
    
        if (getArguments().containsKey(REVIEW)) {
            getPresenter().setChangeReview((Review)getArguments().getSerializable(REVIEW));
        }
    }
    
    public void setupViews() {
        mBinding.addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.rating.getRating() != 0)
                    getPresenter().saveReview(Math.round(mBinding.rating.getRating()), mBinding.textReview.getText().toString());
            }
        });
        
//        mBinding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                ratingBar.setRating((float)Math.ceil(rating));
//            }
//        });
    
        mBinding.rating.setIsIndicator(false);
    }
    
    public static Fragment getInstance(int bookId) {
        ReviewAddFragment fragment = new ReviewAddFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BOOK_ID,bookId);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    public static Fragment getInstance(int bookId, Review review) {
        ReviewAddFragment fragment = new ReviewAddFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(REVIEW,review);
        bundle.putInt(BOOK_ID,bookId);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void setReview(Review review) {
        mBinding.rating.setRating(review.getRating());
        mBinding.textRating.setText(review.getRating().toString());
        mBinding.textReview.setText(review.getReviewText());
        
        mBinding.addReview.setText(getString(R.string.edit_review));
    }
    
    @Override
    public void setEnabled(boolean enable) {
        mBinding.addReview.setEnabled(enable);
    }
}

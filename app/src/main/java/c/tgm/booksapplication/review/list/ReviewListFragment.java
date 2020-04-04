package c.tgm.booksapplication.review.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.ArrayList;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.databinding.FragmentUsersReviewsListBinding;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;
import c.tgm.booksapplication.review.ReviewListener;
import c.tgm.booksapplication.review.list.adapter.ReviewListAdapter;

public class ReviewListFragment extends AbstractFragment implements ReviewListView, IListLoader, ReviewListener, INavigator {
    
    public static final String AUTHOR_ID = "AuthorListFragment.author_id";
    
    @InjectPresenter(type = PresenterType.LOCAL)
    ReviewListPresenter mPresenter;
    
    FragmentUsersReviewsListBinding mBinding;
    
    ReviewListAdapter mAdapter;
    
    @Override
    public String getTitle() {
        return "Список отзывов";
    }
    
    @Override
    public ReviewListPresenter getPresenter() {
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
        
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_users_reviews_list, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        getPresenter().updateReviewList(true);
        
        setupViews();
    }
    
    public void setupViews() {
        mAdapter = new ReviewListAdapter(getContext(),new ArrayList<ReviewWithBook>(),
                this, this, this, BookApplication.INSTANCE.getDataStore().getUserId(),
                getPresenter().getPageSize());
    
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    
    public static Fragment getInstance() {
        ReviewListFragment fragment = new ReviewListFragment();
        return fragment;
    }
    
    @Override
    public void updateList(ArrayList<ReviewWithBook> reviews) {
        if(reviews.size() == 0) {
            mBinding.textEmptyList.setVisibility(View.VISIBLE);
        } else {
            mBinding.textEmptyList.setVisibility(View.GONE);
        }
        mAdapter.setReviews(reviews);
    }
    
    @Override
    public void deleteReviewFromList(int reviewId) {
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            if (mAdapter.getItem(i).getReview().getReviewId()==reviewId) {
                mAdapter.remove(mAdapter.getItem(i));
                mAdapter.notifyDataSetChanged();
                break;
            }
        }
    }
    
    @Override
    public void goToChangeReview(Review review) {
        getPresenter().navigateTo(new Screens.ReviewScreens(Screens.ReviewScreens.ADD_REVIEW_SCREEN,review.getBookId(),review));
    }
    
    @Override
    public void deleteReview(int reviewId) {
        getPresenter().deleteReview(reviewId);
    }
    
    @Override
    public void loadNext() {
        getPresenter().getNextPage();
    }
    
    @Override
    public void goById(int book_id) {
        getPresenter().openBookInfo(book_id);
    }
}

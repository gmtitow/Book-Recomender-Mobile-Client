package c.tgm.booksapplication.books.item.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;

import c.tgm.booksapplication.databinding.BookInfoFullBinding;
import c.tgm.booksapplication.databinding.ReviewListItemBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.review.ReviewHolder;
import c.tgm.booksapplication.review.ReviewListener;

public class ReviewListAdapter extends ArrayAdapter<Review> {
    
    private int mCurrentUserId;
    private ReviewListener mReviewListener;
    private BookInfo mBookInfo;
    private View.OnClickListener mAddToReadListener;
    private View.OnClickListener mAddReviewListener;
    private boolean mShowAddToRead;

    private Button addReviewButton;
    private Integer addReviewVisibility=null;
    private Activity mContext;
    
    public ReviewListAdapter(@NonNull Activity context, @NonNull ArrayList<Review> objects,
                             int currentUserId, ReviewListener listener, BookInfo bookInfo, View.OnClickListener addToReadListener,
                             View.OnClickListener addReviewListener, boolean showAddToRead) {
        super(context, R.layout.review_list_item, objects);
        
        mCurrentUserId = currentUserId;
        mReviewListener = listener;
        mBookInfo = bookInfo;
        mAddToReadListener = addToReadListener;
        mAddReviewListener = addReviewListener;
        mShowAddToRead = showAddToRead;
        mContext = context;
    }

    public int getAddReviewVisibility() {
        return addReviewVisibility;
    }

    public void setAddReviewVisibility(int addReviewVisibility) {
        this.addReviewVisibility = addReviewVisibility;
        if (addReviewButton!=null)
            addReviewButton.setVisibility(addReviewVisibility);
    }

    public void setShowAddToRead(boolean show) {
        mShowAddToRead = show;
        notifyDataSetChanged();
    }

    public Button getReviewAddButton() {
        return addReviewButton;
    }
    
    public BookInfo getBookInfo() {
        return mBookInfo;
    }
    
    public void setBookInfo(BookInfo bookInfo) {
        mBookInfo = bookInfo;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Review review = getItem(position);
        
        if(review.getReviewId()!=-1) {
            ReviewListItemBinding mBinding;
    
//            if (convertView == null) {
//                mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
//                        R.layout.review_list_item, null, false);
//            } else {
//
//                mBinding = DataBindingUtil.bind(convertView);
//            }
    
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                    R.layout.review_list_item, null, false);
    
            ReviewHolder holder = new ReviewHolder(mBinding);
    
            holder.bind(review, mCurrentUserId, mReviewListener);
    
            return mBinding.getRoot();
        } else {
            BookInfoFullBinding mBinding;
//            if (convertView == null) {
//                mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
//                        R.layout.book_info_full, null, false);
//            } else {
//                mBinding = DataBindingUtil.bind(convertView);
//            }
    
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                    R.layout.book_info_full, null, false);

            addReviewButton = mBinding.btnAddReview;

            if (addReviewVisibility!=null)
                addReviewButton.setVisibility(addReviewVisibility);

            BookInfoFullHolder holder = new BookInfoFullHolder(mBinding);
    
            holder.bind(mBookInfo, mContext, mAddToReadListener,mAddReviewListener,mShowAddToRead);
            
            return mBinding.getRoot();
        }
    }
}

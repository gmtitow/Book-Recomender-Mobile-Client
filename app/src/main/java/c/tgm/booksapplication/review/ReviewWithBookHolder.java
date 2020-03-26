package c.tgm.booksapplication.review;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.databinding.ReviewWithBookListItemBinding;
import c.tgm.booksapplication.models.data.ReviewWithBook;

public class ReviewWithBookHolder extends RecyclerView.ViewHolder {
    public ReviewWithBookListItemBinding mBinding;
    
    public ReviewWithBookHolder(ReviewWithBookListItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(final ReviewWithBook review, int currentUserId,
                     final ReviewListener listener, final INavigator navigator) {
        mBinding.content.setText(review.getReview().getReviewText());
        mBinding.rating.setRating(review.getReview().getRating());
        mBinding.textRating.setText(review.getReview().getRating().toString());
    
        try {
            Date date = DataStore.mFormatFromServer.parse(review.getReview().getReviewDate());
            SimpleDateFormat reviewDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());
        
            mBinding.textDate.setText(reviewDateFormat.format(date));
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        if(review.getReview().getUserId() == currentUserId) {
            mBinding.btnChange.setVisibility(View.VISIBLE);
            mBinding.btnDelete.setVisibility(View.VISIBLE);
            
            mBinding.btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.goToChangeReview(review.getReview());
                }
            });
    
            mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.deleteReview(review.getReview().getReviewId());
                }
            });
        } else {
            mBinding.btnChange.setVisibility(View.GONE);
            mBinding.btnDelete.setVisibility(View.GONE);
        }
    
        mBinding.textAuthorName.setText(review.getBookInfo().getAuthorName());
        mBinding.textBookName.setText(review.getBookInfo().getName());
        mBinding.textDescription.setText(review.getBookInfo().getDescription());
        
        mBinding.containerBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goById(review.getBookInfo().getBookId());
            }
        });
    }
}

package c.tgm.booksapplication.review;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.databinding.ReviewListItemBinding;
import c.tgm.booksapplication.databinding.ReviewWithBookListItemBinding;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;

public class ReviewHolder extends RecyclerView.ViewHolder {
    public ReviewListItemBinding mBinding;
    
    public ReviewHolder(ReviewListItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(final Review review, int currentUserId, final ReviewListener listener) {
        mBinding.content.setText(review.getReviewText());
        mBinding.rating.setRating(review.getRating());
        mBinding.textRating.setText(review.getRating().toString());
        
//        SimpleDateFormat mFormatFromServer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.getDefault());
        try {
            Date date = DataStore.mFormatFromServer.parse(review.getReviewDate());
            SimpleDateFormat reviewDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());
    
            mBinding.textDate.setText(reviewDateFormat.format(date));
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        if(review.getUserId() == currentUserId) {
            mBinding.btnChange.setVisibility(View.VISIBLE);
            mBinding.btnDelete.setVisibility(View.VISIBLE);
            
            mBinding.btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.goToChangeReview(review);
                }
            });
            
            mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.deleteReview(review.getReviewId());
                }
            });
        } else {
            mBinding.btnChange.setVisibility(View.GONE);
            mBinding.btnDelete.setVisibility(View.GONE);
        }
    }
}

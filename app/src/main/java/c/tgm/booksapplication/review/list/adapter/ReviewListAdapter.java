package c.tgm.booksapplication.review.list.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.databinding.ReviewWithBookListItemBinding;
import c.tgm.booksapplication.models.data.ReviewWithBook;
import c.tgm.booksapplication.review.ReviewWithBookHolder;
import c.tgm.booksapplication.review.ReviewListener;

/**
 * Created by Герман on 16.11.2017.
 */

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewWithBookHolder> {
    
    private List<ReviewWithBook> mReviews;
    private Context mContext;
    private IListLoader mLoader;
    private int mPageSize;
    private ReviewListener mReviewListener;
    private int mCurrentUserId;
    private INavigator mNavigator;
    
    public ReviewListAdapter(Context context, List<ReviewWithBook> reviews,
                             IListLoader loader, ReviewListener listener, INavigator navigator, int currentUserId,
                             int pageSize)
    {
        mReviews = reviews;
        mContext = context;
        mLoader = loader;
        mPageSize = pageSize;
        mReviewListener = listener;
        mCurrentUserId = currentUserId;
        mNavigator = navigator;
    }
    
    public void setReviews(List<ReviewWithBook> reviews) {
        mReviews = reviews;
        notifyDataSetChanged();
    }
    
    public ReviewWithBook getItem(int position) {
        return mReviews.get(position);
    }
    
    public void remove(ReviewWithBook review) {
        mReviews.remove(review);
    }
    
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    
    public ReviewWithBookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ReviewWithBookListItemBinding binding = null;
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.review_with_book_list_item, parent, false);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        
        return new ReviewWithBookHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(ReviewWithBookHolder holder, final int position) {
        holder.bind(mReviews.get(position), mCurrentUserId,mReviewListener,mNavigator);
    }
    
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    
    @Override
    public void onViewAttachedToWindow(@NonNull ReviewWithBookHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (getItemCount() % mPageSize == 0 && holder.getLayoutPosition()==getItemCount()-1) {
            mLoader.loadNext();
        }
    }
    
    @Override
    public int getItemCount() {
        return mReviews.size();
    }
}

package c.tgm.booksapplication.books.item.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.book_lists.DialogAddToReadFragment;
import c.tgm.booksapplication.databinding.BookInfoFullBinding;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.BookList;

public class BookInfoFullHolder extends RecyclerView.ViewHolder {
    public BookInfoFullBinding mBinding;
    
    public BookInfoFullHolder(BookInfoFullBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(final BookInfo bookInfo, final Context context, View.OnClickListener addToReadListener, boolean showAddToRead) {
        mBinding.textAuthorName.setText(bookInfo.getAuthorName());
        mBinding.textBookName.setText(bookInfo.getName());
        mBinding.textDescription.setText(bookInfo.getDescription());
        mBinding.labelRating.setText(bookInfo.getRating().toString());
        mBinding.ratingBar.setRating(bookInfo.getRating().floatValue());
    
        Picasso.with(context) //передаем контекст приложения
                .load(UserRequest.URL_SERVER + bookInfo.getImagePath())
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .centerInside()
                .into(mBinding.imageView);
        
        if (bookInfo.getReviews().getPagination().getTotal() == 0) {
            mBinding.reviewCount.setText("");
            mBinding.textReview.setText("Отзывов нет");
        } else {
            mBinding.textReview.setText("Отзывов:");
            mBinding.reviewCount.setText(String.valueOf(bookInfo.getReviews().getPagination().getTotal()));
        }

        if (showAddToRead) {
            mBinding.btnAddToRead.setVisibility(View.VISIBLE);
            mBinding.btnAddToRead.setOnClickListener(addToReadListener);
        } else {
            mBinding.btnAddToRead.setVisibility(View.GONE);
        }
    }
}

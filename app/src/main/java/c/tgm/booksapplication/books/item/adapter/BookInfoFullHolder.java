package c.tgm.booksapplication.books.item.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.book_lists.DialogAddToReadFragment;
import c.tgm.booksapplication.databinding.BookInfoFullBinding;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.Genre;

public class BookInfoFullHolder extends RecyclerView.ViewHolder {
    public BookInfoFullBinding mBinding;
    
    public BookInfoFullHolder(BookInfoFullBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(final BookInfo bookInfo, final Activity context, View.OnClickListener addToReadListener,
                     View.OnClickListener addReviewListener, boolean showAddToRead) {
        mBinding.textAuthorName.setText(bookInfo.getAuthorName());
        mBinding.textBookName.setText(bookInfo.getName());
        mBinding.textDescription.setText(bookInfo.getDescription());
        mBinding.labelRating.setText(bookInfo.getRating().toString());
        mBinding.ratingBar.setRating(bookInfo.getRating().floatValue());

        if (bookInfo.getNewPrice()!=null) {
            mBinding.textPriceOld.setVisibility(View.VISIBLE);
            mBinding.textPriceOld.setText(bookInfo.getPrice()==null?"Бесплатно":bookInfo.getPrice()+" р.");
            mBinding.textPriceOld.setPaintFlags(mBinding.textPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mBinding.textPrice.setText(bookInfo.getNewPrice()==null?"Бесплатно":bookInfo.getNewPrice()+" р.");
        } else {
            mBinding.textPrice.setText(bookInfo.getPrice() == null ? "Бесплатно" : bookInfo.getPrice() + " р.");
            mBinding.textPriceOld.setVisibility(View.GONE);
        }

        //Жанры
        mBinding.listGenres.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.topMargin = 10;
        for (Genre genre:bookInfo.getGenres()) {
            View container = context.getLayoutInflater().inflate(R.layout.genre_in_book_info,null);

            TextView textView = (TextView)container.findViewById(R.id.genreName);

            textView.setText(genre.getGenreName());

//            textView.setLayoutParams(params);
            container.setLayoutParams(params);

            mBinding.listGenres.addView(container);
        }
    
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

        if (bookInfo.getReviewExists()) {
            mBinding.btnAddReview.setVisibility(View.GONE);
        } else {
            mBinding.btnAddReview.setVisibility(View.VISIBLE);
            mBinding.btnAddReview.setOnClickListener(addReviewListener);
        }

        if (showAddToRead) {
            mBinding.btnAddToRead.setVisibility(View.VISIBLE);
            mBinding.btnAddToRead.setOnClickListener(addToReadListener);
        } else {
            mBinding.btnAddToRead.setVisibility(View.GONE);
        }


    }
}

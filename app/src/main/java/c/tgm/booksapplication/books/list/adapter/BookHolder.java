package c.tgm.booksapplication.books.list.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.databinding.BookItemBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.request.promotion.BookDescription;

/**
 * Created by Герман on 16.11.2017.
 */

public class BookHolder extends RecyclerView.ViewHolder {
    public BookItemBinding mBinding;
    
    public BookHolder(BookItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bindGood(final Book book, final INavigator navigator, final Activity context) {
        mBinding.textAuthorName.setText(book.getAuthorName());
        mBinding.textBookName.setText(book.getName());
        mBinding.textDescription.setText(book.getDescription());
        mBinding.ratingBar.setRating(book.getRating().floatValue());

        if (book.getNewPrice()!=null) {
            mBinding.textPriceOld.setVisibility(View.VISIBLE);
            mBinding.textPriceOld.setText(book.getPrice()==null?"Бесплатно":book.getPrice()+" р.");
            mBinding.textPriceOld.setPaintFlags(mBinding.textPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mBinding.textPrice.setText(book.getNewPrice()==null?"Бесплатно":book.getNewPrice()+" р.");
        } else {
            mBinding.textPrice.setText(book.getPrice() == null ? "Бесплатно" : book.getPrice() + " р.");
            mBinding.textPriceOld.setVisibility(View.GONE);
        }
    
        Picasso.with(context) //передаем контекст приложения
                .load(UserRequest.URL_SERVER + book.getImagePath())
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .centerInside()
                .into(mBinding.imageView);

        //Жанры
        mBinding.listGenres.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.topMargin = 10;
        for (Genre genre:book.getGenres()) {
            View container = context.getLayoutInflater().inflate(R.layout.genre_in_book_info,null);

            TextView textView = (TextView)container.findViewById(R.id.genreName);

            textView.setText(genre.getGenreName());

//            textView.setLayoutParams(params);
            container.setLayoutParams(params);

            mBinding.listGenres.addView(container);
        }

        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goById(book.getBookId());
            }
        });
    }

    public void bindGood(final BookDescription description, final INavigator navigator, final Activity context, final boolean delete) {

        bindGood(description.getBook(),navigator,context);

        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delete)
                    navigator.goById(description);
            }
        });
    }
}

package c.tgm.booksapplication.promotion.add.selectbooks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.databinding.SelectBookItemBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Book;

public class SelectBooksHolder extends RecyclerView.ViewHolder {
    SelectBookItemBinding mBinding;

    public SelectBooksHolder(SelectBookItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public void bind(final Book book, final INavigator navigator, final Context context) {
        mBinding.textAuthorName.setText(book.getAuthorName());
        mBinding.textBookName.setText(book.getName());
        mBinding.textDescription.setText(book.getDescription());
        mBinding.ratingBar.setRating(book.getRating().floatValue());

        Picasso.with(context) //передаем контекст приложения
                .load(UserRequest.URL_SERVER + book.getImagePath())
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .centerInside()
                .into(mBinding.imageView);


        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goById(book);
            }
        });
    }
}

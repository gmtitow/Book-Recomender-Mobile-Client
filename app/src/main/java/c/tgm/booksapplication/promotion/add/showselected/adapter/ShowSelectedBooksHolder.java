package c.tgm.booksapplication.promotion.add.showselected.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.databinding.SelectBookItemBinding;
import c.tgm.booksapplication.databinding.SelectedBookDescriptionBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.request.promotion.BookDescription;

public class ShowSelectedBooksHolder extends RecyclerView.ViewHolder {
    SelectedBookDescriptionBinding mBinding;

    public ShowSelectedBooksHolder(SelectedBookDescriptionBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public void bind(final BookDescription bookDescription, final IRemover remover, final boolean delete) {
        if (bookDescription.getAuthor()==null)
            mBinding.textAuthor.setText("Не выбран");
        else
            mBinding.textAuthor.setText(bookDescription.getAuthor().getFullName());
        if (bookDescription.getGenre() == null)
            mBinding.textGenre.setText("Не выбран");
        else
            mBinding.textGenre.setText(bookDescription.getGenre().getGenreName());

        mBinding.textQuery.setText(bookDescription.getQuery());

        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delete)
                    remover.delete(bookDescription);
            }
        });
    }
}

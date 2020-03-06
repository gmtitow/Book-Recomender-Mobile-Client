package c.tgm.booksapplication.authors.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import c.tgm.booksapplication.books.list.adapter.INavigator;
import c.tgm.booksapplication.databinding.AuthorListItemBinding;
import c.tgm.booksapplication.models.data.Author;

/**
 * Created by Герман on 16.11.2017.
 */

public class AuthorHolder extends RecyclerView.ViewHolder {
    public AuthorListItemBinding mBinding;
    
    public AuthorHolder(AuthorListItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(final Author author, final INavigator navigator) {
        mBinding.textFullName.setText(author.getFullName());
        
        mBinding.buttonToBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.showBookInfo(author.getAuthorId());
            }
        });
    }
}

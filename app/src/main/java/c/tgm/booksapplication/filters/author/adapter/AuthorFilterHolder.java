package c.tgm.booksapplication.filters.author.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import c.tgm.booksapplication.databinding.AuthorFilterListItemBinding;
import c.tgm.booksapplication.databinding.AuthorListItemBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Author;

/**
 * Created by Герман on 16.11.2017.
 */

public class AuthorFilterHolder extends RecyclerView.ViewHolder {
    public AuthorFilterListItemBinding mBinding;
    
    public AuthorFilterHolder(AuthorFilterListItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(final Author author, final INavigator navigator) {
        mBinding.textFullName.setText(author.getFullName());
        
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goById(author);
            }
        });
    }
}

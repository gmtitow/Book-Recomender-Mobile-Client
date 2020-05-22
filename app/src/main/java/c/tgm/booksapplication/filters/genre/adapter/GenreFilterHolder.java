package c.tgm.booksapplication.filters.genre.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import c.tgm.booksapplication.databinding.AuthorFilterListItemBinding;
import c.tgm.booksapplication.databinding.GenreFilterListItemBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

/**
 * Created by Герман on 16.11.2017.
 */

public class GenreFilterHolder extends RecyclerView.ViewHolder {
    public GenreFilterListItemBinding mBinding;
    
    public GenreFilterHolder(GenreFilterListItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(final Genre genre, final INavigator navigator) {
        mBinding.textFullName.setText(genre.getGenreName());
        
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goById(genre);
            }
        });
    }
}

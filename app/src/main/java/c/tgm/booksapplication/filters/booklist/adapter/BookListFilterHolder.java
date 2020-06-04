package c.tgm.booksapplication.filters.booklist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import c.tgm.booksapplication.databinding.BookListFilterItemBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.BookList;

/**
 * Created by Герман on 16.11.2017.
 */

public class BookListFilterHolder extends RecyclerView.ViewHolder {
    public BookListFilterItemBinding mBinding;
    
    public BookListFilterHolder(BookListFilterItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(final BookList list, final INavigator navigator) {
        mBinding.textListName.setText(list.getListName());
        
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goById(list);
            }
        });
    }
}

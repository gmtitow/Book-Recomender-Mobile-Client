package c.tgm.booksapplication.book_lists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.interfaces.IEnableManager;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.databinding.BookListsItemBinding;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.BookList;

/**
 * Created by Герман on 16.11.2017.
 */

public class BookListHolder extends RecyclerView.ViewHolder {
    public BookListsItemBinding mBinding;

    public BookListHolder(BookListsItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bindGood(final BookList list,
                         final INavigator navigator, final IRemover remover, final IEnableManager enableManager,
                         boolean deleteVisible) {
        mBinding.textListName.setText(list.getListName());

        if (deleteVisible) {
            mBinding.button.setImageResource(R.drawable.ic_delete_black_24dp);
            mBinding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (enableManager.isEnable())
                        remover.delete(list.getListId().intValue());
                }
            });
        } else {
            mBinding.button.setImageResource(R.drawable.ic_arrow_forward_black_24dp);

            mBinding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (enableManager.isEnable())
                        navigator.goById(list.getListId().intValue());
                }
            });
        }
    }
}

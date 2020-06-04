package c.tgm.booksapplication.filters.booklist.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import c.tgm.booksapplication.AbstractAdapter;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.BookListFilterItemBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.BookList;

/**
 * Created by Герман on 16.11.2017.
 */

public class BookListFilterAdapter extends AbstractAdapter<BookList, BookListFilterHolder, BookListFilterItemBinding> {

    private INavigator mNavigator;

    public BookListFilterAdapter(List<BookList> lists, Activity context, INavigator mNavigator) {
        super(lists, context);
        this.mNavigator = mNavigator;
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public BookListFilterHolder createNewHolder(BookListFilterItemBinding binding, int type) {
        return new BookListFilterHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.book_list_filter_item;
    }

    @Override
    public void onBindViewHolder(BookListFilterHolder holder, final int position) {
        holder.bind(getObjects().get(position), mNavigator);
    }
    
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    
    @Override
    public int getItemCount() {
        return getObjects().size();
    }
}

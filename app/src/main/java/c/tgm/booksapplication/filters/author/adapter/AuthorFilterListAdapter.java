package c.tgm.booksapplication.filters.author.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import c.tgm.booksapplication.AbstractAdapter;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.AuthorFilterListItemBinding;
import c.tgm.booksapplication.databinding.AuthorListItemBinding;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.pagination.APaginationAdapter;

/**
 * Created by Герман on 16.11.2017.
 */

public class AuthorFilterListAdapter extends APaginationAdapter<Author,AuthorFilterHolder, AuthorFilterListItemBinding> {

    private INavigator mNavigator;

    public AuthorFilterListAdapter(List<Author> authors, Activity context, IListLoader loader, int pageSize, INavigator mNavigator) {
        super(authors, context, loader, pageSize);
        this.mNavigator = mNavigator;
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public AuthorFilterHolder createNewHolder(AuthorFilterListItemBinding binding, int type) {
        return new AuthorFilterHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.author_filter_list_item;
    }

    @Override
    public void onBindViewHolder(AuthorFilterHolder holder, final int position) {
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

package c.tgm.booksapplication.filters.genre.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import c.tgm.booksapplication.AbstractAdapter;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.AuthorFilterListItemBinding;
import c.tgm.booksapplication.databinding.GenreFilterListItemBinding;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.pagination.APaginationAdapter;

/**
 * Created by Герман on 16.11.2017.
 */

public class GenreFilterListAdapter extends AbstractAdapter<Genre, GenreFilterHolder, GenreFilterListItemBinding> {

    private INavigator mNavigator;

    public GenreFilterListAdapter(List<Genre> genres, Activity context, INavigator mNavigator) {
        super(genres, context);
        this.mNavigator = mNavigator;
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public GenreFilterHolder createNewHolder(GenreFilterListItemBinding binding, int type) {
        return new GenreFilterHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.genre_filter_list_item;
    }

    @Override
    public void onBindViewHolder(GenreFilterHolder holder, final int position) {
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

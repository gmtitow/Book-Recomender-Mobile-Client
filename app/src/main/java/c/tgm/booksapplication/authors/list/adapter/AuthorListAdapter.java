package c.tgm.booksapplication.authors.list.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.databinding.AuthorListItemBinding;
import c.tgm.booksapplication.models.data.Author;

/**
 * Created by Герман on 16.11.2017.
 */

public class AuthorListAdapter extends RecyclerView.Adapter<AuthorHolder> {
    
    private List<Author> mAuthors;
    private Context mContext;
    private IListLoader mLoader;
    private INavigator mNavigator;
    private int mPageSize;
    
    public AuthorListAdapter(Context context, List<Author> authors, IListLoader loader, INavigator navigator, int pageSize)
    {
        mAuthors = authors;
        mContext = context;
        mLoader = loader;
        mNavigator = navigator;
        mPageSize = pageSize;
    }
    
    public void setAuthors(List<Author> authors) {
        mAuthors = authors;
        notifyDataSetChanged();
    }
    
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    
    public AuthorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        AuthorListItemBinding binding = null;
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.author_list_item, parent, false);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        
        return new AuthorHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(AuthorHolder holder, final int position) {
        holder.bind(mAuthors.get(position), mNavigator);
    }
    
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    
    @Override
    public void onViewAttachedToWindow(@NonNull AuthorHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (getItemCount() % mPageSize == 0 && holder.getLayoutPosition()==getItemCount()-1) {
            mLoader.loadNext();
        }
    }
    
    @Override
    public int getItemCount() {
        return mAuthors.size();
    }
}

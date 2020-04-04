package c.tgm.booksapplication.book_lists.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.interfaces.IEnableManager;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.databinding.BookListsItemBinding;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.BookList;

/**
 * Created by Герман on 16.11.2017.
 */

public class BookListsListAdapter extends RecyclerView.Adapter<BookListHolder> implements IEnableManager {

    private List<BookList> mBookLists;
    private Context mContext;
    private IListLoader mLoader;
    private INavigator mNavigator;
    private IRemover mRemover;

    private boolean mEnable = true;

    private boolean mDeleteVisible = false;

    @Override
    public boolean isEnable() {
        return this.mEnable;
    }

    public void setEnable(boolean mEnable) {
        this.mEnable = mEnable;
    }

    public BookListsListAdapter(Context context, List<BookList> lists,
                                IListLoader loader, INavigator navigator, IRemover remover)
    {
        mBookLists = lists;
        mContext = context;
        mLoader = loader;
        mNavigator = navigator;
        mRemover = remover;
    }
    
    public void setLists(List<BookList> lists) {
        mBookLists = lists;
        notifyDataSetChanged();
    }

    public void setDelete(boolean deleteVisible) {
        if (deleteVisible == this.mDeleteVisible)
            return;

        this.mDeleteVisible = deleteVisible;
        notifyDataSetChanged();
    }

    public void changeDelete() {
        this.mDeleteVisible = !this.mDeleteVisible;
        notifyDataSetChanged();
    }

    public boolean isDeleteVisible() {
        return mDeleteVisible;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    
    @NonNull
    public BookListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BookListsItemBinding binding;
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.book_lists_item, parent, false);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        
        return new BookListHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(BookListHolder holder, final int position) {
        holder.bindGood(mBookLists.get(position), mNavigator, mRemover,this, mDeleteVisible);
    }
    
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    
    @Override
    public void onViewAttachedToWindow(@NonNull BookListHolder holder) {
        super.onViewAttachedToWindow(holder);
//        if (getItemCount() % mPageSize == 0 && holder.getLayoutPosition()==getItemCount()-1) {
//            mLoader.loadNext();
//        }
    }
    
    @Override
    public int getItemCount() {
        return mBookLists.size();
    }
}

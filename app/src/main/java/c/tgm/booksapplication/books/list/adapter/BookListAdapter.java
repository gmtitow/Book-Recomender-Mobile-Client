package c.tgm.booksapplication.books.list.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.BookListItemBinding;
import c.tgm.booksapplication.models.data.Book;

/**
 * Created by Герман on 16.11.2017.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookHolder> {
    
    private List<Book> mBooks;
    private Context mContext;
    private IListLoader mLoader;
    private INavigator mNavigator;
    private int mPageSize;
    
    public BookListAdapter(Context context, List<Book> books, IListLoader loader,INavigator navigator, int pageSize)
    {
        mBooks = books;
        mContext = context;
        mLoader = loader;
        mNavigator = navigator;
        mPageSize = pageSize;
    }
    
    public void setBooks(List<Book> books) {
        mBooks = books;
        notifyDataSetChanged();
    }
    
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        BookListItemBinding binding = null;
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.book_list_item, parent, false);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        
        return new BookHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(BookHolder holder, final int position) {
        holder.bindGood(mBooks.get(position), mNavigator, mContext);
    }
    
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    
    @Override
    public void onViewAttachedToWindow(@NonNull BookHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (getItemCount() % mPageSize == 0 && holder.getLayoutPosition()==getItemCount()-1) {
            mLoader.loadNext();
        }
    }
    
    @Override
    public int getItemCount() {
        return mBooks.size();
    }
}

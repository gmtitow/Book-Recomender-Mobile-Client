package c.tgm.booksapplication.book_lists.read_books.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.ReadBookItemBinding;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.pagination.APaginationAdapter;

public class ReadBooksAdapter extends APaginationAdapter<ReadBook, ReadBookHolder, ReadBookItemBinding> {


    private IRemover mRemover;
    private boolean mDeleteVisible = false;

    public ReadBooksAdapter(List<ReadBook> readBooks, Context context, IListLoader loader, int pageSize, IRemover remover) {
        super(readBooks, context, loader, pageSize);
        mRemover = remover;
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
    public void onBindViewHolder(@NonNull ReadBookHolder holder, int position) {
        holder.bind(mObjects.get(position), mContext, mRemover, mDeleteVisible);
    }

    @Override
    public ReadBookHolder createNewHolder(ReadBookItemBinding binding, int i) {
        return new ReadBookHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.read_book_item;
    }
}

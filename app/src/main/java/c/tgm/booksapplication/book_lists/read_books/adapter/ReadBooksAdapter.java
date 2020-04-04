package c.tgm.booksapplication.book_lists.read_books.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.ReadBookItemBinding;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.pagination.APaginationAdapter;

public class ReadBooksAdapter extends APaginationAdapter<ReadBook, ReadBookHolder, ReadBookItemBinding> {

    public ReadBooksAdapter(List<ReadBook> readBooks, Context context, IListLoader loader, int pageSize) {
        super(readBooks, context, loader, pageSize);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadBookHolder holder, int position) {
        holder.bind(mObjects.get(position), mContext);
    }

    @Override
    public ReadBookHolder createNewHolder(ReadBookItemBinding binding) {
        return new ReadBookHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.read_book_item;
    }
}

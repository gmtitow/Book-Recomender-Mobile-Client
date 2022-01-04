package c.tgm.booksapplication.promotion.add.selectbooks.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.books.list.adapter.BookHolder;
import c.tgm.booksapplication.databinding.BookItemBinding;
import c.tgm.booksapplication.databinding.SelectBookItemBinding;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.pagination.APaginationAdapter;

public class PromotionSelectBooksAdapter extends APaginationAdapter<Book, BookHolder, BookItemBinding> {

    protected INavigator mNavigator;

    public PromotionSelectBooksAdapter(List<Book> books, Activity context, IListLoader loader, int pageSize, INavigator mNavigator) {
        super(books, context, loader, pageSize);
        this.mNavigator = mNavigator;
    }

    @Override
    public BookHolder createNewHolder(BookItemBinding binding, int i) {
        return new BookHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.book_item;
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder selectBooksHolder, int i) {
        selectBooksHolder.bindGood(getObjects().get(i),mNavigator,mContext);
    }
}

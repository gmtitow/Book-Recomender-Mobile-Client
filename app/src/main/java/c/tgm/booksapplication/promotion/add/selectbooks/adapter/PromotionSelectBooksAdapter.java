package c.tgm.booksapplication.promotion.add.selectbooks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.SelectBookItemBinding;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.pagination.APaginationAdapter;

public class PromotionSelectBooksAdapter extends APaginationAdapter<Book,SelectBooksHolder, SelectBookItemBinding> {

    protected INavigator mNavigator;

    public PromotionSelectBooksAdapter(List<Book> books, Context context, IListLoader loader, int pageSize, INavigator mNavigator) {
        super(books, context, loader, pageSize);
        this.mNavigator = mNavigator;
    }

    @Override
    public SelectBooksHolder createNewHolder(SelectBookItemBinding binding, int i) {
        return new SelectBooksHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.select_book_item;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectBooksHolder selectBooksHolder, int i) {
        selectBooksHolder.bind(getObjects().get(i),mNavigator,mContext);
    }
}

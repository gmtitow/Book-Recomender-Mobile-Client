package c.tgm.booksapplication.promotion.add.showselected.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import c.tgm.booksapplication.AbstractAdapter;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.SelectedBookDescriptionBinding;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.APaginationAdapter;

public class ShowSelectedBooksAdapter extends AbstractAdapter<BookDescription, ShowSelectedBooksHolder, SelectedBookDescriptionBinding> {

    protected IRemover mRemover;

    public ShowSelectedBooksAdapter(List<BookDescription> bookDescriptions, Context context, IRemover mRemover) {
        super(bookDescriptions, context);
        this.mRemover = mRemover;
    }

    @Override
    public ShowSelectedBooksHolder createNewHolder(SelectedBookDescriptionBinding binding) {
        return new ShowSelectedBooksHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.selected_book_description;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowSelectedBooksHolder selectBooksHolder, int i) {
        selectBooksHolder.bind(getObjects().get(i), mRemover);
    }
}

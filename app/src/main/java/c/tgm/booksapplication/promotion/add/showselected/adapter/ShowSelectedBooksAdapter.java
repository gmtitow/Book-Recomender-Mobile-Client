package c.tgm.booksapplication.promotion.add.showselected.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import c.tgm.booksapplication.AbstractAdapter;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.books.list.adapter.BookHolder;
import c.tgm.booksapplication.databinding.BookItemBinding;
import c.tgm.booksapplication.databinding.SelectedBookDescriptionBinding;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.APaginationAdapter;

public class ShowSelectedBooksAdapter extends AbstractAdapter<BookDescription, RecyclerView.ViewHolder, ViewDataBinding>
implements INavigator {

    protected IRemover mRemover;
    private boolean mDeleteVisible = false;


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

    public ShowSelectedBooksAdapter(List<BookDescription> bookDescriptions, Activity context, IRemover mRemover) {
        super(bookDescriptions, context);
        this.mRemover = mRemover;
    }

    public boolean isDeleteVisible() {
        return mDeleteVisible;
    }

    @Override
    public RecyclerView.ViewHolder createNewHolder(ViewDataBinding binding, int type) {
        if (type==1)
            return new ShowSelectedBooksHolder((SelectedBookDescriptionBinding) binding);
        else
            return new BookHolder((BookItemBinding)binding);
    }


    @Override
    public int getItemViewType(int position) {
        return getObjects().get(position).getType().equals(BookDescription.TYPE_BOOK)?0:1;
    }

    @Override
    public int getResourceToItem() {
        return R.layout.selected_book_description;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder selectBooksHolder, int position) {
        if (getItemViewType(position)==0) {
            ((BookHolder)selectBooksHolder).bindGood(getObjects().get(position),this,mContext, mDeleteVisible);
        } else {
            ((ShowSelectedBooksHolder)selectBooksHolder).bind(getObjects().get(position), mRemover, mDeleteVisible);
        }

    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup viewGroup, int type) {
        if (type==0) {
            return DataBindingUtil.inflate(inflater, R.layout.book_item, viewGroup, false);
        } else {
            return DataBindingUtil.inflate(inflater, R.layout.selected_book_description, viewGroup, false);
        }
    }

    @Override
    public void goById(int id) {
        mRemover.delete(id);
    }

    @Override
    public void goById(Object object) {
        mRemover.delete(object);
    }
}

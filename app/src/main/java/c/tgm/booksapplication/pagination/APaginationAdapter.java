package c.tgm.booksapplication.pagination;

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
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;

public abstract class APaginationAdapter<
        Objects,
        Holder extends RecyclerView.ViewHolder,
        Binding extends ViewDataBinding
        > extends AbstractAdapter<Objects,Holder,Binding> {

    protected IListLoader mLoader;
    protected int mPageSize;

    public APaginationAdapter(List<Objects> objects, Activity context, IListLoader loader, int pageSize)
    {
        super(objects,context);
        mLoader = loader;
        mPageSize = pageSize;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull Holder holder) {
        super.onViewAttachedToWindow(holder);
        if (getItemCount() % mPageSize == 0 && holder.getLayoutPosition()==getItemCount()-1) {
            mLoader.loadNext();
        }
    }
}

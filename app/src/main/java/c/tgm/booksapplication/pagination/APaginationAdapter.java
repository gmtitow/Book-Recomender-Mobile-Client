package c.tgm.booksapplication.pagination;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;

public abstract class APaginationAdapter<
        Objects,
        Holder extends RecyclerView.ViewHolder,
        Binding extends ViewDataBinding
        > extends RecyclerView.Adapter<Holder> {

    protected List<Objects> mObjects;
    protected IListLoader mLoader;
    protected int mPageSize;
    protected Context mContext;

    public APaginationAdapter(List<Objects> objects, Context context, IListLoader loader, int pageSize)
    {
        mObjects = objects;
        mLoader = loader;
        mPageSize = pageSize;
        mContext = context;
    }

    public void setObjects(List<Objects> lists) {
        mObjects = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        Binding binding = null;
        try {
            binding = DataBindingUtil.inflate(inflater, getResourceToItem(), viewGroup, false);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return createNewHolder(binding);
    }

    public abstract Holder createNewHolder(Binding binding);

    public abstract int getResourceToItem();

    @Override
    public void onViewAttachedToWindow(@NonNull Holder holder) {
        super.onViewAttachedToWindow(holder);
        if (getItemCount() % mPageSize == 0 && holder.getLayoutPosition()==getItemCount()-1) {
            mLoader.loadNext();
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }
}

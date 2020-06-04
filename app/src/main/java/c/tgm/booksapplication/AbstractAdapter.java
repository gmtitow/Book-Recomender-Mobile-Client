package c.tgm.booksapplication;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class AbstractAdapter<
        Objects,
        Holder extends RecyclerView.ViewHolder,
        Binding extends ViewDataBinding
        > extends RecyclerView.Adapter<Holder> {

    protected Activity mContext;
    protected List<Objects> mObjects;

    public AbstractAdapter(List<Objects> objects, Activity context)
    {
        mObjects = objects;
        mContext = context;
    }

    public void setObjects(List<Objects> lists) {
        mObjects = lists;
        notifyDataSetChanged();
    }

    public List<Objects> getObjects() {
        return mObjects;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        Binding binding = null;
        try {
            binding = getBinding(inflater,viewGroup,i);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return createNewHolder(binding,i);
    }

    public Binding getBinding(LayoutInflater inflater, ViewGroup viewGroup, int type) {
        return DataBindingUtil.inflate(inflater, getResourceToItem(), viewGroup, false);
    }

    public abstract Holder createNewHolder(Binding binding, int type);

    public abstract int getResourceToItem();

    @Override
    public int getItemCount() {
        return mObjects.size();
    }
}

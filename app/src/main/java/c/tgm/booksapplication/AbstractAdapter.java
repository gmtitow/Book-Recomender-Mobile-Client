package c.tgm.booksapplication;

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

    protected Context mContext;
    protected List<Objects> mObjects;

    public AbstractAdapter(List<Objects> objects, Context context)
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
    public int getItemCount() {
        return mObjects.size();
    }
}

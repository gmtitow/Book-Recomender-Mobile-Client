package c.tgm.booksapplication.main.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.ItemBinding;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsHolder> {
    
    private List<String> mItems;
    private Context mContext;
    
    public ItemsAdapter(List<String> items, Context context)
    {
        this.mItems = items;
        mContext=context;
    }
    
    public void reviewList(List<String> items) {
        mItems = items;
        this.notifyDataSetChanged();
    }
    
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    
    public ItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ItemBinding binding = null;
        try {
            binding = DataBindingUtil.inflate(inflater, R.layout.item, parent, false);
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return new ItemsHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(ItemsHolder holder, final int position) {
        holder.bind(mItems.get(position));
    }
    
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

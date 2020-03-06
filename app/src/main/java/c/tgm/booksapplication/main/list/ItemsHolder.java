package c.tgm.booksapplication.main.list;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import c.tgm.booksapplication.databinding.ItemBinding;

public class ItemsHolder extends RecyclerView.ViewHolder {
    public ItemBinding mBinding;
    
    public ItemsHolder(ItemBinding binding)
    {
        super(binding.getRoot());
        mBinding = binding;
    }
    
    public void bind(String item)
    {
        mBinding.textItem.setText(item);
    }
}
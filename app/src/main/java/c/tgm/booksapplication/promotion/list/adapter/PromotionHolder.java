package c.tgm.booksapplication.promotion.list.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.PromotionItemBinding;
import c.tgm.booksapplication.interfaces.IEnableManager;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.Promotion;

public class PromotionHolder extends RecyclerView.ViewHolder {

    PromotionItemBinding mBinding;

    public PromotionHolder(PromotionItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @SuppressLint("SetTextI18n")
    public void bind(final Promotion promotion,
                     final INavigator navigator, final IRemover remover, final IEnableManager enableManager,
                     boolean deleteVisible, boolean shareVisible) {
        mBinding.textDescription.setText(promotion.getDescription());

        mBinding.textDate.setText("с " + promotion.getTimeStart() + " до " + promotion.getTimeEnd());

        if (deleteVisible) {
            mBinding.button.setImageResource(R.drawable.ic_delete_black_24dp);
            mBinding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (enableManager.isEnable())
                        remover.delete(promotion.getPromotionId());
                }
            });
        } else if (shareVisible){
            mBinding.button.setImageResource(R.drawable.ic_share_black);
            mBinding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        else {
            mBinding.button.setImageResource(R.drawable.ic_arrow_forward_black_24dp);

            mBinding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (enableManager.isEnable())
                        navigator.goById(promotion.getPromotionId());
                }
            });
        }
    }
}

package c.tgm.booksapplication.promotion.list.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.ParseException;
import java.util.Date;

import javax.sql.DataSource;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.DataStore;
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
        try {
            Date start = DataStore.mFormatForTokenLifetime.parse(promotion.getTimeStart());
            Date end = DataStore.mFormatForTokenLifetime.parse(promotion.getTimeEnd());

            mBinding.textDate.setText("с " +DataStore.mFormatForDisplay.format(start) +" до " +
                    DataStore.mFormatForDisplay.format(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (deleteVisible) {
            mBinding.button.setImageResource(R.drawable.ic_delete_black_24dp);
            mBinding.button.setOnClickListener(v -> {
                if (enableManager.isEnable())
                    remover.delete(promotion.getPromotionId());
            });
        } else if (shareVisible){
            mBinding.button.setImageResource(R.drawable.ic_share_black);
            mBinding.button.setOnClickListener(v -> {
            });
        }
        else {
            mBinding.button.setImageResource(R.drawable.ic_arrow_forward_black_24dp);

            mBinding.button.setOnClickListener(v -> {
                if (enableManager.isEnable())
                    navigator.goById(promotion.getPromotionId());
            });
        }
    }
}

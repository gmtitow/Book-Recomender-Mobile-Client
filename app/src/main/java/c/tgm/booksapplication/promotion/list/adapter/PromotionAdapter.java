package c.tgm.booksapplication.promotion.list.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.PromotionItemBinding;
import c.tgm.booksapplication.interfaces.IEnableManager;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.Promotion;
import c.tgm.booksapplication.pagination.APaginationAdapter;

public class PromotionAdapter extends APaginationAdapter<Promotion,PromotionHolder, PromotionItemBinding> {

    private INavigator mNavigator;
    private IRemover mRemover;
    private IEnableManager mEnableManager;

    private boolean mDeleteVisible = false;
    private boolean mShareVisible = false;

    public boolean isDeleteVisible() {
        return mDeleteVisible;
    }

    public void setDeleteVisible(boolean mDeleteVisible) {
        this.mDeleteVisible = mDeleteVisible;
    }

    public void changeDeleteVisible() {
        this.mDeleteVisible = !this.mDeleteVisible;
    }

    public boolean isShareVisible() {
        return mShareVisible;
    }

    public void setShareVisible(boolean mShareVisible) {
        this.mShareVisible = mShareVisible;
    }

    public void changeShareVisible() {
        this.mShareVisible = !this.mShareVisible;
    }

    public PromotionAdapter(List<Promotion> promotions, Context context, IListLoader loader, int pageSize, INavigator mNavigator, IRemover mRemover, IEnableManager mEnableManager) {
        super(promotions, context, loader, pageSize);
        this.mNavigator = mNavigator;
        this.mRemover = mRemover;
        this.mEnableManager = mEnableManager;
    }

    @Override
    public PromotionHolder createNewHolder(PromotionItemBinding binding) {
        return new PromotionHolder(binding);
    }

    @Override
    public int getResourceToItem() {
        return R.layout.promotion_item;
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionHolder promotionHolder, int i) {
        promotionHolder.bind(mObjects.get(i),mNavigator,mRemover,mEnableManager,mDeleteVisible,mShareVisible);
    }
}

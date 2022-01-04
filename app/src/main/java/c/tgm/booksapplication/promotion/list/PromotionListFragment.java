package c.tgm.booksapplication.promotion.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.TemplateListLayoutBinding;
import c.tgm.booksapplication.interfaces.IEnableManager;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.Promotion;
import c.tgm.booksapplication.pagination.APaginationFragment;
import c.tgm.booksapplication.promotion.list.adapter.PromotionAdapter;

public class PromotionListFragment extends APaginationFragment<
        Promotion, PromotionListModel, PromotionView, PromotionListPresenter, TemplateListLayoutBinding,
        PromotionAdapter> implements INavigator, IRemover, IEnableManager, PromotionView {

    @InjectPresenter(type = PresenterType.LOCAL)
    PromotionListPresenter mPresenter;

    private boolean mEnabled = true;

    MenuItem mShare;
    MenuItem mDelete;

    @Override
    public PromotionListPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.promotions_menu, menu);

        mShare = menu.getItem(1);
        mDelete = menu.getItem(2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:{
                mAdapter.changeDeleteVisible();
                if (mAdapter.isDeleteVisible())
                    item.setIcon(R.drawable.ic_close);
                else
                    item.setIcon(R.drawable.ic_delete);

                mAdapter.setShareVisible(false);
                mShare.setIcon(R.drawable.ic_share);

                mAdapter.notifyDataSetChanged();
                break;
            }

            case R.id.action_share:{
                mAdapter.changeShareVisible();
                if (mAdapter.isShareVisible())
                    item.setIcon(R.drawable.ic_close);
                else
                    item.setIcon(R.drawable.ic_share);

                mAdapter.setDeleteVisible(false);
                mDelete.setIcon(R.drawable.ic_delete);

                mAdapter.notifyDataSetChanged();
                break;
            }

            case R.id.action_add:{
                getPresenter().goToAddPromotion();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initializeAdapter() {
        this.mAdapter = new PromotionAdapter(
                getPresenter().getObjects(),getActivity(),this,getPresenter().getPageSize(),
                this,this,this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.template_list_layout;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mBinding.recyclerView;
    }

    @Override
    public boolean withEventBus() {
        return false;
    }

    @Override
    public boolean isEnable() {
        return mEnabled;
    }

    @Override
    public void goById(int id) {

    }

    @Override
    public void delete(Object id) {
        getPresenter().deletePromotion((int)id);
    }

    public static Fragment getInstance() {
        return new PromotionListFragment();
    }

    @Override
    public void setEnable(boolean enable) {
        mEnabled = enable;
    }

    @Override
    public String getTitle() {
        return "Акции";
    }
}



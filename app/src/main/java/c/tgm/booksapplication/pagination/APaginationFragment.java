package c.tgm.booksapplication.pagination;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.ArrayList;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;

public abstract class APaginationFragment
        <
                MainObject,
                Model extends APaginationModel<MainObject>,
                IView extends IPaginationView<MainObject>,
                PaginationPresenter extends APaginationPresenter<IView, MainObject,Model>,
                DataBinding extends ViewDataBinding,
                PaginationAdapter extends APaginationAdapter
        >
        extends AbstractFragment implements IPaginationView<MainObject>, IListLoader {

    protected DataBinding mBinding;

    protected PaginationAdapter mAdapter;

    @Override
    public abstract PaginationPresenter getPresenter();

    protected PaginationAdapter getAdapter() {
        return mAdapter;
    }

    protected abstract void initializeAdapter();

    protected void setupViews() {
        initializeAdapter();

        getRecyclerView().setAdapter(mAdapter);
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter.setObjects(getPresenter().getObjects());
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().getNewObjects(false);
    }

    protected abstract int getLayoutResourceId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        beforeSetupView();

        setupViews();

        afterSetupView();
    }

    protected void beforeSetupView() {

    }

    protected void afterSetupView() {

    }

    @Override
    public void loadNext() {
        getPresenter().getNextPage();
    }

    @Override
    public void updateList(ArrayList<MainObject> objects) {
        getAdapter().setObjects(objects);
    }

    public abstract RecyclerView getRecyclerView();
}

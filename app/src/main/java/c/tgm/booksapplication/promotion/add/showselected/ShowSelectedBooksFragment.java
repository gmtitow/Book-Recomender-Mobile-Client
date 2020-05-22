package c.tgm.booksapplication.promotion.add.showselected;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.ArrayList;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.databinding.FragmentPromotionSelectBooksBinding;
import c.tgm.booksapplication.databinding.FragmentShowSelectedBooksBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.APaginationFragment;
import c.tgm.booksapplication.promotion.add.selectbooks.IBookDescriptionRemember;
import c.tgm.booksapplication.promotion.add.selectbooks.adapter.PromotionSelectBooksAdapter;
import c.tgm.booksapplication.promotion.add.showselected.adapter.ShowSelectedBooksAdapter;

public class ShowSelectedBooksFragment extends AbstractFragment
        implements IRemover, ShowSelectedBooksView {
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_DESCRIPTIONS = "book_descriptions";

    FragmentShowSelectedBooksBinding mBinding;

    ShowSelectedBooksAdapter mAdapter;

    @InjectPresenter(type = PresenterType.LOCAL)
    ShowSelectedBooksPresenter mPresenter;

    @Override
    public ShowSelectedBooksPresenter getPresenter() {
        return mPresenter;
    }

    protected void initializeAdapter() {
        mAdapter = new ShowSelectedBooksAdapter(getPresenter().getModel().getDescriptions(), getContext(), this);
    }

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
    }

    protected int getLayoutResourceId() {
        return R.layout.fragment_show_selected_books;
    }

    public RecyclerView getRecyclerView() {
        return mBinding.recyclerView;
    }

    @Override
    public boolean withEventBus() {
        return false;
    }

    protected void setupViews() {
        initializeAdapter();

        getRecyclerView().setAdapter(mAdapter);
        getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));

        //
        mBinding.buttonBack.setOnClickListener(v -> {
            getPresenter().exit();
        });

        mBinding.buttonShowSelected.setOnClickListener(v -> {
            getPresenter().complete();
        });
    }

    @Override
    public void delete(int id) {

    }

    protected void beforeSetupView() {
        if (getArguments() != null && getArguments().containsKey(KEY_REMEMBER)) {
            getPresenter().setDescriptionRemember(
                    (IBookDescriptionRemember) getArguments().getSerializable(KEY_REMEMBER));
        }

        if (getArguments() != null && getArguments().containsKey(KEY_DESCRIPTIONS)) {
            getPresenter().setBookDescriptions(
                    getArguments().getParcelableArrayList(KEY_DESCRIPTIONS));
        }
    }

    public static Fragment getInstance(IBookDescriptionRemember remember,
                                       ArrayList<BookDescription> descriptions) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_REMEMBER, remember);
        bundle.putParcelableArrayList(KEY_DESCRIPTIONS, descriptions);
        Fragment fragment = new ShowSelectedBooksFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public String getTitle() {
        return "Выбранные книги";
    }
}

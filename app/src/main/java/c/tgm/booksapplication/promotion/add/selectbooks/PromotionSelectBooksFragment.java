package c.tgm.booksapplication.promotion.add.selectbooks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.CustomAppNavigator;
import c.tgm.booksapplication.databinding.FragmentPromotionSelectBooksBinding;
import c.tgm.booksapplication.filters.FilterFragment;
import c.tgm.booksapplication.filters.FilterViewOptions;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;
import c.tgm.booksapplication.pagination.APaginationFragment;
import c.tgm.booksapplication.promotion.add.selectbooks.adapter.PromotionSelectBooksAdapter;
import c.tgm.booksapplication.promotion.list.PromotionListFragment;
import ru.terrakok.cicerone.Navigator;

public class PromotionSelectBooksFragment extends APaginationFragment<Book, PromotionSelectBooksModel,
        PromotionSelectBooksView, PromotionSelectBooksPresenter, FragmentPromotionSelectBooksBinding, PromotionSelectBooksAdapter>
        implements INavigator, PromotionSelectBooksView {
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_DESCRIPTIONS = "book_descriptions";

    @InjectPresenter(type = PresenterType.LOCAL)
    PromotionSelectBooksPresenter mPresenter;

    @Override
    public PromotionSelectBooksPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    protected void initializeAdapter() {
        mAdapter = new PromotionSelectBooksAdapter(getPresenter().getObjects(), getActivity(), this, getPresenter().getPageSize(), this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.filterLayout, FilterFragment.getInstance(getPresenter(),
                new FilterViewOptions(false,false,true))).commit();;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_promotion_select_books;
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
    protected void setupViews() {
        super.setupViews();

        mBinding.buttonShowSelected.setOnClickListener(v -> {
            getPresenter().showSelectedBooks();
        });
    }

    @Override
    public void goById(Object object) {
        getPresenter().addBook((Book) object);
    }

    @Override
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.promotion_select_book_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_filters:
                getPresenter().addDescription();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Fragment getInstance(IBookDescriptionRemember remember,
                                       ArrayList<BookDescription> descriptions) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_REMEMBER, remember);
        bundle.putParcelableArrayList(KEY_DESCRIPTIONS, descriptions);
        Fragment fragment = new PromotionSelectBooksFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public String getTitle() {
        return "Выберете книги";
    }
}

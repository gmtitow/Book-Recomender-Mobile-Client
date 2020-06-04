package c.tgm.booksapplication.filters;

import android.animation.Animator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.books.list.BookListPresenter;
import c.tgm.booksapplication.databinding.FragmentFiltersBinding;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.Genre;

public class FilterFragment extends AbstractFragment implements FilterView {

    private static final String KEY_HANDLER = "handler";
    private static final String KEY_VIEW_OPTIONS = "view_options";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_QUERY = "query";

    FragmentFiltersBinding mBinding;

    @InjectPresenter(type = PresenterType.GLOBAL)
    FilterPresenter mPresenter;

    boolean filtersVisible = true;

    @Override
    public FilterPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public boolean withEventBus() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_filters, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        beforeSetupView();

        FilterViewOptions options;
        if (getArguments() != null && getArguments().containsKey(KEY_VIEW_OPTIONS)) {
            options = (FilterViewOptions) getArguments().getSerializable(KEY_VIEW_OPTIONS);
        } else
            options = new FilterViewOptions();

        if (options.getHandler()!=null) {
            switch(options.getHandler()) {
                case "BookListPresenter":{
                    getPresenter().setHandler(BookListPresenter.getFilterHandler());
                }
            }
        }

        setupViews(options);
    }

    public void setupViews(FilterViewOptions options) {

        if (!options.isAuthorGone())
            mBinding.buttonAuthor.setOnClickListener(v -> {
                getPresenter().onAuthorPressed();
            });
        else
            mBinding.buttonAuthor.setVisibility(View.GONE);


        if (!options.isGenreGone())
            mBinding.buttonGenre.setOnClickListener(v -> {
                getPresenter().onGenrePressed();
            });
        else
            mBinding.buttonGenre.setVisibility(View.GONE);

        if (!options.isBookListsGone())
            mBinding.buttonList.setOnClickListener(v -> {
                getPresenter().onListPressed();
            });
        else
            mBinding.buttonList.setVisibility(View.GONE);

        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().onQueryChanged(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                getPresenter().onQueryChanged(newText);
                return false;
            }
        });

        mBinding.filterLayout.setVisibility(View.GONE);
        mBinding.buttonSwitch.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        filtersVisible = false;

        mBinding.buttonSwitch.setOnClickListener(v -> {
            TransitionManager.beginDelayedTransition(mBinding.filterLayout);
            if (filtersVisible) {
//                animatorHide.start();
                mBinding.filterLayout.setVisibility(View.GONE);
                mBinding.buttonSwitch.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            } else {
//                animatorShow.start();
                mBinding.filterLayout.setVisibility(View.VISIBLE);
                mBinding.buttonSwitch.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
            }

            TransitionManager.endTransitions(mBinding.filterLayout);
            filtersVisible = !filtersVisible;
        });
    }

    protected void beforeSetupView() {
        if (getArguments() != null && getArguments().containsKey(KEY_HANDLER)) {
            getPresenter().setHandler(
                    (IFIlterHandler) getArguments().getSerializable(KEY_HANDLER));
        }

        if (getArguments() != null && getArguments().containsKey(KEY_GENRE)) {
            getPresenter().setGenre(
                    (Genre) getArguments().getSerializable(KEY_GENRE));
        }

        if (getArguments() != null && getArguments().containsKey(KEY_AUTHOR)) {
            getPresenter().setAuthor(
                    (Author) getArguments().getSerializable(KEY_AUTHOR));
        }

        if (getArguments() != null && getArguments().containsKey(KEY_QUERY)) {
            getPresenter().setQuery(getArguments().getString(KEY_QUERY));
        }
    }

    public static Fragment getInstance(IFIlterHandler handler,
                                       Author author, Genre genre, String query) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_HANDLER, handler);
        bundle.putSerializable(KEY_GENRE, genre);
        bundle.putSerializable(KEY_AUTHOR, author);
        bundle.putString(KEY_QUERY, query);
        Fragment fragment = new FilterFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    public static Fragment getInstance(IFIlterHandler handler) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_HANDLER, handler);
        Fragment fragment = new FilterFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    public static Fragment getInstance(IFIlterHandler handler, FilterViewOptions options) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_HANDLER, handler);
        bundle.putSerializable(KEY_VIEW_OPTIONS, options);
        Fragment fragment = new FilterFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    public static Fragment getInstance(FilterViewOptions options) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_VIEW_OPTIONS, options);
        Fragment fragment = new FilterFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void changeAuthor(Author author) {
        if (author == null)
            mBinding.buttonAuthor.setText("Выбрать автора...");
        else
            mBinding.buttonAuthor.setText(author.getFullName());
    }

    @Override
    public void changeGenre(Genre genre) {
        if (genre == null)
            mBinding.buttonGenre.setText("Выбрать жанр...");
        else
            mBinding.buttonGenre.setText(genre.getGenreName());
    }

    @Override
    public void changeList(BookList list) {
        if (list == null)
            mBinding.buttonList.setText("Выбрать список...");
        else
            mBinding.buttonList.setText(list.getListName());
    }

    @Override
    public boolean isChangeTitle() {
        return false;
    }
}

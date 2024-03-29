package c.tgm.booksapplication.books.recommends;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.ArrayList;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.books.list.BookListFragment;
import c.tgm.booksapplication.books.list.BookListPresenter;
import c.tgm.booksapplication.books.list.adapter.BookListAdapter;
import c.tgm.booksapplication.databinding.FragmentBookListBinding;
import c.tgm.booksapplication.databinding.FragmentRecommendListBinding;
import c.tgm.booksapplication.filters.FilterFragment;
import c.tgm.booksapplication.filters.FilterViewOptions;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.Genre;

public class BookRecommendsFragment extends BookListFragment {

    FragmentBookListBinding mBinding;
    @InjectPresenter(type = PresenterType.LOCAL)
    BookRecommendsPresenter mPresenter;
    
    @Override
    public String getTitle() {
        return "Рекомендации";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public BookRecommendsPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.filterLayout, FilterFragment.getInstance(getPresenter(),
                new FilterViewOptions(true,false,false))).commit();

        if(getArguments()!= null && getArguments().containsKey(AUTHOR_ID)){
            getPresenter().rememberAuthorId(getArguments().getInt(AUTHOR_ID));
        }

        setupViews();

        if (getPresenter().getBooks().size()==0)
            getPresenter().updateBookList(false);
    }

    @Override
    public void setupViews() {
        mAdapter = new BookListAdapter(getActivity(),getPresenter().getBooks(),this,this,getPresenter().getPageSize());

        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.recommends_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_update: {
                getPresenter().updateRecommendList();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void loadNext() {
        getPresenter().getNextPage();
    }

    public static Fragment getInstance() {
        BookRecommendsFragment fragment = new BookRecommendsFragment();
        return fragment;
    }
    
    public static Fragment getInstance(int authorId) {
        BookRecommendsFragment fragment = new BookRecommendsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AUTHOR_ID,authorId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void updateList(ArrayList<Book> books) {
        if(books.size() == 0) {
            mBinding.textEmptyList.setVisibility(View.VISIBLE);
        } else {
            mBinding.textEmptyList.setVisibility(View.GONE);
        }
        mAdapter.setBooks(books);
    }
}

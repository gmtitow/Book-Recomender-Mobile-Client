package c.tgm.booksapplication.authors.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.ArrayList;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.authors.list.adapter.AuthorListAdapter;
import c.tgm.booksapplication.books.list.adapter.BookListAdapter;
import c.tgm.booksapplication.books.list.adapter.IListLoader;
import c.tgm.booksapplication.books.list.adapter.INavigator;
import c.tgm.booksapplication.databinding.FragmentBookListBinding;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Genre;

public class AuthorListFragment extends AbstractFragment implements AuthorListView, IListLoader, INavigator {
    
    public static final String AUTHOR_ID = "AuthorListFragment.author_id";
    
    @InjectPresenter(type = PresenterType.LOCAL)
    AuthorListPresenter mPresenter;
    
    FragmentBookListBinding mBinding;
    
    AuthorListAdapter mAdapter;
    ArrayAdapter mGenreAdapter;
    
    @Override
    public String getTitle() {
        return "Авторы";
    }
    
    @Override
    public AuthorListPresenter getPresenter() {
        return mPresenter;
    }
    
    @Override
    public boolean withEventBus() {
        return false;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_list, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        setupViews();
        
        getPresenter().updateAuthorList(mBinding.searchView.getQuery().toString(), false);
    }
    
    public void setupViews() {
        mAdapter = new AuthorListAdapter(getContext(),new ArrayList<Author>(),
                this,this,getPresenter().getPageSize());
    
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().updateAuthorList(query,true);
                return false;
            }
    
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    
//        mGenreAdapter = new GenreAdapter(getContext(),getPresenter().getGenres());
    
        mGenreAdapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, getPresenter().getGenres());
    
        
        mBinding.spinner.setAdapter(mGenreAdapter);
        mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                getPresenter().setGenre(((Genre)adapterView.getSelectedItem()).getGenreId(),mBinding.searchView.getQuery().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
    }
    
    public static Fragment getInstance() {
        AuthorListFragment fragment = new AuthorListFragment();
        return fragment;
    }
    
    public static Fragment getInstance(int authorId) {
        AuthorListFragment fragment = new AuthorListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AUTHOR_ID,authorId);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void updateList(ArrayList<Author> authors) {
        if(authors.size() == 0) {
            mBinding.textEmptyList.setVisibility(View.VISIBLE);
        } else {
            mBinding.textEmptyList.setVisibility(View.GONE);
        }
        mAdapter.setAuthors(authors);
    }
    
    @Override
    public void loadNext() {
        getPresenter().getNextPage(mBinding.searchView.getQuery().toString());
    }
    
    @Override
    public void updateGenres(ArrayList<Genre> genres) {
//        mGenreAdapter.addAll(genres);
    }
    
    @Override
    public void showBookInfo(int author_id) {
        getPresenter().openBooks(author_id);
    }
}

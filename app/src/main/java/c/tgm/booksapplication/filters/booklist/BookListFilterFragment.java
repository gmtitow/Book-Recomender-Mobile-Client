package c.tgm.booksapplication.filters.booklist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.ArrayList;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.FragmentAuthorFilterBinding;
import c.tgm.booksapplication.filters.booklist.adapter.BookListFilterAdapter;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.BookList;

public class BookListFilterFragment extends AbstractFragment implements BookListFilterView, INavigator {

    private static final String KEY_HANDLER = "handler";
    
    @InjectPresenter(type = PresenterType.LOCAL)
    BookListFilterPresenter mPresenter;
    
    FragmentAuthorFilterBinding mBinding;
    
    BookListFilterAdapter mAdapter;
    
    @Override
    public String getTitle() {
        return "Списки книг";
    }
    
    @Override
    public BookListFilterPresenter getPresenter() {
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
        
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_author_filter, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        setupViews();

        if (getArguments() != null && getArguments().containsKey(KEY_HANDLER)) {
            getPresenter().setHandler(
                    (IBookListChanger) getArguments().getSerializable(KEY_HANDLER));
        }
        
        getPresenter().updateAuthorList(mBinding.searchView.getQuery().toString(), false);
    }
    
    public void setupViews() {
        mAdapter = new BookListFilterAdapter(new ArrayList<BookList>(), getActivity(),this);
    
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
    }
    
    public static Fragment getInstance() {
        BookListFilterFragment fragment = new BookListFilterFragment();
        return fragment;
    }
    
    public static Fragment getInstance(IBookListChanger changer) {
        BookListFilterFragment fragment = new BookListFilterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_HANDLER,changer);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void updateList(ArrayList<BookList> lists) {
        if(lists.size() == 0) {
            mBinding.textEmptyList.setVisibility(View.VISIBLE);
        } else {
            mBinding.textEmptyList.setVisibility(View.GONE);
        }
        mAdapter.setObjects(lists);
    }
    
    @Override
    public void goById(Object list) {
        getPresenter().onSelectList((BookList) list);
    }
}

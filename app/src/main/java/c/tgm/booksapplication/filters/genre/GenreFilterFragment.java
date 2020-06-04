package c.tgm.booksapplication.filters.genre;

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
import c.tgm.booksapplication.databinding.FragmentGenreFilterBinding;
import c.tgm.booksapplication.filters.genre.adapter.GenreFilterListAdapter;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

public class GenreFilterFragment extends AbstractFragment implements GenreFilterView, INavigator {

    private static final String KEY_HANDLER = "handler";
    
    @InjectPresenter(type = PresenterType.LOCAL)
    GenreFilterPresenter mPresenter;
    
    FragmentGenreFilterBinding mBinding;
    
    GenreFilterListAdapter mAdapter;
    
    @Override
    public String getTitle() {
        return "Жанры";
    }
    
    @Override
    public GenreFilterPresenter getPresenter() {
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
        
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_genre_filter, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        setupViews();

        if (getArguments() != null && getArguments().containsKey(KEY_HANDLER)) {
            getPresenter().setHandler(
                    (IGenreChanger) getArguments().getSerializable(KEY_HANDLER));
        }
        
        getPresenter().updateGenreList();
    }
    
    public void setupViews() {
        mAdapter = new GenreFilterListAdapter(new ArrayList<>(), getActivity(), this);
    
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    
    public static Fragment getInstance() {
        GenreFilterFragment fragment = new GenreFilterFragment();
        return fragment;
    }
    
    public static Fragment getInstance(IGenreChanger changer) {
        GenreFilterFragment fragment = new GenreFilterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_HANDLER,changer);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void updateList(ArrayList<Genre> genres) {
        if(genres.size() == 0) {
            mBinding.textEmptyList.setVisibility(View.VISIBLE);
        } else {
            mBinding.textEmptyList.setVisibility(View.GONE);
        }
        mAdapter.setObjects(genres);
    }
    
    @Override
    public void goById(Object genre) {
        getPresenter().onSelectGenre((Genre)genre);
    }
}

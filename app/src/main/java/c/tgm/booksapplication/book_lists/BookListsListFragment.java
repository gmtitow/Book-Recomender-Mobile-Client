package c.tgm.booksapplication.book_lists;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.book_lists.adapter.BookListsListAdapter;
import c.tgm.booksapplication.books.list.BookListPresenter;
import c.tgm.booksapplication.books.list.BookListView;
import c.tgm.booksapplication.books.list.adapter.BookListAdapter;
import c.tgm.booksapplication.interfaces.IListLoader;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.databinding.TemplateListLayoutBinding;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.Genre;

public class BookListsListFragment extends AbstractFragment implements
        BookListsListView, IListLoader, INavigator, DialogAddListFragment.DialogCommunicator, IRemover,
        DialogAlertDeleteFragment.WarningCommunicator {
    
   public static final int REQUEST_CODE = 1;
    
    @InjectPresenter(type = PresenterType.LOCAL)
    BookListsListPresenter mPresenter;
    
    TemplateListLayoutBinding mBinding;
    
    BookListsListAdapter mAdapter;
    
    @Override
    public String getTitle() {
        return "Списки книг";
    }
    
    @Override
    public BookListsListPresenter getPresenter() {
        return mPresenter;
    }
    
    @Override
    public boolean withEventBus() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.template_list_layout, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        setupViews();
        
        if (getPresenter().getLists().size()==0)
            getPresenter().updateListsList();
    }
    
    public void setupViews() {
        mAdapter = new BookListsListAdapter(getContext(),getPresenter().getLists(),
                this,this, this);
    
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    
    public static Fragment getInstance() {
        return new BookListsListFragment();
    }

    @Override
    public void updateList(ArrayList<BookList> lists) {
        if(lists.size() == 0) {
            mBinding.textEmptyList.setVisibility(View.VISIBLE);
        } else {
            mBinding.textEmptyList.setVisibility(View.GONE);
        }
        mAdapter.setLists(lists);
    }
    
    @Override
    public void loadNext() {
//        getPresenter().getNextPage(mBinding.searchView.getQuery().toString());
        getPresenter().updateListsList();
    }
    
    @Override
    public void goById(int book_id) {
//        getPresenter().openBookInfo(book_id);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.book_lists_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Немного грязи
        if (!mAdapter.isEnable())
            return true;

        switch (item.getItemId()) {
            case R.id.action_add:
                DialogAddListFragment dialogAdd = new DialogAddListFragment();
                dialogAdd.setTargetFragment(this, REQUEST_CODE);
                dialogAdd.show(getFragmentManager(),"dialog_add_list");
                return true;
            case R.id.action_delete:{
                mAdapter.changeDelete();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void createList(String listName) {
        mPresenter.createList(listName);
    }

    @Override
    public void delete(int id) {
        DialogAlertDeleteFragment dialog = new DialogAlertDeleteFragment();
        dialog.setTargetFragment(this,REQUEST_CODE);
        dialog.show(getFragmentManager(),"tag");
        mPresenter.setCurrentListId(id);
    }

    @Override
    public void progressChanged(boolean progress) {
        if (progress) {
            mAdapter.setEnable(false);
        } else {
            mAdapter.setEnable(true);
        }
    }

    @Override
    public void delete() {
        mPresenter.deleteList();
    }
}

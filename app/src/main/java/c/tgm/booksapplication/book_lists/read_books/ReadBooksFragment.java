package c.tgm.booksapplication.book_lists.read_books;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.book_lists.DialogAddListFragment;
import c.tgm.booksapplication.book_lists.DialogAlertDeleteFragment;
import c.tgm.booksapplication.book_lists.read_books.adapter.ReadBooksAdapter;
import c.tgm.booksapplication.databinding.TemplateListLayoutBinding;
import c.tgm.booksapplication.interfaces.IRemover;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.pagination.APaginationFragment;
import c.tgm.booksapplication.pagination.IPaginationView;

public class ReadBooksFragment extends APaginationFragment<
        ReadBook,ReadBooksModel,ReadBooksView,ReadBooksPresenter, TemplateListLayoutBinding, ReadBooksAdapter
        > implements ReadBooksView, IRemover, DialogAlertDeleteFragment.WarningCommunicator {

    @InjectPresenter(type = PresenterType.LOCAL)
    ReadBooksPresenter mPresenter;

    @Override
    public ReadBooksPresenter getPresenter() {
        return mPresenter;
    }

    private static String LIST_ID_KEY = "list_id";

    @Override
    protected void beforeSetupView() {
        super.beforeSetupView();

        if (getArguments() != null && getArguments().containsKey(LIST_ID_KEY)) {
            getPresenter().setListId(getArguments().getInt(LIST_ID_KEY));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void afterSetupView() {
        super.afterSetupView();
    }

    @Override
    public String getTitle() {
        return "Прочитанные книги";
    }

    @Override
    protected void initializeAdapter() {
        this.mAdapter = new ReadBooksAdapter(getPresenter().getObjects(),getActivity(),this,getPresenter().getPageSize(), this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.template_list_layout;
    }

    @Override
    public boolean withEventBus() {
        return false;
    }

    public static ReadBooksFragment getInstance(int listId) {
        ReadBooksFragment fragment = new ReadBooksFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LIST_ID_KEY,listId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mBinding.recyclerView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.read_books_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:{
                mAdapter.changeDelete();
                if (mAdapter.isDeleteVisible())
                    item.setIcon(R.drawable.ic_close);
                else
                    item.setIcon(R.drawable.ic_delete);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void delete(int id) {
        DialogAlertDeleteFragment dialog = new DialogAlertDeleteFragment();
        dialog.setTargetFragment(this,0);
        dialog.show(getFragmentManager(),"tag");
        mPresenter.setCurrentBookId(id);
    }

    @Override
    public void delete() {
        mPresenter.deleteBookFromList();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}

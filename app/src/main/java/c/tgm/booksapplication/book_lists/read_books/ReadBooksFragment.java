package c.tgm.booksapplication.book_lists.read_books;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.book_lists.read_books.adapter.ReadBooksAdapter;
import c.tgm.booksapplication.databinding.TemplateListLayoutBinding;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.pagination.APaginationFragment;
import c.tgm.booksapplication.pagination.IPaginationView;

public class ReadBooksFragment extends APaginationFragment<
        ReadBook,ReadBooksModel,ReadBooksPresenter, TemplateListLayoutBinding, ReadBooksAdapter
        > implements IPaginationView<ReadBook> {

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
    public void onStart() {
        super.onStart();
        mPresenter.getNewObjects();
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
        this.mAdapter = new ReadBooksAdapter(getPresenter().getObjects(),getContext(),this,getPresenter().getPageSize());
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
}

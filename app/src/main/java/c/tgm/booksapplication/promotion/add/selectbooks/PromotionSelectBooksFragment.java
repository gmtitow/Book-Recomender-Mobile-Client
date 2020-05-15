package c.tgm.booksapplication.promotion.add.selectbooks;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.FragmentPromotionSelectBooksBinding;
import c.tgm.booksapplication.interfaces.INavigator;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;
import c.tgm.booksapplication.pagination.APaginationFragment;
import c.tgm.booksapplication.promotion.add.selectbooks.adapter.PromotionSelectBooksAdapter;
import c.tgm.booksapplication.promotion.list.PromotionListFragment;

public class PromotionSelectBooksFragment extends APaginationFragment<Book,PromotionSelectBooksModel,
        PromotionSelectBooksView, PromotionSelectBooksPresenter, FragmentPromotionSelectBooksBinding, PromotionSelectBooksAdapter>
    implements INavigator
{

    FragmentPromotionSelectBooksBinding mBinding;

    @InjectPresenter(type = PresenterType.LOCAL)
    PromotionSelectBooksPresenter mPresenter;

    @Override
    public PromotionSelectBooksPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initializeAdapter() {
        mAdapter = new PromotionSelectBooksAdapter(getPresenter().getObjects(),getContext(),this,getPresenter().getPageSize(),this);
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
    public void goById(int id) {

    }


    public static Fragment getInstance(IBookDescriptionRemember remember,
                                       List<PromotionAddRequest.BookDescription> descriptions) {
        return new PromotionListFragment();
    }
}

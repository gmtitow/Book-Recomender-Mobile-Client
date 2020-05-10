package c.tgm.booksapplication.books.item;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.book_lists.DialogAddToReadFragment;
import c.tgm.booksapplication.books.item.adapter.ReviewListAdapter;
import c.tgm.booksapplication.databinding.FragmentBookItemBinding;
import c.tgm.booksapplication.events_for_bus.ReviewChanged;
import c.tgm.booksapplication.events_for_bus.ReviewCreated;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.review.ReviewListener;

public class BookItemFragment extends AbstractFragment implements BookItemView, ReviewListener, DialogAddToReadFragment.Communicator {
    
    public static final String BOOK_INFO = "BookItemFragment.author_id";
    
    @InjectPresenter(type = PresenterType.LOCAL)
    BookItemPresenter mPresenter;
    
    FragmentBookItemBinding mBinding;

    private BookItemFragment thisFragment = this;
    
    ReviewListAdapter mReviewAdapter;
    
    @Override
    public String getTitle() {
        return "Подробнее";
    }
    
    @Override
    public BookItemPresenter getPresenter() {
        return mPresenter;
    }
    
    @Override
    public boolean withEventBus() {
        return true;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_item, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        BookInfo bookInfo = (BookInfo) getArguments().getSerializable(BOOK_INFO);
        getPresenter().saveInfo(bookInfo);
        setupViews(bookInfo);
    
        if (BookApplication.INSTANCE.getDataStore().isAuthorized() && !bookInfo.getReviewExists()){
            this.showCreateReviewBtn();
        }
    }
    
    public void setupViews(final BookInfo book) {
        if (book.getReviews().getData().size() == 0 || book.getReviews().getData().get(0).getReviewId() != -1) {
            Review empty = new Review();
            empty.setReviewId(-1);
            book.getReviews().getData().add(0, empty);
        }

        View.OnClickListener addToReadListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddToReadFragment dialog = DialogAddToReadFragment.getInstance(getPresenter().getBookLists());

                dialog.setTargetFragment(thisFragment,0);

                dialog.show(getFragmentManager(),"tag");
            }
        };

        List list = getPresenter().getBookLists();
        
        mReviewAdapter = new ReviewListAdapter(getContext(), book.getReviews().getData(),
                BookApplication.INSTANCE.getDataStore().getUserId(),this, book,addToReadListener,list.size()!=0);
        
        mBinding.listReviews.setAdapter(mReviewAdapter);
        
        mBinding.btnAddReview.setVisibility(View.GONE);
        
        mBinding.btnAddReview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPresenter().navigateTo(new Screens.ReviewScreens(Screens.ReviewScreens.ADD_REVIEW_SCREEN, book.getBookId()));
                    }
                }
        );
    }
    
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        
        inflater.inflate(R.menu.book_item_menu, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_download:
                getPresenter().downloadBook(getContext());
                
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public static Fragment getInstance(BookInfo bookInfo) {
        BookItemFragment fragment = new BookItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BOOK_INFO, bookInfo);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void addReview(Review review) {
        mReviewAdapter.add(review);
    }
    
    @Override
    public void showCreateReviewBtn() {
        mBinding.btnAddReview.setVisibility(View.VISIBLE);
    }
    
    public void hideCreateReviewBtn() {
        mBinding.btnAddReview.setVisibility(View.GONE);
    }
    
    @Override
    public void goToChangeReview(Review review) {
        getPresenter().navigateTo(new Screens.ReviewScreens(Screens.ReviewScreens.ADD_REVIEW_SCREEN,review.getBookId(),review));
    }
    
    @Override
    public void deleteReview(int reviewId) {
        getPresenter().deleteReview(reviewId);
    }
    
    @Override
    public void deleteReviewFromList(int reviewId) {
        for (int i = 0; i < mReviewAdapter.getCount(); i++) {
            if (mReviewAdapter.getItem(i).getReviewId()==reviewId) {
                mReviewAdapter.remove(mReviewAdapter.getItem(i));
                mReviewAdapter.getBookInfo().getReviews().getPagination().decreaseTotal();
                mReviewAdapter.getBookInfo().setReviewExists(false);
            }
        }
        
        if (BookApplication.INSTANCE.getDataStore().isAuthorized())
            showCreateReviewBtn();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReviewCreatedEvent(ReviewCreated event) {
        mReviewAdapter.insert(event.getReview(),1);
        mReviewAdapter.getBookInfo().getReviews().getPagination().increaseTotal();
        mReviewAdapter.getBookInfo().setReviewExists(true);
        mReviewAdapter.notifyDataSetChanged();
        hideCreateReviewBtn();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReviewChangedEvent(ReviewChanged event) {
        for (int i = 0; i < mReviewAdapter.getCount(); i++) {
            if (mReviewAdapter.getItem(i).getReviewId()==event.getReview().getReviewId()) {
                Review oldReview = mReviewAdapter.getItem(i);
                oldReview.setRating(event.getReview().getRating());
                oldReview.setReviewText(event.getReview().getReviewText());
                mReviewAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void add(Long listId, int rating) {
        getPresenter().addBookInList(listId,rating);
    }

    @Override
    public void bookWasAdded() {
        mReviewAdapter.setShowAddToRead(getPresenter().getBookLists().size()!=0);

        Toast.makeText(getContext(),"Книга была успешно добавлена",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }
}

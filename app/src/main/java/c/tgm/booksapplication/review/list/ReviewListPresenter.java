package c.tgm.booksapplication.review.list;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.books.BookRepository;
import c.tgm.booksapplication.books.BookRepositoryImpl;
import c.tgm.booksapplication.books.list.BookListView;
import c.tgm.booksapplication.books.list.BookPresenterRepo;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;
import c.tgm.booksapplication.repositories.ReviewPresenterRepo;
import c.tgm.booksapplication.repositories.ReviewRepository;
import c.tgm.booksapplication.repositories.ReviewRepositoryImpl;

public class ReviewListPresenter extends NavigatorPresenter<ReviewListView>
        implements ReviewPresenterRepo, BookPresenterRepo {
    
    protected ReviewListModel mModel;
    protected ReviewRepository mRepository;
    protected BookRepository mBookRepository;
    
    protected boolean lastBookRepository;
    
    public ReviewListPresenter() {
        mModel = new ReviewListModel();
        mRepository = new ReviewRepositoryImpl(this);
        mBookRepository = new BookRepositoryImpl(this);
        lastBookRepository = false;
    }
    
    public void updateReviewList(boolean rewrite) {
        if(mModel.isLoading())
            mRepository.cancelRequest();
        
        if (rewrite){
            mModel.setCurPage(1);
        }
        
        mModel.setLoading(true);
        getReviews(rewrite);
    }
    
    @Override
    public void onAddReview(Review review) {
    }
    
    @Override
    public void onEditReview(Review review) {
    }
    
    @Override
    public void onDeleteReview(int reviewId) {
        getView().deleteReviewFromList(reviewId);
    }
    
    public void deleteReview(int reviewId) {
        mRepository.deleteReview(reviewId);
    }
    
    protected void getReviews(boolean rewrite) {
        mRepository.getUsersReviews(mModel.getCurPage(),mModel.getPageSize(), rewrite);
    }
    
    public void getNextPage() {
        mModel.increasePage();
        updateReviewList(false);
    }
    
    public int getPageSize() {
        return mModel.getPageSize();
    }
    
    @Override
    public void onGetReviewList(ArrayList<ReviewWithBook> reviews, boolean rewrite) {
        if (rewrite) {
            mModel.setCurReviews(reviews);
        } else {
            mModel.addReviews(reviews);
        }
        if (getView()!=null)
            getView().updateList(mModel.getCurReviews());
    }
    
    @Override
    public void onError(String error) {
        mRepository.retry();
    }
    
    @Override
    public void rememberBooks(List<Book> books, boolean rewrite) {
    }
    
    @Override
    public void rememberBook(BookInfo book) {
        lastBookRepository = false;
        navigateTo(new Screens.BookScreens(Screens.BookScreens.BOOK_INFO_SCREEN, book));
    }
    
    public void openBookInfo(int book_id) {
        lastBookRepository = true;
        mBookRepository.getBookInfo(book_id);
    }
}

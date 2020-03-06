package c.tgm.booksapplication.books.item;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.AbstractPresenter;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.books.BookRepository;
import c.tgm.booksapplication.books.BookRepositoryImpl;
import c.tgm.booksapplication.books.list.BookListModel;
import c.tgm.booksapplication.books.list.BookPresenterRepo;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;
import c.tgm.booksapplication.repositories.ReviewPresenterRepo;
import c.tgm.booksapplication.repositories.ReviewRepository;
import c.tgm.booksapplication.repositories.ReviewRepositoryImpl;

public class BookItemPresenter extends NavigatorPresenter<BookItemView> implements ReviewPresenterRepo, BookPresenterRepo {
    
    private BookInfo mBookInfo;
    protected ReviewRepository mReviewRepository;
    protected BookRepository mBookRepository;
    
    private boolean bookRepository = false;
    public BookItemPresenter() {
        mReviewRepository = new ReviewRepositoryImpl(this);
        mBookRepository = new BookRepositoryImpl(this);
    }
    
    public void deleteReview(int reviewId) {
        bookRepository = false;
        mReviewRepository.deleteReview(reviewId);
    }
    
    public void downloadBook(Context context) {
        bookRepository = true;
//        mBookRepository.downloadFile(mBookInfo.getName(),mBookInfo.getBookId());
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(UserRequest.URL_SERVER+"/book/download/file/txt?book_id="+mBookInfo.getBookId()));
        context.startActivity(i);
    };
    
    public void saveInfo(BookInfo info) {
        mBookInfo = info;
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
    
    @Override
    public void onGetReviewList(ArrayList<ReviewWithBook> reviews, boolean rewrite) {
    }
    
    @Override
    public void onError(String error) {
        if (bookRepository) {
            mBookRepository.retry();
        } else {
            mReviewRepository.retry();
        }
    }
    
    @Override
    public void rememberBooks(List<Book> books, boolean rewrite) {
    }
    
    @Override
    public void rememberBook(BookInfo book) {
    }
}

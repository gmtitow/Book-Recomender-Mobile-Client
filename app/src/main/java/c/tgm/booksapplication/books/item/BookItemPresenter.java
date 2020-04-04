package c.tgm.booksapplication.books.item;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.AbstractPresenter;
import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.books.BookRepository;
import c.tgm.booksapplication.books.BookRepositoryImpl;
import c.tgm.booksapplication.books.list.BookListModel;
import c.tgm.booksapplication.books.list.BookPresenterRepo;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.BookListDao;
import c.tgm.booksapplication.models.data.GenreDao;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.models.data.ReadBookWithList;
import c.tgm.booksapplication.models.data.ReadBookWithListDao;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;
import c.tgm.booksapplication.repositories.RepositoryCall;
import c.tgm.booksapplication.repositories.ReviewPresenterRepo;
import c.tgm.booksapplication.repositories.ReviewRepository;
import c.tgm.booksapplication.repositories.ReviewRepositoryImpl;
import c.tgm.booksapplication.repositories.book_list.BookListRepo;
import c.tgm.booksapplication.repositories.book_list.BookListRepository;
import c.tgm.booksapplication.repositories.book_list.BookListRepositoryImpl;
import retrofit2.Call;

public class BookItemPresenter extends NavigatorPresenter<BookItemView>
        implements ReviewPresenterRepo, BookPresenterRepo, BookListRepo {
    
    private BookInfo mBookInfo;
    protected ReviewRepository mReviewRepository;
    protected BookRepository mBookRepository;
    protected BookListRepository mBookListRepository;
    
    private boolean bookRepository = false;
    public BookItemPresenter() {
        mReviewRepository = new ReviewRepositoryImpl(this);
        mBookRepository = new BookRepositoryImpl(this);
        mBookListRepository = new BookListRepositoryImpl(this);
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

    public List<BookList> getBookLists() {
        BookListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();
//        QueryBuilder<BookList> builder = dao.queryBuilder();

        String query = "SELECT DISTINCT T._id FROM BOOK_LIST T LEFT JOIN READ_BOOK_WITH_LIST ON T._id = READ_BOOK_WITH_LIST.LIST_ID " +
                "where READ_BOOK_WITH_LIST._ID != " + mBookInfo.getBookId().toString();


        List<BookList> list = dao.queryRaw(" WHERE " + BookListDao.Properties._id.name+" IN (" + query + ")");

        return list;
    }

    public void addBookInList(Long listId, int rating) {
        mBookListRepository.addBookInList(listId.intValue(),mBookInfo.getBookId(),rating);
    }

    @Override
    public void onAddBookInList(ReadBookWithList book) {
        ReadBookWithListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getReadBookWithListDao();
        dao.insertOrReplace(book);
        getView().bookWasAdded();
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
//        call.call();
    }
}

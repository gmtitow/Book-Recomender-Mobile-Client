package c.tgm.booksapplication.books.list;

import com.arellomobile.mvp.InjectViewState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.books.BookPresenterRepo;
import c.tgm.booksapplication.books.BookRepository;
import c.tgm.booksapplication.books.BookRepositoryImpl;
import c.tgm.booksapplication.filters.IFIlterHandler;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;
import c.tgm.booksapplication.repositories.RepositoryCall;


public class BookListPresenter extends NavigatorPresenter<BookListView> implements BookPresenterRepo, IFIlterHandler {
    
    protected BookListModel mModel;
    protected BookRepository mRepository;
    protected FilterHandler mFilterHandler;

    protected static IFIlterHandler instance;

    protected boolean rewrite;
    
    public BookListPresenter() {
        mModel = new BookListModel();
        mRepository = new BookRepositoryImpl(this);
        mFilterHandler = new FilterHandler(mModel);
        instance = this;
    }

    public static IFIlterHandler getFilterHandler() {
        return instance;
    }

    public void rememberAuthorId(int authorId) {
        mModel.setAuthorId(authorId);
    }
    
    public void updateBookList(boolean rewrite) {
        if(mModel.isLoading())
            mRepository.cancelRequest();
        
        if (rewrite){
            mModel.setCurPage(1);
        }
        
        mModel.setLoading(true);
        getNewBooks(rewrite);
    }
    
    protected void getNewBooks(boolean rewrite) {
        this.rewrite = rewrite;
        mRepository.getBooks(mModel.getQuery(),mModel.getAuthorId(),mModel.getGenreId(),mModel.getCurPage(),mModel.getPageSize());
    }
    
    public List<Book> getBooks(){
        return mModel.getCurBooks();
    }
    
    @Override
    public void rememberBooks(List<Book> books) {
        mModel.setLoading(false);
        
        if (rewrite)
            mModel.setCurBooks(books);
        else
            mModel.addBooks(books);
        
        if (getView()!=null)
            getView().updateList(mModel.getCurBooks());
    }
    
    public void getNextPage() {
        mModel.increasePage();
        updateBookList(false);
    }
    
    public int getPageSize() {
        return mModel.getPageSize();
    }
    
    public void openBookInfo(int book_id) {
        if (!mModel.isLoading()) {
            mModel.setLoading(true);
            mRepository.getBookInfo(book_id);
        }
    }
    
    @Override
    public void rememberBook(BookInfo book) {
        mModel.setLoading(false);
        navigateTo(new Screens.BookScreens(Screens.BookScreens.BOOK_INFO_SCREEN, book));
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        getView().showMessage(errorDescription);
        call.call();
        if (call.getAttemptsCount()==3)
            mModel.setLoading(false);
    }

    @Override
    public void onQueryChange(String query) {
        this.mModel.setQuery(query);
        getNewBooks(true);
    }

    @Override
    public void onGenreChange(Genre genre) {
        this.mModel.setGenreId(genre.getGenreId());
        getNewBooks(true);
    }
}

package c.tgm.booksapplication.books.list;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.books.BookPresenterRepo;
import c.tgm.booksapplication.books.BookRepository;
import c.tgm.booksapplication.books.BookRepositoryImpl;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;
import c.tgm.booksapplication.repositories.RepositoryCall;

@InjectViewState
public class BookListPresenter extends NavigatorPresenter<BookListView> implements BookPresenterRepo {
    
    protected BookListModel mModel;
    protected BookRepository mRepository;

    protected boolean rewrite;
    
    public BookListPresenter() {
        mModel = new BookListModel();
        mRepository = new BookRepositoryImpl(this);
    }
    
    public void rememberAuthorId(int authorId) {
        mModel.setAuthorId(authorId);
    }
    
    public void updateBookList(String query, boolean rewrite) {
        if(mModel.isLoading())
            mRepository.cancelRequest();
        
        if (rewrite){
            mModel.setCurPage(1);
        }
        
        mModel.setLoading(true);
        getNewBooks(query,rewrite);
    }
    
    protected void getNewBooks(String query, boolean rewrite) {
        this.rewrite = rewrite;
        mRepository.getBooks(query,mModel.getAuthorId(),mModel.getGenreId(),mModel.getCurPage(),mModel.getPageSize());
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
    
    public void getNextPage(String query) {
        mModel.increasePage();
        updateBookList(query, false);
    }
    
    public void setGenre(Long genreId, String query) {
        
        if (genreId < 0)
            genreId = null;
        
        if ((genreId==null && mModel.getGenreId() != null) ||
                (genreId!= null && !genreId.equals(mModel.getGenreId()))) {
            mModel.setGenreId(genreId);
            updateBookList(query,true);
        }
    }
    
    public List<Genre> getGenres() {
        GenreDao genreDao = BookApplication.INSTANCE.getDataStore().getDaoSession().getGenreDao();
        
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(-1l,"Любой жанр"));
    
        genres.addAll(genreDao.queryBuilder().build().list());
        
        return genres;
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
    }
}

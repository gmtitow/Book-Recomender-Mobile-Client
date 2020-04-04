package c.tgm.booksapplication.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.BookListDao;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;
import c.tgm.booksapplication.models.data.ReadBookWithListDao;
import c.tgm.booksapplication.models.data.ReadBookWithList;
import c.tgm.booksapplication.repositories.GenreRepository;
import c.tgm.booksapplication.repositories.GenreRepositoryImpl;
import c.tgm.booksapplication.repositories.GenreSaver;
import c.tgm.booksapplication.repositories.RepositoryCall;
import c.tgm.booksapplication.repositories.book_list.BookListRepo;
import c.tgm.booksapplication.repositories.book_list.BookListRepository;
import c.tgm.booksapplication.repositories.book_list.BookListRepositoryImpl;

@InjectViewState
public class MainActivityPresenter extends NavigatorPresenter<MainActivityView> implements GenreSaver, BookListRepo {
    private MainActivityView view;
    private MainModel mModel;

    private BookListRepository mBookListRepository;
    
    public MainActivityPresenter() {
        mModel = new MainModel();
    
        GenreRepository genreRepository = new GenreRepositoryImpl(this);
        
        genreRepository.getGenres();

        mBookListRepository = new BookListRepositoryImpl(this);

        if (BookApplication.INSTANCE.getDataStore().isAuthorized()) {
            mBookListRepository.getBookLists();

            mBookListRepository.getAllReadBooks();
        }
    }
    
    public void setView(MainActivityView view) {
        this.view = view;
        view.updateAuthenticated(BookApplication.INSTANCE.getDataStore().isAuthorized(),BookApplication.INSTANCE.getDataStore().getLogin());
        
    }
    
    public void logout(Context mContext) {
        BookApplication.INSTANCE.getDataStore().clearAll(mContext);
        view.updateAuthenticated(BookApplication.INSTANCE.getDataStore().isAuthorized(),BookApplication.INSTANCE.getDataStore().getLogin());
    }
    
    public void someOtherMethod() {
        //some work
    }
    
    @Override
    public void saveGenres(List<Genre> genres) {
        GenreDao genreDAO = BookApplication.INSTANCE.getDataStore().getDaoSession().getGenreDao();
        genreDAO.deleteAll();
        genreDAO.insertOrReplaceInTx(genres);
    }

    @Override
    public void onGetLists(ArrayList<BookList> lists) {
        BookListDao genreDAO = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();
        genreDAO.deleteAll();
        genreDAO.insertOrReplaceInTx(lists);
    }

    @Override
    public void onGetAllReadBooks(List<ReadBookWithList> books) {
        ReadBookWithListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getReadBookWithListDao();
        dao.deleteAll();
        dao.insertOrReplaceInTx(books);

//        List<ReadBookWithList> all = dao.loadAll();
//
//        List<ReadBookWithList> readWithLists2 = dao.queryBuilder().where(
//                ReadBookWithListDao.Properties.ListId.eq(34))
//                .limit(10).offset(0).build().list();
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        call.call();
    }
}

package c.tgm.booksapplication.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import c.tgm.booksapplication.AbstractPresenter;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;
import c.tgm.booksapplication.repositories.GenreRepository;
import c.tgm.booksapplication.repositories.GenreRepositoryImpl;
import c.tgm.booksapplication.repositories.GenreSaver;
import ru.terrakok.cicerone.Navigator;

import static c.tgm.booksapplication.any.DataStore.getDaoSession;

@InjectViewState
public class MainActivityPresenter extends NavigatorPresenter<MainActivityView> implements GenreSaver {
    private MainActivityView view;
    private MainModel mModel;
    
    public MainActivityPresenter() {
        mModel = new MainModel();
    
        GenreRepository genreRepository = new GenreRepositoryImpl(this);
        
        genreRepository.getGenres();
    }
    
    public void setView(MainActivityView view) {
        this.view = view;
        view.updateAuthenticated(DataStore.isAuthorized(),DataStore.getLogin());
        
    }
    
    public void logout(Context mContext) {
        DataStore.clearAll(mContext);
        view.updateAuthenticated(DataStore.isAuthorized(),DataStore.getLogin());
    }
    
    public void someOtherMethod() {
        //some work
    }
    
    @Override
    public void saveGenres(List<Genre> genres) {
        GenreDao genreDAO = DataStore.getDaoSession().getGenreDao();
        
        genreDAO.insertOrReplaceInTx(genres);
    }
}

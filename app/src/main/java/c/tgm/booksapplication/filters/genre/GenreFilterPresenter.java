package c.tgm.booksapplication.filters.genre;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.authors.list.AuthorPresenterRepo;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;

@InjectViewState
public class GenreFilterPresenter extends NavigatorPresenter<GenreFilterView> {
    
    protected GenreFilterModel mModel;
    
    public GenreFilterPresenter() {
        mModel = new GenreFilterModel();
    }

    public void setHandler(IGenreChanger handler) {
        mModel.setChanger(handler);
    }
    
    public void updateGenreList() {
        GenreDao genreDAO = BookApplication.INSTANCE.getDataStore().getDaoSession().getGenreDao();

        List<Genre> genres = genreDAO.loadAll();

        Genre emptyGenre = new Genre();
        emptyGenre.setGenreId(-1L);
        emptyGenre.setGenreName("Сбросить");

        genres.add(0,emptyGenre);

        rememberGenres(genres);
    }

    public void rememberGenres(List<Genre> genres) {
        mModel.setGenres(genres);

        getViewState().updateList(mModel.getGenres());
    }

    public void onSelectGenre(Genre genre) {
        if (genre.getGenreId()==-1)
            mModel.getChanger().onChangeGenre(null);
        else
            mModel.getChanger().onChangeGenre(genre);
        exit();
    }
}

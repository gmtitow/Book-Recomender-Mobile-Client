package c.tgm.booksapplication.filters;

import com.arellomobile.mvp.InjectViewState;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.filters.author.IAuthorChanger;
import c.tgm.booksapplication.filters.genre.IGenreChanger;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

@InjectViewState
public class FilterPresenter extends NavigatorPresenter<FilterView> implements IAuthorChanger, IGenreChanger {
    FilterModel mModel;

    public FilterPresenter() {
        mModel = new FilterModel();
        getViewState().changeGenre(mModel.getGenre());
        getViewState().changeAuthor(mModel.getAuthor());
    }

    public void setHandler(IFIlterHandler handler) {
        mModel.setHandler(handler);
    }

    public void setGenre(Genre genre) {
        mModel.setGenre(genre);
        getViewState().changeGenre(mModel.getGenre());
    }

    public void setAuthor(Author author) {
        mModel.setAuthor(author);
        getViewState().changeAuthor(mModel.getAuthor());
    }

    @Override
    public void setView(FilterView view) {
        super.setView(view);
    }

    public void setQuery(String query) {
        mModel.setQuery(query);
    }

    public void onQueryChanged(String query) {
        mModel.setQuery(query);

        mModel.getHandler().onQueryChange(query);
    }

    public void onGenrePressed() {
//        getViewState().showMessage("Жанр нажат");
        navigateTo(new Screens.FilterScreens(Screens.FilterScreens.FILTER_GENRE_SCREEN,this));
    }

    public void onAuthorPressed() {
//        getViewState().showMessage("Автор нажат");
        navigateTo(new Screens.FilterScreens(Screens.FilterScreens.FILTER_AUTHOR_SCREEN,this));
    }

    @Override
    public void onChangeAuthor(Author author) {
        mModel.getHandler().onAuthorChange(author);
        setAuthor(author);
    }

    @Override
    public void onChangeGenre(Genre genre) {
        mModel.getHandler().onGenreChange(genre);
        setGenre(genre);
    }
}

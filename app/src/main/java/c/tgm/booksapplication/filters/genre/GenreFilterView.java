package c.tgm.booksapplication.filters.genre;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

public interface GenreFilterView extends MvpView {
    
    void updateList(ArrayList<Genre> genres);
}

package c.tgm.booksapplication.authors.list;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Genre;

public interface AuthorListView extends MvpView {
    
    void updateList(ArrayList<Author> authors);
    
    void updateGenres(ArrayList<Genre> genres);
}

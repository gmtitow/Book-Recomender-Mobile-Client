package c.tgm.booksapplication.books.list;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Genre;

public interface BookListView extends MvpView {
    
    void updateList(ArrayList<Book> books);
    
    void updateGenres(ArrayList<Genre> genres);
}

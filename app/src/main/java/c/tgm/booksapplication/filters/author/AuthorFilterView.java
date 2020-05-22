package c.tgm.booksapplication.filters.author;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

public interface AuthorFilterView extends MvpView {
    
    void updateList(ArrayList<Author> authors);
}

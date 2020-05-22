package c.tgm.booksapplication.filters;

import c.tgm.booksapplication.AbstractView;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

public interface FilterView extends AbstractView {

    void changeAuthor(Author author);

    void changeGenre(Genre genre);
}

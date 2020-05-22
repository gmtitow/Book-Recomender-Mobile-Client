package c.tgm.booksapplication.filters;

import java.io.Serializable;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

public interface IFIlterHandler extends Serializable {
    default void onQueryChange(String query) {}

    default void onGenreChange(Genre genre) {}

    default void onAuthorChange(Author author) {}
}

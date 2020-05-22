package c.tgm.booksapplication.filters.genre;

import java.io.Serializable;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

public interface IGenreChanger extends Serializable {
    void onChangeGenre(Genre genre);
}

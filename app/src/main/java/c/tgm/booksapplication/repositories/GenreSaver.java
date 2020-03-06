package c.tgm.booksapplication.repositories;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Genre;

public interface GenreSaver {
    void saveGenres(List<Genre> genres);
}

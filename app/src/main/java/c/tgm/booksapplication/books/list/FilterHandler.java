package c.tgm.booksapplication.books.list;

import c.tgm.booksapplication.filters.IFIlterHandler;
import c.tgm.booksapplication.models.data.Genre;

public class FilterHandler implements IFIlterHandler {
    private BookListModel model;

    public FilterHandler(BookListModel model) {
        this.model = model;
    }

    @Override
    public void onQueryChange(String query) {
        this.model.setQuery(query);
    }

    @Override
    public void onGenreChange(Genre genre) {
        this.model.setGenreId(genre.getGenreId());
    }
}

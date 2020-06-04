package c.tgm.booksapplication.filters;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.Genre;

public class FilterModel {
    private Genre genre;

    private Author author;

    private BookList list;

    private String query;

    private IFIlterHandler handler;

    public IFIlterHandler getHandler() {
        return handler;
    }

    public void setHandler(IFIlterHandler handler) {
        this.handler = handler;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public BookList getList() {
        return list;
    }

    public void setList(BookList list) {
        this.list = list;
    }
}

package c.tgm.booksapplication.promotion.add.selectbooks;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.APaginationModel;

public class PromotionSelectBooksModel extends APaginationModel<Book> {
    private Author author;

    private Genre genre;

    private String query;

    private IBookDescriptionRemember remember;

    private List<BookDescription> descriptions;

    private List<Integer> addedBooks = new ArrayList<>();

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public IBookDescriptionRemember getRemember() {
        return remember;
    }

    public void setRemember(IBookDescriptionRemember remember) {
        this.remember = remember;
    }

    public List<BookDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<BookDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Integer> getAddedBooks() {
        return addedBooks;
    }

    public void setAddedBooks(List<Integer> addedBooks) {
        this.addedBooks = addedBooks;
    }
}

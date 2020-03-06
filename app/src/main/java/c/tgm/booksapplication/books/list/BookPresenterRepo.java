package c.tgm.booksapplication.books.list;

import java.util.List;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;

public interface BookPresenterRepo {
    void rememberBooks(List<Book> books, boolean rewrite);
    
    void rememberBook(BookInfo book);
    
    void onError(String error);
}

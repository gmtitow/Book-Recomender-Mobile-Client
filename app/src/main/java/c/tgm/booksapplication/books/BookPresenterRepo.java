package c.tgm.booksapplication.books;

import java.util.List;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.repositories.CommonRepo;

public interface BookPresenterRepo extends CommonRepo {
    void rememberBooks(List<Book> books);
    
    void rememberBook(BookInfo book);

    default void onUpdateRecommends(String answer){};
}

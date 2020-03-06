package c.tgm.booksapplication.authors.list;

import java.util.List;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Book;

public interface AuthorPresenterRepo {
    void rememberAuthors(List<Author> authors, boolean rewrite);
    
    void onError(String error);
}

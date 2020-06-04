package c.tgm.booksapplication.filters.booklist;

import java.io.Serializable;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.BookList;

public interface IBookListChanger extends Serializable {
    void onChangeList(BookList list);
}

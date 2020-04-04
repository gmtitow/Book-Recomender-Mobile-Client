package c.tgm.booksapplication.repositories.book_list;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.models.data.ReadBookWithList;
import c.tgm.booksapplication.repositories.CommonRepo;

public interface BookListRepo extends CommonRepo {
    default void onGetLists(ArrayList<BookList> lists){};

    default void onCreateList(BookList list){};

    default void onGetBooksFromList(List<ReadBook> books){};

    default void onDeleteList(String deleted){};

    default void onAddBookInList(ReadBookWithList book){};

    default void onGetAllReadBooks(List<ReadBookWithList> books){};
}

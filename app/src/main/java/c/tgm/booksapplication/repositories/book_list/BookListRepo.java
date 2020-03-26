package c.tgm.booksapplication.repositories.book_list;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.repositories.CommonRepo;

public interface BookListRepo extends CommonRepo {
    void onGetLists(ArrayList<BookList> lists);

    void onCreateList(BookList list);

    void onDeleteList(String deleted);
}

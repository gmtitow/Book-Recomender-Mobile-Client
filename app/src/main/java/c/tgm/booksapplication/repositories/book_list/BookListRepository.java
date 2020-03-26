package c.tgm.booksapplication.repositories.book_list;

import c.tgm.booksapplication.repositories.Repository;

public interface BookListRepository extends Repository {
    void getBookLists();

    void createNewList(String listName);

    void deleteList(int listId);
}

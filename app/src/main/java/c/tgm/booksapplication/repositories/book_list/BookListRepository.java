package c.tgm.booksapplication.repositories.book_list;

import c.tgm.booksapplication.repositories.Repository;

public interface BookListRepository extends Repository {
    void getBookLists();

    void createNewList(String listName);

    void getBooksFromList(int listId, int page, int pageSize);

    void deleteList(int listId);

    void addBookInList(int listId, int bookId, int rating);

    void getAllReadBooks();

    void deleteBookFromList(int bookId, int listId);
}

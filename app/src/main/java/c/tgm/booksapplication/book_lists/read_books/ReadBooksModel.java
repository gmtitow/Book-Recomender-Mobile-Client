package c.tgm.booksapplication.book_lists.read_books;

import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.pagination.APaginationModel;

public class ReadBooksModel extends APaginationModel<ReadBook> {
    private int listId;

    private int currentBookId;

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getCurrentBookId() {
        return currentBookId;
    }

    public void setCurrentBookId(int currentBookId) {
        this.currentBookId = currentBookId;
    }
}

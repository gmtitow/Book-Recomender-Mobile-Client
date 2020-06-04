package c.tgm.booksapplication.book_lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.BookList;

public class BookListsModel implements Serializable {

    private List<BookList> curLists = new ArrayList<>();

    private int currentListId;

    public List<BookList> getCurLists() {
        return curLists;
    }

    public void setCurLists(List<BookList> curLists) {
        this.curLists.clear();
        this.curLists.addAll(curLists);
    }

    public void deleteListById(int listId) {
        for (int i = 0; i < curLists.size(); i++) {
            if (curLists.get(i).getListId() == listId) {
                curLists.remove(i);
                break;
            }
        }
    }

    public void addList(BookList list) {
        this.curLists.add(list);
    }

    public int getCurrentListId() {
        return currentListId;
    }

    public void setCurrentListId(int currentListId) {
        this.currentListId = currentListId;
    }
}

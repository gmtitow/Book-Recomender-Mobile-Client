package c.tgm.booksapplication.filters.booklist;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.BookList;

public interface BookListFilterView extends MvpView {
    
    void updateList(ArrayList<BookList> lists);
}

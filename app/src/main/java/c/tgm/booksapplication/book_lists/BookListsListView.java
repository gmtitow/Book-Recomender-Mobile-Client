package c.tgm.booksapplication.book_lists;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.BookList;

public interface BookListsListView extends MvpView {

    void updateList(List<BookList> lists);

    void progressChanged(boolean progress);
}

package c.tgm.booksapplication.book_lists;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import c.tgm.booksapplication.models.data.BookList;

public interface BookListsListView extends MvpView {

    void updateList(ArrayList<BookList> lists);

    void progressChanged(boolean progress);
}

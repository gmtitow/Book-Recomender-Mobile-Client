package c.tgm.booksapplication.promotion.add.showselected;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.IPaginationView;

public interface ShowSelectedBooksView extends MvpView {
    void updateList(List<BookDescription> descriptions);
}

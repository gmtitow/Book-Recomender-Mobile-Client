package c.tgm.booksapplication.promotion.add.showselected;

import java.util.List;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.books.BookPresenterRepo;
import c.tgm.booksapplication.books.BookRepository;
import c.tgm.booksapplication.books.BookRepositoryImpl;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.APaginationPresenter;
import c.tgm.booksapplication.promotion.add.selectbooks.IBookDescriptionRemember;
import c.tgm.booksapplication.repositories.RepositoryCall;


public class ShowSelectedBooksPresenter extends NavigatorPresenter<ShowSelectedBooksView> {
    protected BookRepository mRepository;
    protected boolean rewrite;

    ShowSelectedBooksModel mModel;

    public ShowSelectedBooksPresenter() {
        initializeModel();
    }

    public ShowSelectedBooksModel getModel() {
        return mModel;
    }

    protected void initializeModel() {
        mModel = new ShowSelectedBooksModel();
    }

    public void setDescriptionRemember(IBookDescriptionRemember remember) {
        getModel().setRemember(remember);
    }

    public void setBookDescriptions(List<BookDescription> descriptions) {
        getModel().setDescriptions(descriptions);
    }

    public void showSelectedBooks() {

    }
}

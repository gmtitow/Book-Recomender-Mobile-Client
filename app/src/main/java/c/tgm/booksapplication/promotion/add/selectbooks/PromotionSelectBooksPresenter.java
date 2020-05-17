package c.tgm.booksapplication.promotion.add.selectbooks;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.books.BookPresenterRepo;
import c.tgm.booksapplication.books.BookRepository;
import c.tgm.booksapplication.books.BookRepositoryImpl;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.APaginationPresenter;
import c.tgm.booksapplication.repositories.RepositoryCall;


public class PromotionSelectBooksPresenter extends APaginationPresenter<PromotionSelectBooksView, Book,PromotionSelectBooksModel>
implements BookPresenterRepo {
    protected BookRepository mRepository;
    protected boolean rewrite;

    @Override
    protected void initializeModel() {
        mModel = new PromotionSelectBooksModel();
    }

    @Override
    protected void initializeRepository() {
        mRepository = new BookRepositoryImpl(this);
    }

    public void setDescriptionRemember(IBookDescriptionRemember remember) {
        getModel().setRemember(remember);
    }

    public void setBookDescriptions(List<BookDescription> descriptions) {
        getModel().setDescriptions(descriptions);
    }

    public void showSelectedBooks() {

    }

    public void addBook(Book book) {
        getModel().getDescriptions().add(new BookDescription(BookDescription.TYPE_BOOK,book,1.f));

        getModel().getAddedBooks().add(book.getBookId());
    }

    public void addDescription() {
        getModel().getDescriptions().add(new BookDescription(
                BookDescription.TYPE_DESCRIPTION,getModel().getAuthor(),
                getModel().getGenre(),getModel().getQuery(),1.f));
    }


    @Override
    protected void getNewObjects(boolean add) {
        mRepository.getBooks(getModel().getQuery(),getModel().getAuthor().getAuthorId(),
                getModel().getGenre().getGenreId(),getModel().getCurPage(),getModel().getPageSize());
    }

    @Override
    public void rememberBooks(List<Book> books) {
        mModel.setLoading(false);

        if (rewrite)
            mModel.setObjects(books);
        else
            mModel.addObjects(books);

        getView().updateList(mModel.getObjects());
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        getView().showMessage(errorDescription);
        call.call();
    }
}

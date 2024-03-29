package c.tgm.booksapplication.promotion.add.selectbooks;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.books.BookPresenterRepo;
import c.tgm.booksapplication.books.BookRepository;
import c.tgm.booksapplication.books.BookRepositoryImpl;
import c.tgm.booksapplication.filters.IFIlterHandler;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.APaginationPresenter;
import c.tgm.booksapplication.repositories.RepositoryCall;


public class PromotionSelectBooksPresenter extends APaginationPresenter<PromotionSelectBooksView, Book,PromotionSelectBooksModel>
implements BookPresenterRepo, IFIlterHandler, IBookDescriptionRemember {
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

        recountAddedBooks();
    }

    private void recountAddedBooks() {
        getModel().setAddedBooks(new ArrayList<>());
        for (BookDescription description:getModel().getDescriptions()) {
            if (description.getType().equals(BookDescription.TYPE_BOOK))
                getModel().getAddedBooks().add(description.getBook().getBookId());
        }
    }

    public void showSelectedBooks() {
        navigateTo(new Screens.PromotionScreens(
                Screens.PromotionScreens.SHOW_SELECTED_BOOKS_SCREEN,
                getModel().getRemember(),this,getModel().getDescriptions()));
    }

    public void addBook(Book book) {
        getModel().getDescriptions().add(new BookDescription(BookDescription.TYPE_BOOK,book,1.f));

        getModel().getAddedBooks().add(book.getBookId());

        getModel().getObjects().remove(book);

        getView().updateList(getModel().getObjects());
    }

    public void addDescription() {
        getModel().getDescriptions().add(new BookDescription(
                BookDescription.TYPE_DESCRIPTION,getModel().getAuthor(),
                getModel().getGenre(),getModel().getQuery(),1.f));

        if (getView()!=null)
            getView().showMessage("Все книги с данным фильтром были добавлены");
    }


    @Override
    protected void getNewObjects(boolean add) {
        if (add)
            rewrite = false;
        else
            rewrite = true;

        mRepository.getBooks(getModel().getQuery(),getModel().getAuthor()==null?null:getModel().getAuthor().getAuthorId(),
                getModel().getGenre()==null?null:getModel().getGenre().getGenreId(),
                getModel().getAddedBooks(),
                getModel().getCurPage(),getModel().getPageSize());
    }

    @Override
    public void rememberBooks(List<Book> books) {
        mModel.setLoading(false);

        if (rewrite)
            mModel.setObjects(books);
        else
            mModel.addObjects(books);

        if (getView()!=null)
            getView().updateList(mModel.getObjects());
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        getView().showMessage(errorDescription);
        call.call();
    }

    //IFilterHandler
    @Override
    public void onQueryChange(String query) {
        getModel().setQuery(query);

//        mRepository.cancelRequest();

        getNewObjects(false);
    }

    @Override
    public void onGenreChange(Genre genre) {
        getModel().setGenre(genre);

//        mRepository.cancelRequest();

        getNewObjects(false);
    }

    @Override
    public void onAuthorChange(Author author) {
        getModel().setAuthor(author);

//        mRepository.cancelRequest();

        getNewObjects(false);
    }

    @Override
    public void rememberBookDescriptions(List<BookDescription> descriptions) {
        getModel().setDescriptions(descriptions);
        recountAddedBooks();
    }
}

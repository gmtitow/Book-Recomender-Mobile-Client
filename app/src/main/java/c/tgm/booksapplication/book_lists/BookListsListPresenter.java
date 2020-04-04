package c.tgm.booksapplication.book_lists;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.BookListDao;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.repositories.RepositoryCall;
import c.tgm.booksapplication.repositories.book_list.BookListRepo;
import c.tgm.booksapplication.repositories.book_list.BookListRepositoryImpl;

public class BookListsListPresenter extends NavigatorPresenter<BookListsListView> implements BookListRepo {

    protected BookListsModel mModel;
    protected BookListRepositoryImpl mRepository;

    public BookListsListPresenter() {
        mModel = new BookListsModel();

        BookListDao listDAO = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();

        List<BookList> lists = listDAO.loadAll();

        mModel.setCurLists(lists);

        mRepository = new BookListRepositoryImpl(this);
    }

    @Override
    public void onGetLists(ArrayList<BookList> lists) {

        BookListDao listDAO = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();
        listDAO.deleteAll();
        listDAO.insertInTx(lists);

        mModel.setCurLists(lists);
        getView().updateList(mModel.getCurLists());
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        call.call();
    }

    public List<BookList> getLists() {
        return mModel.getCurLists();
    }

    public void updateListsList() {
        mRepository.getBookLists();
    }

    public void createList(String listName) {
        mRepository.createNewList(listName);
    }

    public void openReadBooks(Integer listId) {
        navigateTo(new Screens.BookListsScreens(Screens.BookListsScreens.READ_BOOKS_SCREEN,listId));
    }

    @Override
    public void onCreateList(BookList list) {
        mModel.addList(list);

        BookListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();
        dao.insert(list);

        getView().updateList(mModel.getCurLists());
    }

    public void deleteList() {
        getView().progressChanged(true);
        mRepository.deleteList(
                mModel.getCurrentListId()
        );
    }

    public void setCurrentListId(int listId) {
        mModel.setCurrentListId(listId);
    }

    @Override
    public void onDeleteList(String deleted) {
        mModel.deleteListById(mModel.getCurrentListId());

        BookListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();

        dao.deleteByKey((long)mModel.getCurrentListId());

        getView().updateList(mModel.getCurLists());
        getView().progressChanged(false);
    }

    @Override
    public void onGetBooksFromList(List<ReadBook> books) {
    }
}

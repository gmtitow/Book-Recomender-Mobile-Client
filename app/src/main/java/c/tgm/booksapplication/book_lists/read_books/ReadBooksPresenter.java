package c.tgm.booksapplication.book_lists.read_books;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.models.data.ReadBookWithList;
import c.tgm.booksapplication.models.data.ReadBookWithListDao;
import c.tgm.booksapplication.pagination.APaginationPresenter;
import c.tgm.booksapplication.repositories.RepositoryCall;
import c.tgm.booksapplication.repositories.book_list.BookListRepo;
import c.tgm.booksapplication.repositories.book_list.BookListRepository;
import c.tgm.booksapplication.repositories.book_list.BookListRepositoryImpl;

public class ReadBooksPresenter extends APaginationPresenter<ReadBook, ReadBooksModel> implements BookListRepo {

    BookListRepository mRepository;

    public ReadBooksPresenter() {
        super();
    }

    public void setListId(int listId) {
        mModel.setListId(listId);

//        ReadBookWithListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getReadBookWithListDao();
//
//        List<ReadBook> readBooks = new ArrayList<>();
//
//        List<ReadBookWithList> readWithLists = dao.queryBuilder().where(
//                ReadBookWithListDao.Properties.ListId.eq(mModel.getListId()))
//                .limit(mModel.getPageSize()).offset(mModel.getPageSize()*(mModel.getCurPage()-1)).build().list();
//
//        readBooks.addAll(readWithLists);
//
//        mModel.setObjects(readBooks);
    }

    @Override
    protected void initializeModel() {
        mModel = new ReadBooksModel();
    }

    @Override
    protected void initializeRepository() {
        mRepository = new BookListRepositoryImpl(this);
    }

    @Override
    protected void getNewObjects() {
//        mRepository.getBooksFromList(mModel.getListId(), mModel.getCurPage(), mModel.getPageSize());
        ReadBookWithListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getReadBookWithListDao();

        List<ReadBook> readBooks = new ArrayList<>();

        List<ReadBookWithList> readWithLists = dao.queryBuilder().where(
                ReadBookWithListDao.Properties.ListId.eq(mModel.getListId()))
                .limit(mModel.getPageSize()).offset(mModel.getPageSize()*(mModel.getCurPage()-1)).build().list();

        for (ReadBookWithList book:readWithLists) {
            readBooks.add(book.toReadBook());
        }

        onGetBooksFromList(readBooks);
    }

    @Override
    public void onGetBooksFromList(List<ReadBook> books) {
        mModel.addObjects(books);

        getView().updateList(mModel.getObjects());
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        call.call();
    }

    @Override
    public void onGetLists(ArrayList<BookList> lists) {
    }

    @Override
    public void onCreateList(BookList list) {
    }

    @Override
    public void onDeleteList(String deleted) {
    }
}

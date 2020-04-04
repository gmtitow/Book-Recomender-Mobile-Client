package c.tgm.booksapplication.repositories.book_list;


import c.tgm.booksapplication.api.BookListsAPI;
import c.tgm.booksapplication.models.request.book_list.CommonBookListRelationRequest;
import c.tgm.booksapplication.models.request.book_list.CommonListRequest;
import c.tgm.booksapplication.models.response.BookListResponse;
import c.tgm.booksapplication.models.response.BookListsResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.models.response.ReadBookResponse;
import c.tgm.booksapplication.models.response.ReadBookWithListsResponse;
import c.tgm.booksapplication.models.response.ReadBooksResponse;
import c.tgm.booksapplication.models.response.ReadBooksWithListsResponse;
import c.tgm.booksapplication.repositories.RepositoryCallImpl;
import c.tgm.booksapplication.repositories.RepositoryImpl;
import retrofit2.Call;

public class BookListRepositoryImpl extends RepositoryImpl implements BookListRepository {

    private BookListRepo mHandler;

    private BookListsAPI mApi;

    public BookListRepositoryImpl(BookListRepo handler) {
        this.mHandler = handler;
        mApi = getAPI(BookListsAPI.class);
    }

    @Override
    public void getBookLists() {
        RepositoryCallImpl call = new RepositoryCallImpl<>(mApi.getLists(), BookListsResponse.class, mHandler::onGetLists, mHandler::onError, this);
        call.call();
    }

    @Override
    public void createNewList(String listName) {
        CommonListRequest request = new CommonListRequest(listName);
        RepositoryCallImpl call = new RepositoryCallImpl<>(mApi.addList(request), BookListResponse.class, mHandler::onCreateList, mHandler::onError, this);
        call.call();
    }

    @Override
    public void deleteList(int listId) {
        CommonListRequest request = new CommonListRequest(listId);
        RepositoryCallImpl call = new RepositoryCallImpl<>(
                mApi.deleteList(request), CommonResponse.class, mHandler::onDeleteList, mHandler::onError, this);
        call.call();
    }

    @Override
    public void getBooksFromList(int listId, int page, int pageSize) {
        Call call = mApi.getBooksInList(listId, page, pageSize);
        RepositoryCallImpl respCall = new RepositoryCallImpl<>(
                call, ReadBooksResponse.class, mHandler::onGetBooksFromList, mHandler::onError, this);
        respCall.call();
    }

    @Override
    public void addBookInList(int listId, int bookId, int rating) {
        Call call = mApi.addBookInList(new CommonBookListRelationRequest(listId, bookId, rating));
        RepositoryCallImpl respCall = new RepositoryCallImpl<>(
                call, ReadBookWithListsResponse.class, mHandler::onAddBookInList, mHandler::onError, this);
        respCall.call();
    }

    @Override
    public void getAllReadBooks() {
        Call call = mApi.getAllReadBooks();
        RepositoryCallImpl respCall = new RepositoryCallImpl<>(
                call, ReadBooksWithListsResponse.class, mHandler::onGetAllReadBooks, mHandler::onError, this);
        respCall.call();
    }
}

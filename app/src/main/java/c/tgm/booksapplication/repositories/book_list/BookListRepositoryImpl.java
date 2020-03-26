package c.tgm.booksapplication.repositories.book_list;


import c.tgm.booksapplication.api.BookListsAPI;
import c.tgm.booksapplication.models.request.book_list.CommonListRequest;
import c.tgm.booksapplication.models.response.BookListResponse;
import c.tgm.booksapplication.models.response.BooksResponse;
import c.tgm.booksapplication.models.response.BookListsResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.repositories.RepositoryImpl;

public class BookListRepositoryImpl extends RepositoryImpl implements BookListRepository {

    private BookListRepo mHandler;

    private BookListsAPI mApi;

    public BookListRepositoryImpl(BookListRepo handler) {
        this.mHandler = handler;
        mApi = getAPI(BookListsAPI.class);
    }

    @Override
    public void getBookLists() {
        this.callAPIMethod(mApi::getLists, BookListsResponse.class, mHandler::onGetLists, mHandler::onError);
    }

    @Override
    public void createNewList(String listName) {
        CommonListRequest request = new CommonListRequest(listName);
        this.callAPIMethod(mApi::addList,request, BookListResponse.class, mHandler::onCreateList, mHandler::onError);
    }

    @Override
    public void deleteList(int listId) {
        CommonListRequest request = new CommonListRequest(listId);
        this.callAPIMethod(mApi::deleteList,request, CommonResponse.class, mHandler::onDeleteList, mHandler::onError);
    }
}

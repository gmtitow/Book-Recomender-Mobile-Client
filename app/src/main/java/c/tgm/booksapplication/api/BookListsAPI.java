package c.tgm.booksapplication.api;


import c.tgm.booksapplication.models.request.book_list.CommonBookListRelationRequest;
import c.tgm.booksapplication.models.request.book_list.CommonListRequest;
import c.tgm.booksapplication.models.response.BookListResponse;
import c.tgm.booksapplication.models.response.BookListsResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.models.response.ReadBookWithListsResponse;
import c.tgm.booksapplication.models.response.ReadBooksResponse;
import c.tgm.booksapplication.models.response.ReadBooksWithListsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static c.tgm.booksapplication.any.UserRequest.PRIVATE;

/**
 * Created by NorD on 16.11.2017.
 */

public interface BookListsAPI {
    final String PREFIX = "/book-list";
    
    final String URL_GET_LISTS = PRIVATE + PREFIX + "/get";
    final String URL_ADD = PRIVATE + PREFIX + "/add";
    final String URL_EDIT = PRIVATE + PREFIX + "/edit";
    final String URL_DELETE = PRIVATE + PREFIX + "/delete";
    final String URL_ADD_BOOK_IN_LIST = PRIVATE + PREFIX + "/add/book";
    final String URL_DELETE_BOOK_FROM_LIST = PRIVATE + PREFIX + "/delete/book";
    final String URL_GET_BOOKS = PRIVATE + PREFIX + "/get/books";
    final String URL_GET_ALL_READ_BOOKS = PRIVATE + PREFIX + "/get/books/all";
    
    @GET(BookListsAPI.URL_GET_LISTS)
    Call<BookListsResponse> getLists();
    
    @POST(BookListsAPI.URL_ADD)
    Call<BookListResponse> addList(@Body CommonListRequest request);

    @POST(BookListsAPI.URL_EDIT)
    Call<BookListResponse> editList(@Body CommonListRequest request);

    @HTTP(method = "DELETE", path = BookListsAPI.URL_DELETE, hasBody = true)
    Call<CommonResponse> deleteList(@Body CommonListRequest request);

    @POST(BookListsAPI.URL_ADD_BOOK_IN_LIST)
    Call<ReadBookWithListsResponse> addBookInList(@Body CommonBookListRelationRequest request);

    @HTTP(method = "DELETE", path = BookListsAPI.URL_DELETE_BOOK_FROM_LIST, hasBody = true)
    Call<CommonResponse> deleteBookFromList(@Body CommonBookListRelationRequest request);

    @GET(BookListsAPI.URL_GET_BOOKS)
    Call<ReadBooksResponse> getBooksInList(@Query("list_id") int listId,
                                           @Query("page") int page,
                                           @Query("page_size") int pageSize);

    @GET(BookListsAPI.URL_GET_ALL_READ_BOOKS)
    Call<ReadBooksWithListsResponse> getAllReadBooks();
}

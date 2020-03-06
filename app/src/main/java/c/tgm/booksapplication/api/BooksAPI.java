package c.tgm.booksapplication.api;


import java.util.HashMap;

import c.tgm.booksapplication.models.response.AuthResponse;
import c.tgm.booksapplication.models.response.BookInfoResponse;
import c.tgm.booksapplication.models.response.BookListResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by NorD on 16.11.2017.
 */

public interface BooksAPI {
    final String PREFIX = "book";
    
    final String URL_FIND_BOOKS = PREFIX + "/find";
    final String URL_GET_RECOMMENDS = PREFIX + "/recommends";
    final String URL_GET_INFO = PREFIX + "/get/info";
    
    public final String URL_DOWNLOAD_FILE = PREFIX + "/download/file/txt";
    
    @POST(BooksAPI.URL_FIND_BOOKS)
    Call<BookListResponse> find(@Body HashMap<String, Object> data);
    
    
    @POST(BooksAPI.URL_GET_RECOMMENDS)
    Call<BookListResponse> getRecommends(@Body HashMap<String, Object> data);
    
    @GET(BooksAPI.URL_GET_INFO)
    Call<BookInfoResponse> getInfo(@Query("book_id") int book_id);
    
    @GET(BooksAPI.URL_DOWNLOAD_FILE)
    Call<ResponseBody> download(@Query("book_id") int book_id);
    
    @GET("images/sunset.jpg")
    Call<ResponseBody> testDownload();
}

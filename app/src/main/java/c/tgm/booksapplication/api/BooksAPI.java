package c.tgm.booksapplication.api;


import java.util.HashMap;

import c.tgm.booksapplication.models.request.book_list.CommonListRequest;
import c.tgm.booksapplication.models.response.BookInfoResponse;
import c.tgm.booksapplication.models.response.BooksResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by NorD on 16.11.2017.
 */

public interface BooksAPI {
    final String PREFIX = "book";
    
    final String URL_FIND_BOOKS = PREFIX + "/find";
    final String URL_GET_RECOMMENDS = "private/" + PREFIX + "/recommends";
    final String URL_GET_INFO = PREFIX + "/get/info";
    final String URL_UPDATE_RECOMMENDS = "private/" + PREFIX + "/form-recommends";
    
    public final String URL_DOWNLOAD_FILE = PREFIX + "/download/file/txt";
    
    @POST(BooksAPI.URL_FIND_BOOKS)
    Call<BooksResponse> find(@Body HashMap<String, Object> data);
    
    
    @POST(BooksAPI.URL_GET_RECOMMENDS)
    Call<BooksResponse> getRecommends(@Body HashMap<String, Object> data);
    
    @GET(BooksAPI.URL_GET_INFO)
    Call<BookInfoResponse> getInfo(@Query("book_id") int book_id);
    
    @GET(BooksAPI.URL_DOWNLOAD_FILE)
    Call<ResponseBody> download(@Query("book_id") int book_id);

    @POST(BooksAPI.URL_UPDATE_RECOMMENDS)
    Call<CommonResponse> updateRecommends(@Body CommonListRequest list);
    
    @GET("images/sunset.jpg")
    Call<ResponseBody> testDownload();
}

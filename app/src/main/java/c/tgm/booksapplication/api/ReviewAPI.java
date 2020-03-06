package c.tgm.booksapplication.api;


import java.util.HashMap;

import c.tgm.booksapplication.books.item.adapter.ReviewListAdapter;
import c.tgm.booksapplication.models.response.AuthorListResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.models.response.ReviewListResponse;
import c.tgm.booksapplication.models.response.ReviewResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by NorD on 16.11.2017.
 */

public interface ReviewAPI {
    final String PREFIX = "private/review";
    
    final String URL_ADD = PREFIX + "/add";
    final String URL_EDIT = PREFIX + "/edit";
    final String URL_DELETE = PREFIX + "/delete";
    final String URL_GET = PREFIX + "/get";
    
    @POST(ReviewAPI.URL_ADD)
    Call<ReviewResponse> add(@Body HashMap<String, Object> data);
    
    @PUT(ReviewAPI.URL_EDIT)
    Call<ReviewResponse> edit(@Body HashMap<String, Object> data);
    
    @HTTP(method = "DELETE", path = ReviewAPI.URL_DELETE, hasBody = true)
    Call<CommonResponse> delete(@Body HashMap<String, Object> data);
    
    @GET(ReviewAPI.URL_GET)
    Call<ReviewListResponse> get(@Query("page") int page, @Query("page_size") int page_size);
}

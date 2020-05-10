package c.tgm.booksapplication.api;


import java.util.HashMap;

import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;
import c.tgm.booksapplication.models.response.BookListResponse;
import c.tgm.booksapplication.models.response.BooksResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.models.response.PromotionListResponse;
import c.tgm.booksapplication.models.response.PromotionResponse;
import c.tgm.booksapplication.models.response.ReviewListResponse;
import c.tgm.booksapplication.models.response.ReviewResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by NorD on 16.11.2017.
 */

public interface PromotionAPI {
    final String PREFIX = "moderator/promotion";
    
    final String URL_ADD = PREFIX + "/add";
    final String URL_DELETE = PREFIX + "/delete";
    final String URL_GET = PREFIX + "/get";
    final String URL_GET_BOOKS = PREFIX + "/get/books";
    
    @POST(PromotionAPI.URL_ADD)
    Call<PromotionResponse> add(@Body PromotionAddRequest request);
    
    @HTTP(method = "DELETE", path = PromotionAPI.URL_DELETE, hasBody = true)
    Call<CommonResponse> delete(@Body HashMap<String, Object> data);
    
    @GET(PromotionAPI.URL_GET)
    Call<PromotionListResponse> get(@Query("page") int page, @Query("page_size") int page_size);

    @GET(PromotionAPI.URL_GET_BOOKS)
    Call<BooksResponse> getBooks(@Query("promotion_id") int promotion_id,
                                 @Query("page") int page, @Query("page_size") int page_size);
}

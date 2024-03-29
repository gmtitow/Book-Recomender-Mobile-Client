package c.tgm.booksapplication.api;


import java.util.HashMap;

import c.tgm.booksapplication.models.response.AuthorListResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by NorD on 16.11.2017.
 */

public interface AuthorAPI {
    final String PREFIX = "author";
    
    final String URL_FIND_AUTHORS = PREFIX + "/find";
    
    @POST(AuthorAPI.URL_FIND_AUTHORS)
    Call<AuthorListResponse> find(@Body HashMap<String, Object> data);
}

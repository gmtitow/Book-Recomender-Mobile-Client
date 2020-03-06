package c.tgm.booksapplication.api;


import java.util.HashMap;

import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.response.AuthResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.models.response.GenreResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by NorD on 16.11.2017.
 */

public interface GenreAPI {
    final String PREFIX = "genre";
    
    final String URL_GET = PREFIX + "/get";
    
    @GET(GenreAPI.URL_GET)
    Call<GenreResponse> getAll();
}

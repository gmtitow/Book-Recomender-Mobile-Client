package c.tgm.booksapplication.api;


import java.util.HashMap;

import c.tgm.booksapplication.models.response.AuthResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by NorD on 16.11.2017.
 */

public interface AuthenticationAPI {
    
    final String PREFIX = "authentication";
    
    final String URL_LOGIN = PREFIX + "/login";
    final String URL_CHECK_LOGIN = PREFIX + "/check/login";
    final String URL_GET_ACTIVATION_CODE = PREFIX + "/get/activation-code";
    final String URL_CHECK_ACTIVATION_CODE = PREFIX + "/check/activation-code";
    final String URL_REGISTER = PREFIX + "/register";
    
    @POST(AuthenticationAPI.URL_LOGIN)
    Call<AuthResponse> login(@Body HashMap<String, Object> data);
    
    @POST(AuthenticationAPI.URL_CHECK_LOGIN)
    Call<CommonResponse> checkLogin(@Body HashMap<String, Object> data);
    
    @POST(AuthenticationAPI.URL_GET_ACTIVATION_CODE)
    Call<CommonResponse> getActivationCode(@Body HashMap<String, Object> data);
    
    @POST(AuthenticationAPI.URL_CHECK_ACTIVATION_CODE)
    Call<CommonResponse> checkActivationCode(@Body HashMap<String, Object> data);
    
    @POST(AuthenticationAPI.URL_REGISTER)
    Call<AuthResponse> register(@Body HashMap<String, Object> data);
}

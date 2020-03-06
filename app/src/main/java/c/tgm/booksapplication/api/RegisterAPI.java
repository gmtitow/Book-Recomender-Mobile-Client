package c.tgm.booksapplication.api;


import c.tgm.booksapplication.models.data.AuthData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by NorD on 16.11.2017.
 */

public interface RegisterAPI {
    final String PARAM_LOGIN = "login";
    final String PARAM_PASSWORD = "password";
    final String PARAM_RESET_CODE = "resetcode";
    final String PARAM_ACTIVATION_CODE = "activationCode";
    
    
    final String GET_RESET_CODE = "getResetPasswordCode";
    final String GET_ACTIVATION_CODE = "getActivationCode";
    final String CHECK_RESET_CODE = "checkResetPasswordCode";
    final String CHANGE_PASSWORD = "changePassword";
    final String CHECK_LOGIN = "checkLogin";
    final String ACTIVATE_LINK = "checkActivationCode";
    
    @FormUrlEncoded
    @POST("index")
    Call<AuthData> register(@Field(PARAM_LOGIN) String login,
                            @Field(PARAM_PASSWORD) String password);
    
//    @POST(GET_ACTIVATION_CODE)
//    Call<StatusWithTime> getActivationCode();
//
//    @FormUrlEncoded
//    @POST(ACTIVATE_LINK)
//    Call<Status> checkActivationCode(@Field(PARAM_ACTIVATION_CODE) String activationCode,
//                              @Field(PARAM_LOGIN) String login);
//
//    @FormUrlEncoded
//    @POST(GET_RESET_CODE)
//    Call<StatusWithTime> getResetPasswordCode(@Field(PARAM_LOGIN) String login);
//
//    @FormUrlEncoded
//    @POST(CHECK_RESET_CODE)
//    Call<Status> checkResetPasswordCode(@Field(PARAM_LOGIN) String login,
//                                        @Field(PARAM_RESET_CODE) String resetCode);
//
//    @FormUrlEncoded
//    @POST(CHANGE_PASSWORD)
//    Call<Status> changePassword(@Field(PARAM_LOGIN) String login,
//                                @Field(PARAM_RESET_CODE) String resetCode,
//                                @Field(PARAM_PASSWORD) String password);
//
//    @FormUrlEncoded
//    @POST(CHECK_LOGIN)
//    Call<Status> checkLogin(@Field(PARAM_LOGIN) String login);
}

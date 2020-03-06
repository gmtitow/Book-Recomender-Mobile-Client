package c.tgm.booksapplication.api;

import java.util.LinkedHashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by NorD on 16.11.2017.
 */

public interface serviceAPI {
    
//    @FormUrlEncoded
//    @POST(UserRequest.URL_SERVICES_GET)
//    Call<ServicesByQuery> getServicesByUserQuery(@Field(UserRequest.PARAM_QUERY_TYPE) int typeQuery,
//                                                 @Field(UserRequest.PARAM_USER_QUERY) String userQuery,
//                                                 @FieldMap LinkedHashMap<String, Double> center,
//                                                 @FieldMap LinkedHashMap<String, Double> diagonal);
//
//    @FormUrlEncoded
//    @POST(UserRequest.URL_SERVICES_GET)
//    Call<ServicesByQuery> getServicesByCategories(@Field(UserRequest.PARAM_QUERY_TYPE) int typeQuery,
//                                                  @FieldMap LinkedHashMap<String, Long> categories,
//                                                  @FieldMap LinkedHashMap<String, Double> center,
//                                                  @FieldMap LinkedHashMap<String, Double> diagonal);
//
//    @FormUrlEncoded
//    @POST(UserRequest.URL_SERVICES_GET)
//    Call<ServicesByQuery> getServicesByAutocomplite(@Field(UserRequest.PARAM_QUERY_TYPE) int typeQuery,
//                                                    @Field(UserRequest.PARAM_ID) long id,
//                                                    @Field(UserRequest.PARAM_TYPE) String type,
//                                                    @FieldMap LinkedHashMap<String, Double> center,
//                                                    @FieldMap LinkedHashMap<String, Double> diagonal);
//
//    @FormUrlEncoded
//    @POST(UserRequest.URL_USER_LOCATION_GET_AUTOCOMPLITE_WITH_SERVICES)
//    Call<AutocompleteWithStatus> getAutocomplete(@Field(UserRequest.PARAM_QUERY_TYPE) int typeQuery,
//                                                 @Field(UserRequest.PARAM_QUERY) String userQuery,
//                                                 @FieldMap LinkedHashMap<String, Double> center,
//                                                 @FieldMap LinkedHashMap<String, Double> diagonal);
//
//    @FormUrlEncoded
//    @POST(UserRequest.URL_USER_LOCATION_GET_AUTOCOMPLITE_WITH_SERVICES)
//    Call<String> getAutocompleteTest(@Field(UserRequest.PARAM_QUERY_TYPE) int typeQuery,
//                                     @Field(UserRequest.PARAM_QUERY) String userQuery,
//                                     @FieldMap LinkedHashMap<String, Double> center,
//                                     @FieldMap LinkedHashMap<String, Double> diagonal);
//
//    @FormUrlEncoded
//    @POST(UserRequest.URL_LOGIN)
//    Call<AuthData>login(@Field(UserRequest.PARAM_LOGIN) String login,
//                        @Field(UserRequest.PARAM_PASSWORD) String password);
//
//    @POST(UserRequest.URL_LOGOUT)
//    Call<Status> logout();
//
//    @GET(UserRequest.URL_CATEGORIES_GET)
//    Call<Categories> getCategories();
//
//    @GET(UserRequest.URL_SERVICES_GET_INFO)
//    Call<ServiceInfo> getServiceInfo(@Path(UserRequest.PARAM_SERVICE_ID) long serviceId);
}

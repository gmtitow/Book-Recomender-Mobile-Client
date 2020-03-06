package c.tgm.booksapplication.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.tgm.booksapplication.any.UserRequest.URL_SERVER;


/**
 * Created by NorD on 16.11.2017.
 */

public class AuthorizationController extends Controller {
    
    private static final String TAG = "Controller";
    
    public AuthorizationController(final Context context) {
        super(context);
    }
    
    private static final String BASE_URL = URL_SERVER + "/";
    
    public static AuthenticationAPI getGsonAPI() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        AuthenticationAPI api = retrofit.create(AuthenticationAPI.class);
        return api;
    }
}
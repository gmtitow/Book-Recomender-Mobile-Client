package c.tgm.booksapplication.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.tgm.booksapplication.any.UserRequest.URL_SERVER;

public class SomeController extends Controller {
    
    private static final String TAG = "Controller";
    
    public SomeController(final Context context) {
        super(context);
    }
    
    private static final String BASE_URL = URL_SERVER + "/";
    
    public static <T> T getGsonAPI(Class<T> classOfT) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        T api = retrofit.create(classOfT);
        return api;
    }
}

//
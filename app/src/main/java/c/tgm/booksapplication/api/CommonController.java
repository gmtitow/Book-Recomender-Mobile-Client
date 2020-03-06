package c.tgm.booksapplication.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static c.tgm.booksapplication.any.UserRequest.URL_HOST;
import static c.tgm.booksapplication.any.UserRequest.URL_SCHEME;

/**
 * Created by NorD on 16.11.2017.
 */

public class CommonController extends Controller {
    
    private static final String TAG = "Controller";
    
    public CommonController(final Context context) {
        super(context);
    }
    
    private static final HttpUrl url = new HttpUrl.Builder().scheme(URL_SCHEME).host(URL_HOST).build();
    
//    public static serviceAPI getStaticAPI() {
//        ScalarsConverterFactory factory = ScalarsConverterFactory.create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(factory)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(client)
//                .build();
//        serviceAPI api = retrofit.create(serviceAPI.class);
//        return api;
//    }
    
    public static serviceAPI getGsonAPI() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        serviceAPI api = retrofit.create(serviceAPI.class);
        return api;
    }
    
    public static serviceAPI getApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        serviceAPI itableApi = retrofit.create(serviceAPI.class);
        return itableApi;
    }
}
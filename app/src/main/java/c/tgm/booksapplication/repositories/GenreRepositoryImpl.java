package c.tgm.booksapplication.repositories;

import com.google.gson.Gson;

import java.io.IOException;

import c.tgm.booksapplication.api.GenreAPI;
import c.tgm.booksapplication.api.SomeController;
import c.tgm.booksapplication.models.response.GenreResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreRepositoryImpl implements GenreRepository {
    
    private GenreSaver mSaver;
    private Call lastCall;
    public GenreRepositoryImpl(GenreSaver saver) {
        mSaver = saver;
    }
    
    @Override
    public void getGenres() {
        GenreAPI api = SomeController.getGsonAPI(GenreAPI.class);
    
        lastCall = api.getAll();
    
        lastCall.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                GenreResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),GenreResponse.class);
                    }catch(Exception e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    resp = response.body();
                }
            
                if (resp.getSuccess()==null || resp.getSuccess().equals(false)) {
                
                } else {
                    mSaver.saveGenres(resp.getData());
                }
            }
        
            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
//                int k = 10;
            }
        });
    }
    
    @Override
    public void cancelRequest() {
        lastCall.cancel();
    }
}

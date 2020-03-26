package c.tgm.booksapplication.authors.list;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import c.tgm.booksapplication.api.AuthorAPI;
import c.tgm.booksapplication.api.SomeController;
import c.tgm.booksapplication.models.response.AuthorListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorRepositoryImpl implements AuthorRepository {
    
    private AuthorPresenterRepo mPresenter;
    private Call lastCall;
    private Callback lastCallback;
    public AuthorRepositoryImpl(AuthorPresenterRepo presenter) {
        mPresenter = presenter;
    }
    
    @Override
    public void getAuthors(String query, Long genreId, int page, int page_size, final boolean rewrite) {
        AuthorAPI api = SomeController.getGsonAPI(AuthorAPI.class);
    
        HashMap<String,Object> data = new HashMap<>();
        data.put("query", query);
        data.put("genre_id", genreId);
        data.put("page", page);
        data.put("page_size", page_size);
    
        lastCall = api.find(data);
    
        lastCallback = new retrofit2.Callback<AuthorListResponse>() {
            @Override
            public void onResponse(Call<AuthorListResponse> call, Response<AuthorListResponse> response) {
                AuthorListResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),AuthorListResponse.class);
                    }catch(IOException e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    resp = response.body();
                }
        
                if (resp.getSuccess()==null || resp.getSuccess().equals(false)) {
                    mPresenter.onError(resp.getErrorDescription());
                } else {
                    mPresenter.rememberAuthors(resp.getData(), rewrite);
                }
            }
    
            @Override
            public void onFailure(Call<AuthorListResponse> call, Throwable t) {
                mPresenter.onError(t.getMessage());
            }
        };
    
        lastCall.enqueue(lastCallback);
    }
    
    @Override
    public void cancelRequest() {
        lastCall.cancel();
    }
    
    @Override
    public void retry() {
        lastCall.clone().enqueue(lastCallback);
    }
}

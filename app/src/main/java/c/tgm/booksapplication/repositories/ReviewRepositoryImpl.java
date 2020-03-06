package c.tgm.booksapplication.repositories;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import c.tgm.booksapplication.api.ReviewAPI;
import c.tgm.booksapplication.api.SomeController;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.models.response.ReviewListResponse;
import c.tgm.booksapplication.models.response.ReviewResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewRepositoryImpl implements ReviewRepository {
    private ReviewPresenterRepo mPresenter;
    private Call lastCall;
    private Callback lastCallback;
    
    
    public ReviewRepositoryImpl(ReviewPresenterRepo presenter) {
        mPresenter = presenter;
    }
    
    @Override
    public void addReview(int rating, String reviewText, int bookId) {
        ReviewAPI api = SomeController.getGsonAPI(ReviewAPI.class);
    
        HashMap<String,Object> data = new HashMap<>();
        data.put("rating", rating);
        data.put("review_text", reviewText);
        data.put("book_id", bookId);
    
        lastCall = api.add(data);
    
        lastCallback = new retrofit2.Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                ReviewResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),ReviewResponse.class);
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
                    mPresenter.onAddReview(resp.getData());
                }
            }
        
            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                mPresenter.onError(t.getMessage());
            }
        };
    
        lastCall.enqueue(lastCallback);
    }
    
    @Override
    public void editReview(int reviewId, int rating, String reviewText) {
        ReviewAPI api = SomeController.getGsonAPI(ReviewAPI.class);
    
        HashMap<String,Object> data = new HashMap<>();
        data.put("rating", rating);
        data.put("review_text", reviewText);
        data.put("review_id", reviewId);
    
        lastCall = api.edit(data);
    
        lastCallback = new retrofit2.Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                ReviewResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),ReviewResponse.class);
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
                    mPresenter.onEditReview(resp.getData());
                }
            }
        
            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                mPresenter.onError(t.getMessage());
            }
        };
    
        lastCall.enqueue(lastCallback);
    }
    
    @Override
    public void deleteReview(final int reviewId) {
        ReviewAPI api = SomeController.getGsonAPI(ReviewAPI.class);
    
        HashMap<String,Object> data = new HashMap<>();
        data.put("review_id", reviewId);
    
        lastCall = api.delete(data);
    
        lastCallback = new retrofit2.Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),CommonResponse.class);
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
                    mPresenter.onDeleteReview(reviewId);
                }
            }
        
            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                mPresenter.onError(t.getMessage());
            }
        };
    
        lastCall.enqueue(lastCallback);
    }
    
    @Override
    public void getUsersReviews(int page, int page_size, final boolean rewrite) {
        ReviewAPI api = SomeController.getGsonAPI(ReviewAPI.class);
    
    
        lastCall = api.get(page,page_size);
    
        lastCallback = new retrofit2.Callback<ReviewListResponse>() {
            @Override
            public void onResponse(Call<ReviewListResponse> call, Response<ReviewListResponse> response) {
                ReviewListResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),ReviewListResponse.class);
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
                    mPresenter.onGetReviewList(resp.getData(),rewrite);
                }
            }
        
            @Override
            public void onFailure(Call<ReviewListResponse> call, Throwable t) {
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

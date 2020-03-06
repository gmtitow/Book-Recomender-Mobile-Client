package c.tgm.booksapplication.books;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.any.Utils;
import c.tgm.booksapplication.api.BooksAPI;
import c.tgm.booksapplication.api.SomeController;
import c.tgm.booksapplication.books.list.BookPresenterRepo;
import c.tgm.booksapplication.models.response.BookInfoResponse;
import c.tgm.booksapplication.models.response.BookListResponse;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepositoryImpl implements BookRepository {
    
    private BookPresenterRepo mPresenter;
    private Call lastCall;
    private Callback lastCallback;
    public BookRepositoryImpl(BookPresenterRepo presenter) {
        mPresenter = presenter;
    }
    
    @Override
    public void getBooks(String query, Integer authorId, Long genreId, int page, int page_size, final boolean rewrite) {
        BooksAPI api = SomeController.getGsonAPI(BooksAPI.class);
    
        HashMap<String,Object> data = new HashMap<>();
        data.put("query", query);
        data.put("author_id", authorId);
        data.put("genre_id", genreId);
        data.put("page", page);
        data.put("page_size", page_size);
    
        lastCall = api.find(data);
    
        lastCallback = new retrofit2.Callback<BookListResponse>() {
            @Override
            public void onResponse(Call<BookListResponse> call, Response<BookListResponse> response) {
                BookListResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),BookListResponse.class);
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
                    mPresenter.rememberBooks(resp.getData(), rewrite);
                }
            }
    
            @Override
            public void onFailure(Call<BookListResponse> call, Throwable t) {
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
    public void getRecommends(String query, Long genreId, int page, int page_size, final boolean rewrite) {
        BooksAPI api = SomeController.getGsonAPI(BooksAPI.class);
        
        HashMap<String,Object> data = new HashMap<>();
        data.put("query", query);
        data.put("genre_id", genreId);
        data.put("page", page);
        data.put("page_size", page_size);
        
        lastCall = api.getRecommends(data);
        
        lastCallback = new retrofit2.Callback<BookListResponse>() {
            @Override
            public void onResponse(Call<BookListResponse> call, Response<BookListResponse> response) {
                BookListResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),BookListResponse.class);
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
                    mPresenter.rememberBooks(resp.getData(), rewrite);
                }
            }
    
            @Override
            public void onFailure(Call<BookListResponse> call, Throwable t) {
                mPresenter.onError(t.getMessage());
            }
        };
        
        lastCall.enqueue(lastCallback);
    }
    
    @Override
    public void getBookInfo(int book_id) {
        BooksAPI api = SomeController.getGsonAPI(BooksAPI.class);
        
        lastCall = api.getInfo(book_id);
        
        lastCallback = new retrofit2.Callback<BookInfoResponse>() {
            @Override
            public void onResponse(Call<BookInfoResponse> call, Response<BookInfoResponse> response) {
                BookInfoResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),BookInfoResponse.class);
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
                    mPresenter.rememberBook(resp.getData());
                }
            }
    
            @Override
            public void onFailure(Call<BookInfoResponse> call, Throwable t) {
                mPresenter.onError(t.getMessage());
            }
        };
        
        lastCall.enqueue(lastCallback);
    }
    
    @Override
    public void downloadFile(final String filename, final int bookId) {
//        BooksAPI api = SomeController.getGsonAPI(BooksAPI.class);
//
//        lastCall = api.testDownload();
//
//        lastCallback = new retrofit2.Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                File path = Environment.getExternalStorageDirectory();
//                File file = new File(path, filename+".txt");
//
//                Utils.writeResponseBodyToDisk(response.body(),file);
//
//                int k = 100;
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                mPresenter.onError(t.getMessage());
//            }
//        };
//
//        lastCall.enqueue(lastCallback);
    }
    
    @Override
    public void retry() {
        lastCall.clone().enqueue(lastCallback);
    }
}

package c.tgm.booksapplication.books;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import c.tgm.booksapplication.api.BooksAPI;
import c.tgm.booksapplication.api.SomeController;
import c.tgm.booksapplication.models.request.book_list.CommonListRequest;
import c.tgm.booksapplication.models.response.BookInfoResponse;
import c.tgm.booksapplication.models.response.BooksResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.repositories.Repository;
import c.tgm.booksapplication.repositories.RepositoryCallImpl;
import c.tgm.booksapplication.repositories.RepositoryImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepositoryImpl extends RepositoryImpl implements BookRepository {
    
    private BookPresenterRepo mPresenter;
    private BooksAPI mApi;
    public BookRepositoryImpl(BookPresenterRepo presenter) {
        mPresenter = presenter;
        mApi = getAPI(BooksAPI.class);
    }

    @Override
    public void getBooks(String query, Integer authorId, Long genreId, List<Integer> book_ids , int page, int page_size) {
        BooksAPI api = SomeController.getGsonAPI(BooksAPI.class);

        HashMap<String,Object> data = new HashMap<>();
        data.put("query", query);
        data.put("author_id", authorId);
        data.put("genre_id", genreId);
        data.put("book_ids", book_ids);
        data.put("page", page);
        data.put("page_size", page_size);

        Call call = mApi.find(data);
        RepositoryCallImpl respCall = new RepositoryCallImpl<>(
                call, BooksResponse.class, mPresenter::rememberBooks, mPresenter::onError, this);
        respCall.call();
    }
    
    @Override
    public void getBooks(String query, Integer authorId, Long genreId, int page, int page_size) {
        getBooks(query,authorId,genreId,null,page,page_size);
    }
    
    @Override
    public void getRecommends(String query, Long listId, Long genreId, int page, int page_size) {

        HashMap<String,Object> data = new HashMap<>();
        data.put("query", query);
        data.put("genre_id", genreId);
        data.put("list_id", listId);
        data.put("page", page);
        data.put("page_size", page_size);

        Call call = mApi.getRecommends(data);
        RepositoryCallImpl respCall = new RepositoryCallImpl<>(
                call, BooksResponse.class, mPresenter::rememberBooks, mPresenter::onError, this);
        respCall.call();
    }
    
    @Override
    public void getBookInfo(int book_id) {

        Call call = mApi.getInfo(book_id);
        RepositoryCallImpl respCall = new RepositoryCallImpl<>(
                call, BookInfoResponse.class, mPresenter::rememberBook, mPresenter::onError, this);
        respCall.call();
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
    public void updateRecommendList(int listId) {
        Call call = mApi.updateRecommends(new CommonListRequest(listId));
        RepositoryCallImpl respCall = new RepositoryCallImpl<>(
                call, CommonResponse.class, mPresenter::onUpdateRecommends, mPresenter::onError, this);
        respCall.call();
    }
}

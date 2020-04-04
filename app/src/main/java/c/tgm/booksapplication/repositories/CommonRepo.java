package c.tgm.booksapplication.repositories;

import java.util.ArrayList;

import retrofit2.Call;

public interface CommonRepo {
    void onError(String errorDescription, RepositoryCall call);
}

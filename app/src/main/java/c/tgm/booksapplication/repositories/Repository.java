package c.tgm.booksapplication.repositories;

import retrofit2.Call;

public interface Repository {

    void cancelRequest();

    void retry();

    void retry(Call call);
}

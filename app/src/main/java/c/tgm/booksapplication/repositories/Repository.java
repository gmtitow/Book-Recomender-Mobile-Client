package c.tgm.booksapplication.repositories;

public interface Repository {

    void cancelRequest();

    void retry();
}

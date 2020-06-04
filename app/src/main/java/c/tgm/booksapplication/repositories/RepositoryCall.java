package c.tgm.booksapplication.repositories;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import retrofit2.Call;

public interface RepositoryCall {

    void call();

    int getAttemptsCount();
}

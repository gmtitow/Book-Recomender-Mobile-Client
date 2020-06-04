package c.tgm.booksapplication.repositories;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import c.tgm.booksapplication.models.response.AbstractResponse;
import retrofit2.Call;

public class RepositoryCallImpl<APIData, APIResponse extends AbstractResponse<APIData>> implements RepositoryCall {

    private Call mCall;
    private final Class<APIResponse> APIResponseClass;
    private final Consumer<APIData> onSuccessMethod;
    private final BiConsumer<String,RepositoryCall> onErrorMethod;

    private int attempts = 0;

    private RepositoryImpl mRepository;

    public RepositoryCallImpl(Call call,
                              Class<APIResponse> APIResponseClass,
                              Consumer<APIData> onSuccessMethod,
                              BiConsumer<String, RepositoryCall> onErrorMethod,
                              RepositoryImpl repository) {
        mCall = call;
        this.APIResponseClass = APIResponseClass;
        this.onSuccessMethod = onSuccessMethod;
        this.onErrorMethod = onErrorMethod;
        mRepository = repository;
    }

    @Override
    public int getAttemptsCount() {
        return attempts;
    }

    @Override
    public void call() {
        if (attempts == 3) {
            return;
        }

        if (mCall.isExecuted()) {
            mCall = mCall.clone();
        }
        attempts++;
        mRepository.callAPIMethod(mCall,APIResponseClass,onSuccessMethod,onErrorMethod,this);
    }
}

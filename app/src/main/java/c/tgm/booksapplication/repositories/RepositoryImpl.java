package c.tgm.booksapplication.repositories;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import c.tgm.booksapplication.api.SomeController;
import c.tgm.booksapplication.models.request.AbstractRequest;
import c.tgm.booksapplication.models.response.AbstractResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract public class RepositoryImpl implements Repository {
    protected Call lastCall;
    protected Callback lastCallback;

    protected <API> API getAPI(Class<API> classAPI){
        return SomeController.getGsonAPI(classAPI);
    }

    @Override
    public void cancelRequest() {
        lastCall.cancel();
    }

    @Override
    public void retry() {
        this.lastCall.clone().enqueue(lastCallback);
    }

    protected <APIData, APIResponse extends AbstractResponse<APIData>>
    void
    callAPIMethod (
            Supplier<Call> ApiMethod,
            final Class<APIResponse> APIResponseClass,
            final Consumer<APIData> onSuccessMethod,
            final Consumer<String> onErrorMethod
    ) {
        this.callAPIMethod(ApiMethod.get(),APIResponseClass,onSuccessMethod,onErrorMethod);
    }

    protected <APIData, APIResponse extends AbstractResponse<APIData>,
            Request>
    void
    callAPIMethod (
            Function<Request,Call> ApiMethod,
            final Request request,
            final Class<APIResponse> APIResponseClass,
            final Consumer<APIData> onSuccessMethod,
            final Consumer<String> onErrorMethod
    ) {
        this.callAPIMethod(ApiMethod.apply(request),APIResponseClass,onSuccessMethod,onErrorMethod);
    }

    protected <APIData, APIResponse extends AbstractResponse<APIData>>
    void
    callAPIMethod (
            Call call,
            final Class<APIResponse> APIResponseClass,
            final Consumer<APIData> onSuccessMethod,
            final Consumer<String> onErrorMethod
    ) {
        this.lastCall = call;

        this.lastCallback = new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(), APIResponseClass);
                    }catch(IOException e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    resp = response.body();
                }

                if (resp.getSuccess()==null || resp.getSuccess().equals(false)) {
                    onErrorMethod.accept(resp.getErrorDescription());
                } else {
                    onSuccessMethod.accept(resp.getData());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                onErrorMethod.accept(t.getMessage());
            }
        };

        this.lastCall.enqueue(this.lastCallback);
    }
}

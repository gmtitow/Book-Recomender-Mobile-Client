package c.tgm.booksapplication.authentication.authorization;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;

import c.tgm.booksapplication.api.AuthorizationController;
import c.tgm.booksapplication.api.AuthenticationAPI;
import c.tgm.booksapplication.authentication.authorization.events.LoginErrorEvent;
import c.tgm.booksapplication.authentication.authorization.events.LoginSuccessEvent;
import c.tgm.booksapplication.models.response.AuthResponse;
import retrofit2.Call;
import retrofit2.Response;

public class AuthorizationRepositoryImpl implements AuthorizationRepository {
    private final String unknownError = "unknown error";
    @Override
    public void login(String login, String password) {
        AuthenticationAPI api = AuthorizationController.getGsonAPI();
    
        HashMap<String,Object> data = new HashMap<>();
        data.put("login", login);
        data.put("password", password);
        
    
        api.login(data).enqueue(new retrofit2.Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                
                if (response.isSuccessful()) {
    
                    if (!response.body().getSuccess().equals(true)) {
                        EventBus.getDefault().post(new LoginErrorEvent(
                                response.body().getErrorDescription().isEmpty() ? response.body().getErrorDescription() : unknownError));
                    } else {
                        EventBus.getDefault().post(new LoginSuccessEvent(response.body().getData()));
                    }
                } else {
                    try {
                        EventBus.getDefault().post(new LoginErrorEvent(
                                response.errorBody().string()));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                EventBus.getDefault().post(new LoginErrorEvent(t.getMessage()));
            }
        });
    }
}

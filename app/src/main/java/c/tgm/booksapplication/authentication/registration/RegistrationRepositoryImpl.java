package c.tgm.booksapplication.authentication.registration;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;

import c.tgm.booksapplication.api.AuthenticationAPI;
import c.tgm.booksapplication.api.AuthorizationController;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterActivationErrorEvent;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterActivationSuccessEvent;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterGetActivationCodeErrorEvent;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterGetActivationCodeSuccessEvent;
import c.tgm.booksapplication.authentication.registration.password.events.RegisterErrorEvent;
import c.tgm.booksapplication.authentication.registration.password.events.RegisterSuccessEvent;
import c.tgm.booksapplication.events_for_bus.ErrorEvent;
import c.tgm.booksapplication.models.response.AuthResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import retrofit2.Call;
import retrofit2.Response;

public class RegistrationRepositoryImpl implements RegistrationRepository {
    private final String unknownError = "unknown error";
    @Override
    public void register(String login, String password, String activationCode) {
        AuthenticationAPI api = AuthorizationController.getGsonAPI();
        
        HashMap<String,Object> data = new HashMap<>();
        data.put("login", login);
        data.put("activation_code", activationCode);
        data.put("password", password);
        
        api.register(data).enqueue(new retrofit2.Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse resp;
                if (!response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        resp = gson.fromJson(response.errorBody().string(),AuthResponse.class);
                    }catch(IOException e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    resp = response.body();
                }
    
                if (resp.getSuccess()==null || !resp.getSuccess().equals(true)) {
                    EventBus.getDefault().post(new RegisterErrorEvent((resp.getErrorDescription().isEmpty() ?
                            unknownError: resp.getErrorDescription() ),resp.getError()));
                } else {
                    EventBus.getDefault().post(new RegisterSuccessEvent(resp.getData()));
                }
                
//                if (!response.body().getStatus().equals(Status.STATUS_OK)) {
//                    EventBus.getDefault().post(new RegisterErrorEvent(response.body().getErrors().size()>0?
//                            response.body().getErrors().get(0):unknownError,response.body().getStatus()));
//
//                } else {
//                    EventBus.getDefault().post(new RegisterSuccessEvent(response.body()));
//                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                EventBus.getDefault().post(new RegisterErrorEvent(t.getMessage(),null));
            }
        });
    }
    
    @Override
    public void checkActivationCode(String activationCode, String login) {
        AuthenticationAPI api = AuthorizationController.getGsonAPI();
    
        HashMap<String,Object> data = new HashMap<>();
        data.put("login", login);
        data.put("activation_code", activationCode);

        api.checkActivationCode(data).enqueue(new retrofit2.Callback<CommonResponse>() {
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
                
                if (resp.getSuccess()==null||!resp.getSuccess().equals(true)) {
                    EventBus.getDefault().post(new RegisterActivationErrorEvent(resp.getErrorDescription().isEmpty() ?
                            unknownError: resp.getErrorDescription() ));
                } else {
                    EventBus.getDefault().post(new RegisterActivationSuccessEvent());
                }
            }
            
            //EventBus.getDefault().post(new RegisterActivationSuccessEvent());
            
            //EventBus.getDefault().post(new RegisterActivationErrorEvent(response.body().getErrors().size()>0?
            //                            response.body().getErrors().get(0):unknownError));

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                EventBus.getDefault().post(new RegisterActivationErrorEvent(t.getMessage()));
            }
        });
    }
    
    @Override
    public void replyActivationCode(String login) {
        AuthenticationAPI api = AuthorizationController.getGsonAPI();
        
        HashMap<String,Object> data = new HashMap<>();
        data.put("login", login);
        
        api.getActivationCode(data).enqueue(new retrofit2.Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getSuccess().equals(true)) {
                        EventBus.getDefault().post(new RegisterGetActivationCodeErrorEvent(response.body().getErrorDescription().isEmpty() ?
                                unknownError: response.body().getErrorDescription() ));
                    } else {
                        EventBus.getDefault().post(new RegisterGetActivationCodeSuccessEvent());
                    }
                } else {
                    try {
                        Gson gson = new Gson();
                        CommonResponse resp = gson.fromJson(response.errorBody().string(),CommonResponse.class);
                        EventBus.getDefault().post(new RegisterGetActivationCodeErrorEvent(resp.getErrorDescription().isEmpty() ?
                                unknownError: resp.getErrorDescription() ));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                EventBus.getDefault().post(new RegisterGetActivationCodeErrorEvent(t.getMessage()));
            }
        });
    }
    
    @Override
    public void checkLogin(final String login) {
        AuthenticationAPI api = AuthorizationController.getGsonAPI();
    
        HashMap<String,Object> data = new HashMap<>();
        data.put("login", login);
        
        api.checkLogin(data).enqueue(new retrofit2.Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getSuccess().equals(true)) {
                        EventBus.getDefault().post(new ErrorEvent(response.body().getErrorDescription().isEmpty() ?
                                unknownError: response.body().getErrorDescription() ));
                    } else {
//                        EventBus.getDefault().post(new SuccessEvent());
                        
                        replyActivationCode(login);
                    }
                } else {
                    try {
                        Gson gson = new Gson();
                        CommonResponse resp = gson.fromJson(response.errorBody().string(),CommonResponse.class);
                        EventBus.getDefault().post(new ErrorEvent(resp.getErrorDescription().isEmpty() ?
                                unknownError: resp.getErrorDescription() ));
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
            }
        });
    }
}

package c.tgm.booksapplication.authentication.authorization.change_password;

import c.tgm.booksapplication.api.RegisterAPI;
import c.tgm.booksapplication.api.AuthorizationController;

public class ChangePasswordRepositoryImpl implements ChangePasswordRepository {
    private final String unknownError = "unknown error";
    
    @Override
    public void getResetPasswordCode(String login) {
//        RegisterAPI api = AuthorizationController.getGsonAPI();
        
//        api.getResetPasswordCode(login).enqueue(new retrofit2.Callback<StatusWithTime>() {
//            @Override
//            public void onResponse(Call<StatusWithTime> call, Response<StatusWithTime> response) {
//                if (!response.body().getStatus().equals(Status.STATUS_OK)) {
//                    GetResetPasswordCodeFailureEvent  event = new GetResetPasswordCodeFailureEvent(!response.body().getErrors().isEmpty()?
//                            response.body().getErrors().get(0):unknownError);
//
//                    if(response.body().getTimeLeft()!=null){
//                        event.setTimeLeft(response.body().getTimeLeft()*1000);
//                    }
//
//                    EventBus.getDefault().post(event);
//                } else {
//                    EventBus.getDefault().post(new SuccessEvent());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<StatusWithTime> call, Throwable t) {
//                EventBus.getDefault().post(new GetResetPasswordCodeFailureEvent(t.getMessage()));
//            }
//        });
    }
    
    @Override
    public void checkResetPasswordCode(String login, String resetCode) {
//        RegisterAPI api = AuthorizationController.getGsonAPI();
    
//        api.checkResetPasswordCode(login,resetCode).enqueue(new retrofit2.Callback<Status>() {
//            @Override
//            public void onResponse(Call<Status> call, Response<Status> response) {
//                if (!response.body().getStatus().equals(Status.STATUS_OK)) {
//                    EventBus.getDefault().post(new ErrorEvent(!response.body().getErrors().isEmpty()?
//                            response.body().getErrors().get(0):unknownError));
//                } else {
//                    EventBus.getDefault().post(new CheckResetCodeSuccessEvent());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Status> call, Throwable t) {
//                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
//            }
//        });
    }
    
    @Override
    public void changePassword(String login, String resetCode, String password) {
//        RegisterAPI api = AuthorizationController.getGsonAPI();
    
//        api.changePassword(login,resetCode,password).enqueue(new retrofit2.Callback<Status>() {
//            @Override
//            public void onResponse(Call<Status> call, Response<Status> response) {
//                if (!response.body().getStatus().equals(Status.STATUS_OK)) {
//                    EventBus.getDefault().post(new ErrorEvent(!response.body().getErrors().isEmpty()?
//                            response.body().getErrors().get(0):unknownError));
//                } else {
//                    EventBus.getDefault().post(new ChangePasswordSuccessEvent());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Status> call, Throwable t) {
//                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
//            }
//        });
    }
}

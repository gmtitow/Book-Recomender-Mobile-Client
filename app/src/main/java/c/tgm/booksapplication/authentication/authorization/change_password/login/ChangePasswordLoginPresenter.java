package c.tgm.booksapplication.authentication.authorization.change_password.login;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.authentication.authorization.change_password.ChangePasswordRepository;
import c.tgm.booksapplication.authentication.authorization.change_password.ChangePasswordRepositoryImpl;
import c.tgm.booksapplication.authentication.authorization.change_password.code.events.GetResetPasswordCodeFailureEvent;
import c.tgm.booksapplication.authentication.events.ChangePasswordLoginInputEvent;
import c.tgm.booksapplication.events_for_bus.SuccessEvent;

@InjectViewState
public class ChangePasswordLoginPresenter extends MvpPresenter<ChangePasswordLoginView> {
    ChangePasswordLoginModel mModel;
    Context mContext;
    ChangePasswordRepository mRepository;
    
    public ChangePasswordLoginPresenter(){
        mModel = new ChangePasswordLoginModel();
        mRepository = new ChangePasswordRepositoryImpl();
    }
    
    public void onCreate(Context context){
        mContext = context;
    }
    
    public void onDestroy(){
        mContext = null;
    }
    
    public void onLoginChanged(String login){
        mModel.setLogin(login);
        getViewState().changeRegisterButtonEnabled(mModel.inputIsCorrect());
    }
    
    public void goToCode(){
        if(!mModel.inputIsCorrect()){
            getViewState().setLoginError(mContext.getResources().getString(R.string.autentication_authorization_login_incorrect));
            return;
        }
        
        if(mModel.getCurrentLogin().equals(mModel.getLogin())){
            EventBus.getDefault().post(new ChangePasswordLoginInputEvent(mModel.getCurrentLogin()));
            return;
        }
        
        getViewState().showProgress();
        EventBus.getDefault().register(this);
        mModel.setSentLogin(mModel.getLogin());
        mRepository.getResetPasswordCode(mModel.getLogin());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessEvent(SuccessEvent event) {
        EventBus.getDefault().unregister(this);
        
        mModel.setCurrentLogin(mModel.getSentLogin());
        DataStore.setLogin(mModel.getCurrentLogin());
        
        EventBus.getDefault().post(new ChangePasswordLoginInputEvent(mModel.getLogin()));
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetResetPasswordCodeFailureEvent(GetResetPasswordCodeFailureEvent event) {
        EventBus.getDefault().unregister(this);
    
        getViewState().setError(event.getError());
        getViewState().hideProgress();
    
        if(event.getTimeLeft()!= null){
            Date now = new Date();
            Date now2 = new Date(now.getTime()+event.getTimeLeft());
            EventBus.getDefault().post(new ChangePasswordLoginInputEvent(mModel.getLogin()));
        }
    }
}

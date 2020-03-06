package c.tgm.booksapplication.authentication.authorization.change_password.password;

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
import c.tgm.booksapplication.authentication.authorization.change_password.password.events.ChangePasswordSuccessEvent;
import c.tgm.booksapplication.authentication.events.ChangePasswordEvent;
import c.tgm.booksapplication.events_for_bus.ErrorEvent;


@InjectViewState
public class ChangePasswordPasswordPresenter extends MvpPresenter<ChangePasswordPasswordView> {
    private ChangePasswordPasswordModel mModel;
    private Context mContext;
    private ChangePasswordRepository mRepository;
    
    public ChangePasswordPasswordPresenter(){
        mModel = new ChangePasswordPasswordModel();
        mRepository = new ChangePasswordRepositoryImpl();
    }
    
    public void onCreate(Context context){
        mContext = context;
    
        if(mModel.isAuthDataWait()){
            DataStore.setAuthorizationInfo(context,mModel.getCurrentAuthData(), mModel.getLogin());
            mModel.setAuthDataWait(false);
            mModel.setCurrentAuthData(null);
        }
    }
    
    public void onDestroy(){
        mContext = null;
    }
    
    public void onPasswordChanged(String password){
        mModel.setPassword(password);
        getViewState().changeRegisterButtonEnabled(mModel.inputIsCorrect());
    }
    
    public void onConfirmChanged(String confirm){
        mModel.setConfirm(confirm);
        getViewState().changeRegisterButtonEnabled(mModel.inputIsCorrect());
    }
    
    public void changePassword(){
        if(!mModel.inputIsCorrect()){
            getViewState().setPasswordError(mContext.getResources().getString(R.string.autentication_authorization_password_incorrect));
            return;
        }
        
        getViewState().showProgress();
        EventBus.getDefault().register(this);
        mModel.setPasswordSent(mModel.getPassword());
        mRepository.changePassword(mModel.getLogin(),mModel.getResetCode(),mModel.getPasswordSent());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangePasswordSuccessEvent(ChangePasswordSuccessEvent event) {
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().post(new ChangePasswordEvent());
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        EventBus.getDefault().unregister(this);
        getViewState().setError(event.getError());
        getViewState().hideProgress();
    }
    
    public void rememberData(String login, String resetCode) {
        mModel.setLogin(login);
        mModel.setResetCode(resetCode);
    }
}

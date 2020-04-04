package c.tgm.booksapplication.authentication.registration.password;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.authentication.events.RegisterEvent;
import c.tgm.booksapplication.authentication.registration.RegistrationRepository;
import c.tgm.booksapplication.authentication.registration.RegistrationRepositoryImpl;
import c.tgm.booksapplication.authentication.registration.password.events.RegisterErrorEvent;
import c.tgm.booksapplication.authentication.registration.password.events.RegisterSuccessEvent;
import ru.terrakok.cicerone.commands.Back;


@InjectViewState
public class RegistrationPasswordPresenter extends MvpPresenter<RegistrationPasswordView> {
    private RegistrationPasswordModel mModel;
    private Context mContext;
    private RegistrationRepository mRepository;
    
    public RegistrationPasswordPresenter(){
        mModel = new RegistrationPasswordModel();
        mRepository = new RegistrationRepositoryImpl();
    }
    
    public void rememberLogin(String login, String activationCode){
        mModel.setLogin(login);
        mModel.setCurrentLogin(login);
        mModel.setActivationCode(activationCode);
    }
    
    public void onCreate(Context context){
        mContext = context;
    
        if(mModel.isAuthDataWait()){
            BookApplication.INSTANCE.getDataStore().setAuthorizationInfo(context,mModel.getCurrentAuthData(), mModel.getLogin());
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
    
    public void register(){
        if(!mModel.inputIsCorrect()){
            getViewState().setPasswordError(mContext.getResources().getString(R.string.autentication_authorization_password_incorrect));
            return;
        }
        
        getViewState().showProgress();
        EventBus.getDefault().register(this);
        mRepository.register(mModel.getLogin(),mModel.getPassword(),mModel.getActivationCode());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccessEvent(RegisterSuccessEvent event) {
        if(mContext!= null){
            BookApplication.INSTANCE.getDataStore().setAuthorizationInfo(mContext,event.getAuthData(), mModel.getLogin());
            
        } else{
            mModel.setCurrentAuthData(event.getAuthData());
            mModel.setAuthDataWait(true);
        }
        
        EventBus.getDefault().unregister(this);
//        DataStoreTemp.setLastTimeForActivationCode((new Date()).getTime());
        EventBus.getDefault().post(new RegisterEvent());
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterErrorEvent(RegisterErrorEvent event) {
        EventBus.getDefault().unregister(this);
        getViewState().hideProgress();
        
//        if(event.getError()!=null && event.getStatus().equals(Status.STATUS_ALREADY_EXISTS)){
//            EventBus.getDefault().post(new BackEvent());
//        } else {
            getViewState().setError(event.getErrorDescription());
//        }
    }
}

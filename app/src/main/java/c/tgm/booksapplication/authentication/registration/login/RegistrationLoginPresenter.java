package c.tgm.booksapplication.authentication.registration.login;

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
import c.tgm.booksapplication.authentication.events.RegisterLoginInputEvent;
import c.tgm.booksapplication.authentication.events.goToConfirmCodeEvent;
import c.tgm.booksapplication.authentication.registration.RegistrationRepository;
import c.tgm.booksapplication.authentication.registration.RegistrationRepositoryImpl;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterActivationSuccessEvent;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterGetActivationCodeErrorEvent;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterGetActivationCodeSuccessEvent;
import c.tgm.booksapplication.events_for_bus.ErrorEvent;
import c.tgm.booksapplication.events_for_bus.SuccessEvent;


@InjectViewState
public class RegistrationLoginPresenter extends MvpPresenter<RegistrationLoginView> {
    RegistrationLoginModel mModel;
    Context mContext;
    private RegistrationRepository mRepository;
    
    public RegistrationLoginPresenter(){
        mModel = new RegistrationLoginModel();
        mRepository = new RegistrationRepositoryImpl();
    }
    
    public void rememberLogin(String login){
        mModel.setLogin(login);
        mModel.setCurrentLogin(login);
    }
    
    public void onCreate(Context context){
        mContext = context;
    }
    
    public void onDestroy(){
        mContext = null;
    }
    
    public void onLoginChanged(String login){
        mModel.setCurrentLogin(login);
        getViewState().changeRegisterButtonEnabled(mModel.inputIsCorrect());
    }
    
    public void goToCheckActivation(){
        if(!mModel.inputIsCorrect()){
            getViewState().setLoginError(mContext.getResources().getString(R.string.autentication_authorization_login_incorrect));
            return;
        }
        
        if(BookApplication.INSTANCE.getDataStore().isAuthorized(mContext)) {
            if(mModel.getLogin() == null || mModel.getLogin().trim().equals("")){
                mModel.setLogin(BookApplication.INSTANCE.getDataStore().getLogin());
            }
            
            if (mModel.getLogin().equals(mModel.getCurrentLogin())) {
                EventBus.getDefault().post(new goToConfirmCodeEvent());
                return;
            }
        }
        
        EventBus.getDefault().register(this);
        getViewState().showProgress();
        mRepository.checkLogin(mModel.getCurrentLogin());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessEvent(RegisterGetActivationCodeSuccessEvent event) {
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().post(new RegisterLoginInputEvent(mModel.getCurrentLogin()));
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(RegisterGetActivationCodeErrorEvent event) {
        EventBus.getDefault().unregister(this);
        getViewState().setError(event.getError());
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        EventBus.getDefault().unregister(this);
        getViewState().setError(event.getError());
        getViewState().hideProgress();
    }
}

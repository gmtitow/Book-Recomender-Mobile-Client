package c.tgm.booksapplication.authentication.authorization;

import android.animation.Animator;
import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.authentication.authorization.events.LoginErrorEvent;
import c.tgm.booksapplication.authentication.authorization.events.LoginSuccessEvent;
import c.tgm.booksapplication.authentication.events.GoToChangePasswordEvent;
import c.tgm.booksapplication.authentication.events.GoToRegisterEvent;
import c.tgm.booksapplication.authentication.events.LoginEvent;
import c.tgm.booksapplication.models.data.BookListDao;


@InjectViewState
public class AuthorizationPresenter extends MvpPresenter<AuthorizationView> {
    
    AuthorizationModel mModel;
    AuthorizationRepository mRepository;
    Context mContext;
    
    public AuthorizationPresenter(){
        mModel = new AuthorizationModel();
        mRepository = new AuthorizationRepositoryImpl();
    }
    
    public void onCreate(Context context){
        mContext = context;
        if(mModel.isAuthDataWait()){
            BookApplication.INSTANCE.getDataStore().setAuthorizationInfo(context,mModel.getCurrentAuthData(), mModel.getLogin());
            mModel.setAuthDataWait(false);
            mModel.setCurrentAuthData(null);
        }
    }
    
    public void goToRegister(){
        EventBus.getDefault().post(new GoToRegisterEvent(mModel.getLogin()));
    }
    
    public void onDestroy(){
        mContext = null;
    }
    
    public void onLoginChanged(String login){
        mModel.setLogin(login);
        getViewState().changeEnterButtonEnabled(mModel.inputIsCorrect());
    }
    
    public void onPasswordChanged(String password){
        mModel.setPassword(password);
        getViewState().changeEnterButtonEnabled(mModel.inputIsCorrect());
    }
    
    public void submitLogin(){
        if(!mModel.loginIsCorrect()){
            getViewState().setLoginError(mContext.getResources().getString(R.string.autentication_authorization_login_incorrect));
            return;
        }
        
        if(!mModel.passwordIsCorrect()){
            getViewState().setPasswordError(mContext.getResources().getString(R.string.autentication_authorization_password_incorrect));
            return;
        }
        
        getViewState().showProgress();
        EventBus.getDefault().register(this);
        mRepository.login(mModel.getLogin(),mModel.getPassword());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccessEvent(LoginSuccessEvent event) {
        if(mContext!= null){
            BookApplication.INSTANCE.getDataStore().setAuthorizationInfo(mContext,event.getAuthData(), mModel.getLogin());
        } else{
            mModel.setCurrentAuthData(event.getAuthData());
            mModel.setAuthDataWait(true);
        }
        
        EventBus.getDefault().unregister(this);


        BookListDao genreDAO = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();
        genreDAO.insertOrReplaceInTx(event.getAuthData().getLists());

        EventBus.getDefault().post(new LoginEvent());
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginErrorEvent(LoginErrorEvent event) {
        EventBus.getDefault().unregister(this);
        getViewState().setError(event.getError());
        getViewState().hideProgress();
    }
    
    public void goToChangePassword() {
        EventBus.getDefault().post(new GoToChangePasswordEvent(mModel.getLogin()));
    }
}

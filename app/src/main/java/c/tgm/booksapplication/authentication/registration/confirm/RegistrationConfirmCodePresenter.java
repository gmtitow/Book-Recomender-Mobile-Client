package c.tgm.booksapplication.authentication.registration.confirm;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.Constants;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.any.DataStoreTemp;
import c.tgm.booksapplication.authentication.events.ChangeLoginEvent;
import c.tgm.booksapplication.authentication.events.GoToChangePasswordEvent;
import c.tgm.booksapplication.authentication.events.LoginEvent;
import c.tgm.booksapplication.authentication.registration.RegistrationRepository;
import c.tgm.booksapplication.authentication.registration.RegistrationRepositoryImpl;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterActivationErrorEvent;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterActivationSuccessEvent;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterGetActivationCodeErrorEvent;
import c.tgm.booksapplication.authentication.registration.confirm.events.RegisterGetActivationCodeSuccessEvent;


@InjectViewState
public class RegistrationConfirmCodePresenter extends NavigatorPresenter<RegistrationConfirmCodeView> {
    private RegistrationConfirmCodeModel mModel;
    private Context mContext;
    private RegistrationRepository mRepository;
    private MyTimer currentTimer;
    
    public RegistrationConfirmCodePresenter(){
        mModel = new RegistrationConfirmCodeModel();
        mRepository = new RegistrationRepositoryImpl();
        onCodeSent();
    }
    
    private void onCodeSent(){
        getViewState().setConfirmTimeVisible();
        int milliseconds;
        int maxResendTime = DataStoreTemp.ACTIVATION_CODE_RESEND_TIME;
        try {
            Long lasttime = DataStoreTemp.getLastTimeForActivationCode();
            Date now = new Date();

            milliseconds = (maxResendTime - (int)((now.getTime() - lasttime)));

            if(milliseconds<0)
                milliseconds = 0;

        }catch(Exception e){
            milliseconds = maxResendTime;
        }
        if(currentTimer!=null)
            currentTimer.cancel();
        currentTimer = new MyTimer(milliseconds,1000);
        currentTimer.start();
     }
    
    public void onCreate(Context context){
        mContext = context;
        //EventBus.getDefault().changePassword(this);
    }
    
    public void onDestroy(){
        mContext = null;
        //EventBus.getDefault().unregister(this);
    }
    
    public void onConfirmCodeChanged(String confirmCode){
        mModel.setConfirmCode(confirmCode);
        getViewState().changeSendConfirmButtonEnabled(mModel.inputIsCorrect());
    }
    
    public void sendConfirmCode(){
        if(!mModel.inputIsCorrect()) {
            getViewState().setError(mContext.getResources().getString(
                    R.string.authentication_registration_confirm_code_wrong_activation_code));
            return;
        }
        
        getViewState().showProgress();
        EventBus.getDefault().register(this);
        String login = "";
        if(mModel.getLogin()== null || mModel.getLogin().trim().equals("")){
            login = BookApplication.INSTANCE.getDataStore().getLogin();
        } else{
            login = mModel.getLogin();
        }
        
        mRepository.checkActivationCode(mModel.getConfirmCode(),login);
    }
    
    public void rememberLogin(String login){
        mModel.setLogin(login);
    }
    
    public void replyConfirmCode(){
        getViewState().showProgress();
        EventBus.getDefault().register(this);
        mRepository.replyActivationCode(mModel.getLogin());
    }
    
    public void changeLogin(){
//        EventBus.getDefault().post(new ChangeLoginEvent());
        exit();
    }
    
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onRegisterSuccessEvent(RegisterActivationSuccessEvent event) {
//        if(mContext!=null)
//            BookApplication.INSTANCE.getDataStore().setRole(mContext,Constants.Roles.ROLE_USER_DEFECTIVE);
//        EventBus.getDefault().unregister(this);
//        EventBus.getDefault().post(new LoginEvent());
//        getViewState().hideProgress();
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onRegisterErrorEvent(RegisterActivationErrorEvent event) {
//        EventBus.getDefault().unregister(this);
//        getViewState().setError(event.getError());
//        getViewState().hideProgress();
//    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterGetActivationCodeSuccessEvent(RegisterGetActivationCodeSuccessEvent event) {
        Toast.makeText(mContext,mContext.getResources().getString(
                R.string.authentication_registration_confirm_code_sent),Toast.LENGTH_SHORT).show();
        DataStoreTemp.setLastTimeForActivationCode((new Date()).getTime());
        onCodeSent();
        getViewState().hideProgress();
        EventBus.getDefault().unregister(this);
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterGetActivationCodeErrorEvent(RegisterGetActivationCodeErrorEvent event) {
        getViewState().setError(event.getError());
        if(event.getTimeLeft()!= null){
            Date now = new Date();
            now.setTime(now.getTime()-event.getTimeLeft());
            DataStoreTemp.setLastTimeForActivationCode(now.getTime());
            onCodeSent();
        }
        
        getViewState().hideProgress();
        EventBus.getDefault().unregister(this);
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterActivationCodeSuccessEvent(RegisterActivationSuccessEvent event) {
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().post(new GoToChangePasswordEvent(mModel.getConfirmCode()));
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterActivationCodeErrorEvent(RegisterActivationErrorEvent event) {
        EventBus.getDefault().unregister(this);
        getViewState().setError(event.getError());
        getViewState().hideProgress();
    }
    
    public class MyTimer extends CountDownTimer
    {
        public MyTimer(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }
        
        @Override
        public void onFinish()
        {
            getViewState().setReplyCodeVisible();
        }
        
        public void onTick(long millisUntilFinished)
        {
            getViewState().setTime((int)(millisUntilFinished/1000));
        }
    }
}

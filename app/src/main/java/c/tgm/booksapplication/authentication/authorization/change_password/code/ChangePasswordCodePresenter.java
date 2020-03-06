package c.tgm.booksapplication.authentication.authorization.change_password.code;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.authentication.authorization.change_password.ChangePasswordRepository;
import c.tgm.booksapplication.authentication.authorization.change_password.ChangePasswordRepositoryImpl;
import c.tgm.booksapplication.authentication.authorization.change_password.code.events.CheckResetCodeSuccessEvent;
import c.tgm.booksapplication.authentication.authorization.change_password.code.events.GetResetPasswordCodeFailureEvent;
import c.tgm.booksapplication.authentication.events.BackEvent;
import c.tgm.booksapplication.authentication.events.ResetCodeInputEvent;
import c.tgm.booksapplication.events_for_bus.ErrorEvent;
import c.tgm.booksapplication.events_for_bus.SuccessEvent;

@InjectViewState
public class ChangePasswordCodePresenter extends MvpPresenter<ChangePasswordCodeView> {
    private ChangePasswordCodeModel mModel;
    private Context mContext;
    private ChangePasswordRepository mRepository;
    private MyTimer currentTimer;
    
    public ChangePasswordCodePresenter(){
        mModel = new ChangePasswordCodeModel();
        mRepository = new ChangePasswordRepositoryImpl();
        onCodeSent();
    }
    
    public void onCreate(Context context){
        mContext = context;
    }
    
    public void onDestroy(){
        mContext = null;
    }
    
    public void onCodeChanged(String resetCode){
        mModel.setResetCode(resetCode);
        getViewState().changeSendCodeButtonEnabled(mModel.inputIsCorrect());
    }
    
    public void replyResetCode(){
        getViewState().showProgress();
        EventBus.getDefault().register(this);
        mRepository.getResetPasswordCode(mModel.getLogin());
    }
    
    public void rememberLogin(String login){
        mModel.setLogin(login);
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessEvent(SuccessEvent event) {
        Toast.makeText(mContext, R.string.authentication_reset_code_code_sent,Toast.LENGTH_SHORT).show();
        onCodeSent();
        EventBus.getDefault().unregister(this);
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent event) {
        EventBus.getDefault().unregister(this);
        getViewState().setError(event.getError());
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetResetPasswordCodeFailureEvent(GetResetPasswordCodeFailureEvent event) {
        EventBus.getDefault().unregister(this);
        
//        if(event.getTimeLeft()!= null){
//            Date now = new Date();
//            DataStoreTemp.setLastTimeForResetPasswordCode(now.getTime()+event.getTimeLeft());
//            onCodeSent();
//        }
        
        getViewState().setError(event.getError());
        getViewState().hideProgress();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckResetCodeSuccessEvent(CheckResetCodeSuccessEvent event) {
        getViewState().hideProgress();
        
        EventBus.getDefault().unregister(this);
        
        EventBus.getDefault().post(new ResetCodeInputEvent(mModel.getResetCodeSent()));
    }
    
    public void checkCode() {
        if(!mModel.inputIsCorrect()){
            getViewState().setError(mContext.getResources().getString(R.string.authentication_reset_code_code_incorrect));
            return;
        }
        
        mModel.setResetCodeSent(mModel.getResetCode());
        EventBus.getDefault().register(this);
        mRepository.checkResetPasswordCode(mModel.getLogin(),mModel.getResetCode());
    }
    
    public void changeLogin() {
        EventBus.getDefault().post(new BackEvent());
    }
    
    private void onCodeSent(){
    
    }
    
    private class MyTimer extends CountDownTimer
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

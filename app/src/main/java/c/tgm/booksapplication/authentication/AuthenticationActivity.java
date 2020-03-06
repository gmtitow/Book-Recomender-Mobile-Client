package c.tgm.booksapplication.authentication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import c.tgm.booksapplication.AbstractActivity;
import c.tgm.booksapplication.AbstractPresenter;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.AuthorizationAnimationManager;
import c.tgm.booksapplication.any.CustomAppNavigator;
import c.tgm.booksapplication.any.KeyboardUtils;
import c.tgm.booksapplication.authentication.events.BackEvent;
import c.tgm.booksapplication.authentication.events.ChangeLoginEvent;
import c.tgm.booksapplication.authentication.events.ChangePasswordEvent;
import c.tgm.booksapplication.authentication.events.ChangePasswordLoginInputEvent;
import c.tgm.booksapplication.authentication.events.GoToChangePasswordEvent;
import c.tgm.booksapplication.authentication.events.GoToRegisterEvent;
import c.tgm.booksapplication.authentication.events.LoginEvent;
import c.tgm.booksapplication.authentication.events.RegisterEvent;
import c.tgm.booksapplication.authentication.events.RegisterLoginInputEvent;
import c.tgm.booksapplication.authentication.events.ResetCodeInputEvent;
import c.tgm.booksapplication.databinding.ActivityAuthenticationBinding;
import ru.terrakok.cicerone.Navigator;

public class AuthenticationActivity extends AbstractActivity implements AuthenticationView {
    
    private static final String NEEDED_SCREEN = "needed_screen";
    
    ActivityAuthenticationBinding mBinding;
    
    //Содержит текущее состояние клавиатуры
    private boolean keyboardVisible = false;
    
    @InjectPresenter
    AuthenticationPresenter mPresenter;
    
    private Navigator navigator = new CustomAppNavigator(this,
            R.id.fragmentContainer);
    
    
    @Override
    public Navigator getNavigator() {
        return navigator;
    }
    
    @Override
    public AbstractPresenter getPresenter() {
        return mPresenter;
    }
    
    @Override
    public boolean withEventBus() {
        return true;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //mPresenter.onCreate(this);
        
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_authentication);
        
        KeyboardUtils.addKeyboardToggleListener(this, R.id.content, new KeyboardUtils.SoftKeyboardToggleListener() {
            @Override
            public void onToggleSoftKeyboard(boolean isVisible) {
                keyboardVisible = isVisible;
            }
        });
        String screen = getIntent().getStringExtra(NEEDED_SCREEN);
        
        if (screen!=null && !screen.isEmpty()) {
            if (screen.equals(Screens.AuthenticationScreens.REGISTRATION_CONFIRM_CODE_SCREEN)) {
                mPresenter.replaceScreen(
                        new Screens.AuthenticationScreens(
                                Screens.AuthenticationScreens.REGISTRATION_LOGIN_SCREEN));
                mPresenter.navigateTo(new Screens.AuthenticationScreens(
                        Screens.AuthenticationScreens.REGISTRATION_CONFIRM_CODE_SCREEN));
            } else {
                mPresenter.navigateTo(new Screens.AuthenticationScreens(screen));
            }
        }
        
        setupViews();
    }
    
    private void setupViews() {
    
    }
    
    @Override
    public void onBackPressed() {
        if (keyboardVisible) {
            closeKeyboard();
            return;
        }
        
        mPresenter.exit();
    }
    
    @Override
    public void closeKeyboard() {
        if (keyboardVisible)
            KeyboardUtils.toggleKeyboardVisibility(this);
    }
    
    public static Intent getInstance(Context context) {
        return new Intent(context, AuthenticationActivity.class);
    }
    
    public static Intent getInstance(Context context, String screen) {
        Intent intent = new Intent(context, AuthenticationActivity.class);
        intent.putExtra(NEEDED_SCREEN, screen);
        return intent;
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBackEvent(BackEvent event) {
        mPresenter.exit();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        finish();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterEvent(RegisterEvent event) {
//        mPresenter.onRegisterScreen();
        finish();
    }
    
    
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void goToConfirmCodeEvent(goToConfirmCodeEvent event) {
//        mPresenter.goToRegisterConfirmCodeScreen();
//    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goToRegisterEvent(GoToRegisterEvent event) {
        mPresenter.goToRegisterLoginScreen(event.getLogin());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterLoginInputEvent(RegisterLoginInputEvent event) {
        AuthorizationAnimationManager.setCurrentAnimation(AuthorizationAnimationManager.ONLY_LEFT);
        mPresenter.goToRegisterConfirmCodeScreen(event.getLogin());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLoginEvent(ChangeLoginEvent event) {
        //mPresenter.backTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.REGISTRATION_LOGIN_SCREEN));
        mPresenter.backToRegisterLoginScreen();
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangePasswordLoginInputEvent(ChangePasswordLoginInputEvent event) {
        mPresenter.backToChangePasswordCodeScreen(event.getLogin());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goToChangePasswordEvent(GoToChangePasswordEvent event) {
        mPresenter.goToRegisterPasswordScreen(event.getActivationCode());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResetCodeInputEvent(ResetCodeInputEvent event) {
        mPresenter.goToChangePasswordPassword(event.getResetCode());
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangePasswordEvent(ChangePasswordEvent event) {
        mPresenter.backTo(new Screens.AuthenticationScreens(Screens.AuthenticationScreens.AUTHORIZATION_SCREEN));
        Toast.makeText(this,R.string.autentication_change_password_success,Toast.LENGTH_SHORT).show();
    }
}

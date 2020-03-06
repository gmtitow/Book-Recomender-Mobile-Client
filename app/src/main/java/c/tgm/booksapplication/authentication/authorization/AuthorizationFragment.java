package c.tgm.booksapplication.authentication.authorization;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.databinding.FragmentAuthorizationBinding;

import static java.lang.Integer.parseInt;

public class AuthorizationFragment extends MvpAppCompatFragment implements AuthorizationView {

    @InjectPresenter(type = PresenterType.LOCAL)
    AuthorizationPresenter mPresenter;
    
    FragmentAuthorizationBinding mBinding;
    
    public AuthorizationFragment() {
    }
    
    @Override
    public void onStart() {
        super.onStart();
        //EventBus.getDefault().changePassword(this);
    }
    
    @Override
    public void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPresenter.onCreate(getContext());
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_authorization, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        setupViews(mBinding.getRoot());
        
        mBinding.editLogin.setText("titow.german@yandex.ru");
        mBinding.editPassword.setText("123456");
    }
    
    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
    
    @Override
    public void onResume() {
        super.onResume();
//        mBinding.editLogin.setText(DataStore.getActivationCode());
    }
    
    private void setupViews(View view) {
    
        mBinding.editLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onLoginChanged(s.toString());
            }
        
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    
        mBinding.editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onPasswordChanged(s.toString());
            }
        
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        
        mBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submitLogin();
            }
        });
        
        mBinding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goToRegister();
            }
        });
        
//        mBinding.forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.goToChangePassword();
//            }
//        });
    }
    
    public static Fragment getInstance() {
        AuthorizationFragment fragment = new AuthorizationFragment();
        return fragment;
    }
    
    @Override
    public void changeEnterButtonEnabled(boolean enabled) {
        mBinding.loginButton.setEnabled(enabled);
        mBinding.loginButton.setImageDrawable(enabled?
                getActivity().getResources().getDrawable(R.mipmap.authorization_button_background_enable):
                getActivity().getResources().getDrawable(R.mipmap.authorization_button_background_unenable));
    }
    
    @Override
    public void setError(String error) {
        mBinding.editLogin.setError(error);
        mBinding.editLogin.requestFocus();
    }
    
    @Override
    public void showProgress() {
        mBinding.progress.show();
        changeEnterButtonEnabled(false);
    }
    
    @Override
    public void hideProgress() {
        mBinding.progress.hide();
        changeEnterButtonEnabled(true);
    }
    
    @Override
    public void setLoginError(String error) {
        mBinding.editLogin.setError(error);
        mBinding.editLogin.requestFocus();
    }
    
    @Override
    public void setPasswordError(String error) {
        mBinding.editPassword.setError(error);
        mBinding.editPassword.requestFocus();
    }
    
    @Override
    public void clearErrors() {
        mBinding.editPassword.setError(null);
        mBinding.editLogin.setError(null);
    }
}

package c.tgm.booksapplication.authentication.registration.password;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.databinding.FragmentRegistrationPasswordBinding;
import org.greenrobot.eventbus.EventBus;

public class RegistrationPasswordFragment extends MvpAppCompatFragment implements RegistrationPasswordView {
    
    public static final String REGISTER_LOGIN = "register_login";
    public static final String REGISTER_ACTIVATION_CODE = "register_activation_code";
    
    @InjectPresenter(type = PresenterType.LOCAL)
    RegistrationPasswordPresenter mPresenter;
    
    FragmentRegistrationPasswordBinding mBinding;
    
    public RegistrationPasswordFragment() {
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration_password, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    
        setupViews(mBinding.getRoot());
    
        if(getArguments()!= null && getArguments().containsKey(REGISTER_LOGIN)
                && getArguments().containsKey(REGISTER_ACTIVATION_CODE)){
            mPresenter.rememberLogin(getArguments().getString(REGISTER_LOGIN),getArguments().getString(REGISTER_ACTIVATION_CODE));
        }
    }
    
    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
    
    private void setupViews(View view) {
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
    
        mBinding.editConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onConfirmChanged(s.toString());
            }
        
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        
        mBinding.editConfirm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                mPresenter.register();
                return false;
            }
        });
        
        mBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.register();
            }
        });
    }
    
    public static Fragment getInstance(String login, String activationCode) {
        RegistrationPasswordFragment fragment = new RegistrationPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putString(REGISTER_LOGIN,login);
        bundle.putString(REGISTER_ACTIVATION_CODE,activationCode);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void changeRegisterButtonEnabled(boolean enabled) {
        mBinding.loginButton.setEnabled(enabled);
        mBinding.loginButton.setImageDrawable(enabled?
                getActivity().getResources().getDrawable(R.mipmap.authorization_button_background_enable):
                getActivity().getResources().getDrawable(R.mipmap.authorization_button_background_unenable));
    }
    
    @Override
    public void setError(String error) {
        mBinding.editPassword.setError(error);
    }
    
    @Override
    public void clearErrors() {
        mBinding.editPassword.setError(null);
    }
    
    @Override
    public void setPasswordError(String error) {
        mBinding.editPassword.setError(error);
    }
    
    @Override
    public void showProgress() {
        mBinding.progress.show();
        changeRegisterButtonEnabled(false);
    }
    
    @Override
    public void hideProgress() {
        mBinding.progress.hide();
        changeRegisterButtonEnabled(true);
    }
}
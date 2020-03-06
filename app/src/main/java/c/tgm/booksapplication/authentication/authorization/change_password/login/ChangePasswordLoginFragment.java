package c.tgm.booksapplication.authentication.authorization.change_password.login;

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
import c.tgm.booksapplication.databinding.FragmentRegistrationLoginBinding;


public class ChangePasswordLoginFragment extends MvpAppCompatFragment implements ChangePasswordLoginView {
    
    public static final String REGISTER_LOGIN = "register_login";
    @InjectPresenter(type = PresenterType.LOCAL)
    ChangePasswordLoginPresenter mPresenter;
    
    FragmentRegistrationLoginBinding mBinding;
    
    public ChangePasswordLoginFragment() {
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration_login, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    
        setupViews(mBinding.getRoot());
        
        if(getArguments()!= null && getArguments().containsKey(REGISTER_LOGIN)){
            mBinding.editLogin.setText(getArguments().getString(REGISTER_LOGIN));
        }
    }
    
    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
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
        
        mBinding.editLogin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                mPresenter.goToCode();
                return false;
            }
        });
        
        mBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goToCode();
            }
        });
    }
    
    public static Fragment getInstance() {
        ChangePasswordLoginFragment fragment = new ChangePasswordLoginFragment();
        return fragment;
    }
    
    public static Fragment getInstance(String login) {
        ChangePasswordLoginFragment fragment = new ChangePasswordLoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString(REGISTER_LOGIN,login);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void changeRegisterButtonEnabled(boolean enabled) {
        mBinding.loginButton.setEnabled(enabled);
        mBinding.loginButton.setImageDrawable(enabled?
                getActivity().getResources().getDrawable(R.drawable.ic_menu_send):
                getActivity().getResources().getDrawable(R.drawable.ic_menu_share));
    }
    
    @Override
    public void setError(String error) {
        mBinding.editLogin.setError(error);
    }
    
    @Override
    public void setLoginError(String error) {
        mBinding.editLogin.setError(error);
        mBinding.editLogin.requestFocus();
    }
    
    @Override
    public void clearErrors() {
        mBinding.editLogin.setError(null);
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
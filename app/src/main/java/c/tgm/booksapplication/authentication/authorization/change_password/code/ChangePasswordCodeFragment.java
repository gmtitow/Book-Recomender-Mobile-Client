package c.tgm.booksapplication.authentication.authorization.change_password.code;

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
import c.tgm.booksapplication.databinding.FragmentRegistrationConfirmCodeBinding;

public class ChangePasswordCodeFragment extends MvpAppCompatFragment implements ChangePasswordCodeView {
    
    public static final String REGISTER_LOGIN = "register_login";
    @InjectPresenter(type = PresenterType.LOCAL)
    ChangePasswordCodePresenter mPresenter;
    
    FragmentRegistrationConfirmCodeBinding mBinding;
    
    public ChangePasswordCodeFragment() {
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPresenter.onCreate(getContext());
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration_confirm_code, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    
        setupViews(mBinding.getRoot());
        
        if(getArguments()!= null && getArguments().containsKey(REGISTER_LOGIN)){
            mPresenter.rememberLogin(getArguments().getString(REGISTER_LOGIN));
        }
    }
    
    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
    
    private void setupViews(View view) {
    
        mBinding.editConfirmCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onCodeChanged(s.toString());
            }
        
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    
        mBinding.editConfirmCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                mPresenter.checkCode();
                return false;
            }
        });
    
        mBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.checkCode();
            }
        });
    
        mBinding.textChangeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.changeLogin();
            }
        });
    
        mBinding.textSendConfirmToMailAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.replyResetCode();
            }
        });
    }
    
    public static Fragment getInstance() {
        ChangePasswordCodeFragment fragment = new ChangePasswordCodeFragment();
        return fragment;
    }
    
    public static Fragment getInstance(String login) {
        ChangePasswordCodeFragment fragment = new ChangePasswordCodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(REGISTER_LOGIN,login);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void changeSendCodeButtonEnabled(boolean enabled) {
        mBinding.loginButton.setEnabled(enabled);
        mBinding.loginButton.setImageDrawable(enabled?
                getActivity().getResources().getDrawable(R.drawable.ic_menu_send):
                getActivity().getResources().getDrawable(R.drawable.ic_menu_share));
    }
    
    @Override
    public void setError(String error) {
        mBinding.editConfirmCode.setError(error);
        mBinding.editConfirmCode.requestFocus();
    }
    
    @Override
    public void clearErrors() {
        mBinding.editConfirmCode.setError(null);
    }
    
    @Override
    public void showProgress() {
        mBinding.progress.show();
        changeSendCodeButtonEnabled(false);
    }
    
    @Override
    public void hideProgress() {
        mBinding.progress.hide();
        changeSendCodeButtonEnabled(true);
    }
    
    @Override
    public void setConfirmTimeVisible() {
        mBinding.textReplyTime.setVisibility(View.VISIBLE);
        mBinding.textSendConfirmToMailAgain.setVisibility(View.GONE);
    }
    
    @Override
    public void setTime(int seconds) {
        if(getActivity()!= null)
            mBinding.textReplyTime.setText(getActivity().getResources().getString(
                    R.string.authentication_registration_confirm_code_enter_code_reply,seconds));
    }
    
    @Override
    public void setReplyCodeVisible() {
        mBinding.textReplyTime.setVisibility(View.GONE);
        mBinding.textSendConfirmToMailAgain.setVisibility(View.VISIBLE);
    }
}
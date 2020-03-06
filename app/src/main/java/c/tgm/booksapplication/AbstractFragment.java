package c.tgm.booksapplication;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpView;

import org.greenrobot.eventbus.EventBus;

public abstract class AbstractFragment extends MvpAppCompatFragment implements MvpView {
    
    public abstract AbstractPresenter getPresenter();
    
    public abstract boolean withEventBus();
    
    public String getTitle() {
        return "BookApplication";
    }
    
    public boolean isChangeTitle() {
        return true;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
        if (withEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        
        if (getPresenter() != null)
            getPresenter().setView(this);
    
        if (isChangeTitle())
            getActivity().setTitle(getTitle());
    }
    
    @Override
    public void onStop() {
        super.onStop();
//        if (withEventBus()) {
//            EventBus.getDefault().unregister(this);
//        }
        getPresenter().clearView();
    }
}

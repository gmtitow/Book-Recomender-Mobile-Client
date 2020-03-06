package c.tgm.booksapplication;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;

import org.greenrobot.eventbus.EventBus;

import ru.terrakok.cicerone.Navigator;

public abstract class AbstractActivity extends MvpAppCompatActivity implements MvpView {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        
        if (withEventBus()) {
            EventBus.getDefault().register(this);
        }
        
        if (getPresenter() != null)
            getPresenter().setView(this);
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        if (withEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        getPresenter().clearView();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        BookApplication.INSTANCE.getNavigatorHolder().setNavigator(getNavigator());
    }
    
    
    @Override
    protected void onPause() {
        super.onPause();
        BookApplication.INSTANCE.getNavigatorHolder().removeNavigator();
    }
    
    @Override
    protected void onDestroy() {
        getPresenter().onDestroy();
        super.onDestroy();
    }
    
    public abstract Navigator getNavigator();
    
    public abstract AbstractPresenter getPresenter();
    
    public abstract boolean withEventBus();
}

package c.tgm.booksapplication;

import android.view.View;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

public abstract class AbstractPresenter<View extends MvpView> extends MvpPresenter<View> {
    
    private View mvpView;
    
    public void setView(View view) {
        mvpView = view;
    }
    public View getView() {
        return mvpView;
    }
    public void clearView(){
        mvpView = null;
    }
}

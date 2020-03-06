package c.tgm.booksapplication.main;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface MainActivityView extends MvpView {
    
    public void someViewMethod();
    
    public void updateAuthenticated(boolean isAuthenticated, String login);
}

package c.tgm.booksapplication;

import com.arellomobile.mvp.MvpView;

import java.util.Stack;

import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.Screen;

abstract public class NavigatorPresenter<View extends MvpView> extends AbstractPresenter<View> {
    protected Router mRouter;
    protected Stack<Screen> mScreens = new Stack<>();
    
    public NavigatorPresenter() {
        mRouter = BookApplication.INSTANCE.getRouter();
    }
    
    public void exit() {
        if (mScreens.size() > 0) {
            mScreens.pop();
        }
        mRouter.exit();
    }
    
    public void navigateTo(Screen screen) {
        mScreens.push(screen);
        mRouter.navigateTo(screen);
    }
    
    public void backTo(Screen screen) {
        while (mScreens.size() > 0 && !mScreens.get(mScreens.size() - 1).getScreenKey().equals(screen.getScreenKey())) {
            mScreens.pop();
        }
        
        mRouter.backTo(screen);
    }
    
    public void replaceScreen(Screen screen) {
        mRouter.replaceScreen(screen);
        if (mScreens.size() > 0)
            mScreens.pop();
        mScreens.push(screen);
    }
}

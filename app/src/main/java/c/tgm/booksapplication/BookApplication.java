package c.tgm.booksapplication;

import android.app.Application;

import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.api.Controller;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class BookApplication extends Application {
    public static BookApplication INSTANCE;
    private Cicerone<Router> cicerone;
    private Controller mController;
    private DataStore mDataStore;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        cicerone = Cicerone.create();
        mController = new Controller(getApplicationContext());
        mDataStore = new DataStore(this);
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        return cicerone.getRouter();
    }
    
    public Controller getController(){
        return mController;
    }
}

package c.tgm.booksapplication.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import c.tgm.booksapplication.AbstractActivity;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.ActivityGlobalNavigator;
import c.tgm.booksapplication.any.CustomAppNavigator;
import c.tgm.booksapplication.databinding.ActivityMainBinding;
import ru.terrakok.cicerone.Navigator;

public class MainActivity extends AbstractActivity
                        implements NavigationView.OnNavigationItemSelectedListener, MainActivityView {
    
    @InjectPresenter
    MainActivityPresenter mPresenter;
    
    //Views
    private ActivityMainBinding mBinding;
    
    //menu items
    MenuItem itemLogin;
    MenuItem itemRegister;
    MenuItem itemExit;
    MenuItem itemReviewList;
    
    TextView mTextLogin;
    
    private Navigator navigator = new CustomAppNavigator(this,
            R.id.fragmentContainer);
    
    private ActivityGlobalNavigator mActivityNavigator = new ActivityGlobalNavigator(this);
    
    public ActivityGlobalNavigator getActivityNavigator() {
        return mActivityNavigator;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        getPresenter().replaceScreen(new Screens.BookScreens(Screens.BookScreens.FIND_BOOK_SCREEN));
    
        setupViews();
    }
    
    private void setupViews() {
    
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
    
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    
        itemLogin = navigationView.getMenu().findItem(R.id.nav_authorization);
        itemRegister = navigationView.getMenu().findItem(R.id.nav_register);
        itemExit = navigationView.getMenu().findItem(R.id.nav_exit);
        itemReviewList = navigationView.getMenu().findItem(R.id.nav_users_reviews);
    
        View navHeaderView = mBinding.navView.inflateHeaderView(R.layout.nav_header_main);
        mTextLogin = navHeaderView.findViewById(R.id.textLogin);
    }
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
    
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
    
        switch(id) {
            case R.id.nav_authorization:{
                getActivityNavigator().goToActivity(
                        ActivityGlobalNavigator.AUTHENTICATION_ACTIVITY);
                break;
            }
            case R.id.nav_register:{
        
                getActivityNavigator().goToActivity(
                        ActivityGlobalNavigator.AUTHENTICATION_ACTIVITY,Screens.AuthenticationScreens.REGISTRATION_LOGIN_SCREEN);
                break;
            }
            case R.id.nav_find_books:{
        
                getPresenter().navigateTo(new Screens.BookScreens(Screens.BookScreens.FIND_BOOK_SCREEN));
                break;
            }
            case R.id.nav_recommendations:{
        
                getPresenter().navigateTo(new Screens.BookScreens(Screens.BookScreens.GET_RECOMMENDS_SCREEN));
                break;
            }
            case R.id.nav_find_authors:{
        
                getPresenter().navigateTo(new Screens.AuthorScreens(Screens.AuthorScreens.FIND_AUTHOR_SCREEN));
                break;
            }
            case R.id.nav_users_reviews:{
                getPresenter().navigateTo(new Screens.ReviewScreens(Screens.ReviewScreens.REVIEW_LIST_SCREEN));
                break;
            }
            case R.id.nav_exit:{
    
                getPresenter().logout(this);
                break;
            }
        }
        
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    
    @Override
    public Navigator getNavigator() {
        return navigator;
    }
    
    @Override
    public MainActivityPresenter getPresenter() {
        return mPresenter;
    }
    
    @Override
    public void someViewMethod() {
    
    }
    
    @Override
    public boolean withEventBus() {
        return false;
    }
    
    @Override
    public void updateAuthenticated(boolean isAuthenticated, String login) {
        itemExit.setVisible(isAuthenticated);
        itemLogin.setVisible(!isAuthenticated);
        itemRegister.setVisible(!isAuthenticated);
        itemReviewList.setEnabled(isAuthenticated);
//        itemReviewList.setVisible(isAuthenticated);
    
        mTextLogin.setText(login);
    }
}
